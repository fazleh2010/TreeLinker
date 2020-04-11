/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.core.tree;

import static com.citec.treeLinker.api.Constants.INPUT_CSV;
import static com.citec.treeLinker.api.Constants.INPUT_JSON;
import static com.citec.treeLinker.api.Constants.INPUT_LOCATION;
import static com.citec.treeLinker.api.Constants.INPUT_TEXT;
import com.citec.treeLinker.api.InformationFinder;
import com.citec.treeLinker.core.input.Answers;
import com.citec.treeLinker.core.input.DataUnit;
import com.citec.treeLinker.core.input.JsonReader;
import com.citec.treeLinker.core.input.Question;
import com.citec.treeLinker.core.input.AnswerURI;
import com.citec.treeLinker.core.output.AnswerProcessor;
import com.citec.treeLinker.utils.FileRelatedUtils;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.citec.treeLinker.api.Constants.JSON;
import static com.citec.treeLinker.api.Constants.TEXT;
import static com.citec.treeLinker.api.Constants.TSV;

/**
 *
 * @author elahi
 */
public class CreateTree {

    private TreeLexicon treeLexicon = new TreeLexicon();
    private  List<Tupple> inputTupples = new ArrayList<Tupple>();
    private String OUTPUT_TEXT = "tbx2rdf_atc_en_A_B_alter.txt";

    public CreateTree(String inputType) throws IOException, Exception {

        String inputFileName = null;

        if (inputType.equals(JSON)) {
            //currently not working..needs to be check..
            inputFileName = INPUT_LOCATION + File.separator + INPUT_JSON;
            List<Tupple> allInputTupples = new ArrayList<Tupple>();
            File[] files = FileRelatedUtils.getFiles(inputFileName, ".json");
            for (File file : files) {
                 inputTupples = this.getInputTupplesFromJsonFile(inputFileName + file.getName());
                allInputTupples.addAll(inputTupples);
                TreeLexicon treeLexicon = createTree(inputTupples);
                checkResultWhenJsonFile();
            }
            FileRelatedUtils.WriteToFile(allInputTupples, inputFileName + OUTPUT_TEXT);

        } else if (inputType.equals(TSV)) {
            inputFileName = INPUT_LOCATION + File.separator + INPUT_CSV;
            inputTupples = getInputTupplesFromTextFile(inputFileName);
            treeLexicon = createTree(inputTupples);
            //checkResultWhenTextFile();
        } else if (inputType.equals(TEXT)) {
            inputFileName = INPUT_LOCATION + File.separator + INPUT_TEXT;
            inputTupples = getInputTupplesFromPython(inputFileName);
            treeLexicon = createTree(inputTupples);
            //temporarily closed...
            //checkResultWhenTextFile();
        }

    }

    public List<Tupple> getInputTupplesFromJsonFile(String fileName) throws IOException, Exception {
        List<Tupple> inputTupples = new ArrayList<Tupple>();
        InputStream inputStream = new FileInputStream(fileName);
        InformationFinder informationFinder = new JsonReader(inputStream);
        List<DataUnit> dataUnits = informationFinder.getDataUnit();
        for (DataUnit dataUnit : dataUnits) {
            System.out.println("****************************************");
            String id = dataUnit.getId();

            List<Question> questionUnit = dataUnit.getQuestion();
            String questionString = null;
            for (Question question : questionUnit) {
                questionString = question.getString();
                //System.out.println("question:" + questionString);
            }
            List<Answers> answerUnits = dataUnit.getAnswers();
            // List<String> answers = AnswerURI.getAnswer(answerUnits);
            HashMap<String, String> sparql = dataUnit.getQuery();
            /*System.out.println("id:" + id);
            System.out.println("question:" + questionString);
            System.out.println("answers:" + answers);
            System.out.println("sparql:" + sparql);*/
            //System.out.println("entry: "+questionString+" uri:"+answers.toString()+" type:"+sparql);
            AnswerProcessor answerProcessor = new AnswerProcessor(answerUnits, sparql);
            if (answerProcessor.getUriListFirstUrl()) {
                String uri = answerProcessor.getUri();
                Tupple tupple = new Tupple(questionString, uri, sparql.toString());
                inputTupples.add(tupple);
            }

        }
        return inputTupples;
    }

    public List<Tupple> getInputTupplesFromTextFile(String fileName) throws FileNotFoundException, IOException {
        // Open the file that is the first
        // command line parameter
        List<Tupple> inputTupples = new ArrayList<Tupple>();
        try {

            FileInputStream fstream = new FileInputStream(fileName);

            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String token, tag;
            //Read File Line By Line
            String entry;
            String uri;
            String type;
            Pattern p = Pattern.compile("(.*?)\t(.*?)\t(.*?)$");
            Matcher matcher;
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console

                matcher = p.matcher(strLine);

                if (matcher.find()) {
                    entry = matcher.group(2);
                    uri = matcher.group(1);
                    type = matcher.group(3);
                    inputTupples.add(new Tupple(entry, uri, type));
                    //System.out.println("entry: " + entry + " uri:" + uri + " type:" + type);
                }
            }
        } // doesn't matches with ArithmeticException 
        catch (Exception ex) {
            System.out.println("File not found exception!!!");
        }

        return inputTupples;
    }

    public List<Tupple> getInputTupplesFromPython(String fileName) throws FileNotFoundException, IOException {
        List<Tupple> inputTupples = new ArrayList<Tupple>();
        List<String> questions = new ArrayList<String>();
        List<String> answers = new ArrayList<String>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(":")) {
                    String[] info = line.split(":");
                    if (info[0].contains("Q")) {
                        questions.add(info[1]);
                    } else if (info[0].contains("A")) {
                        answers.add(info[1]);
                    }
                }
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Integer index = 0; index < questions.size(); index++) {
            String question = questions.get(index).toLowerCase().trim();
            String answer = answers.get(index).toLowerCase().trim();
            Tupple tupple = new Tupple(question, answer, "legal");
            inputTupples.add(tupple);
        }
        return inputTupples;

    }

    private TreeLexicon createTree(List<Tupple> inputTupples) throws FileNotFoundException, IOException {
        TreeLexicon lexicon = new TreeLexicon();

        for (Tupple tupple : inputTupples) {
            lexicon.insert(tupple.getEntry(), tupple.getUri(), tupple.getType());
        }

        //checkResultWhenTextFile(lexicon);
        return lexicon;
    }

    //orginal..
    public void checkResultWhenTextFile() {
        System.out.print("Testing...Gabriel Filmtheater\n");
        List<ResultQA> results = treeLexicon.lookup("Gabriel Filmtheater");
        for (ResultQA result : results) {
            System.out.println(result);
        }
        System.out.print("Testing...Gabriel\n");
        results =new ArrayList<ResultQA>();
        results = treeLexicon.lookup("Gabriel");
        for (ResultQA result : results) {
            System.out.println(result);
        }
        
        System.out.print("Testing...Filmtheater\n");
        results =new ArrayList<ResultQA>();
        results = treeLexicon.lookup("Filmtheater");
        for (ResultQA result : results) {
            System.out.println(result);
        }
        
        results =new ArrayList<ResultQA>();
        System.out.print("Testing...Gabriel Frlmtheater\n");
        results = treeLexicon.lookup("Gabriel Frlmtheater");
        for (ResultQA result : results) {
            System.out.println(result);
        }
        results =new ArrayList<ResultQA>();
        System.out.print("Testing...Gabriel Frlmtheater\n");
        results = treeLexicon.lookup("Gabriel Frlmtheater", 0.1);
        for (ResultQA result : results) {
            System.out.println(result);
        }
        results =new ArrayList<ResultQA>();
        System.out.print("Testing...Ich liebe Gabriel Frlmtheater und Odeon\n");
        results = treeLexicon.lookup("Ich liebe Gabriel Frlmtheater und Odeon", 0.1);
        for (ResultQA result : results) {
            System.out.println(result);
        }
    }

    private void checkResultWhenJsonFile() {
        System.out.print("Testing...Who is 8th president of US?\n");
        List<ResultQA> results = treeLexicon.lookup("Who is 8th president of US?");
        for (ResultQA result : results) {
            System.out.println(result);
        }
        System.out.print("Testing...Where is the most deep point in the ocean?\n");
        results = treeLexicon.lookup("Where is the most deep point in the ocean?");
        for (ResultQA result : results) {
            System.out.println(result);
        }
        System.out.print("Testing...Who is the novelist of the work a song of ice and fire?\n");
        results = treeLexicon.lookup("the novelist of the work a song of ice", 0.1);
        for (ResultQA result : results) {
            System.out.println(result);
        }
        System.out.print("Testing...Where is the birthplace of Goethe?\n");
        results = treeLexicon.lookup("birthplace of Goethe", 0.1);
        for (ResultQA result : results) {
            System.out.println(result);
        }
    }

    public List<ResultQA> getResults(String questionString) {
        return treeLexicon.lookup(questionString);
    }

    public TreeLexicon getTreeLexicon() {
        return treeLexicon;
    }

    public List<Tupple> getInputTupples() {
        return inputTupples;
    }

}
