/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.core.tree;

import com.citec.treeLinker.api.InformationFinder;
import com.citec.treeLinker.core.input.Answers;
import com.citec.treeLinker.core.input.DataUnit;
import com.citec.treeLinker.core.input.JsonReader;
import com.citec.treeLinker.core.input.Question;
import com.citec.treeLinker.core.input.AnswerURI;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author elahi
 */
public class CreateTree {

    private TreeLexicon lexicon = new TreeLexicon();

    public CreateTree(String fileName, Integer number) throws IOException {
        if (number == 1) {
            List<Tupple> inputTupples = this.getInputTupplesFromJsonFile(fileName);
            TreeLexicon treeLexicon = createTree(inputTupples);
            checkResultWhenJsonFile(treeLexicon);
        } else {
            List<Tupple> inputTupples = getInputTupplesFromTextFile(fileName);
            TreeLexicon treeLexicon = createTree(inputTupples);
            checkResultWhenTextFile(treeLexicon);
        }

    }

    public List<Tupple> getInputTupplesFromJsonFile(String fileName) throws IOException {
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
            List<String> answers = AnswerURI.getAnswer(answerUnits);
            HashMap<String, String> sparql = dataUnit.getQuery();
            /*System.out.println("id:" + id);
            System.out.println("question:" + questionString);
            System.out.println("answers:" + answers);
            System.out.println("sparql:" + sparql);*/
            //System.out.println("entry: "+questionString+" uri:"+answers.toString()+" type:"+sparql);
            Tupple tupple = new Tupple(questionString, answers.toString(), sparql.toString());
            inputTupples.add(tupple);
        }
        return inputTupples;
    }

    public List<Tupple> getInputTupplesFromTextFile(String fileName) throws FileNotFoundException, IOException {
        // Open the file that is the first
        // command line parameter
        List<Tupple> inputTupples = new ArrayList<Tupple>();
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
                System.out.println("entry: " + entry + " uri:" + uri + " type:" + type);
            }
        }
        return inputTupples;
    }

    private TreeLexicon createTree(List<Tupple> inputTupples) throws FileNotFoundException, IOException {
        TreeLexicon lexicon = new TreeLexicon();

        for (Tupple tupple : inputTupples) {
            lexicon.insert(tupple.getEntry(), tupple.getUri(), tupple.getType());
        }

        checkResultWhenTextFile(lexicon);
        return lexicon;
    }

    private void checkResultWhenTextFile(TreeLexicon lexicon) {
        System.out.print("Testing...Gabriel Filmtheater\n");
        List<com.citec.treeLinker.core.tree.Result> results = lexicon.lookup("Gabriel Filmtheater");
        for (com.citec.treeLinker.core.tree.Result result : results) {
            System.out.println(result);
        }
        System.out.print("Testing...Gabriel Frlmtheater\n");
        results = lexicon.lookup("Gabriel Frlmtheater");
        for (com.citec.treeLinker.core.tree.Result result : results) {
            System.out.println(result);
        }
        System.out.print("Testing...Gabriel Frlmtheater\n");
        results = lexicon.lookup("Gabriel Frlmtheater", 0.1);
        for (com.citec.treeLinker.core.tree.Result result : results) {
            System.out.println(result);
        }
        System.out.print("Testing...Ich liebe Gabriel Frlmtheater und Odeon\n");
        results = lexicon.lookup("Ich liebe Gabriel Frlmtheater und Odeon", 0.1);
        for (com.citec.treeLinker.core.tree.Result result : results) {
            System.out.println(result);
        }
    }

    private void checkResultWhenJsonFile(TreeLexicon lexicon) {
        System.out.print("Testing...Who is 8th president of US?\n");
        List<com.citec.treeLinker.core.tree.Result> results = lexicon.lookup("Who is 8th president of US?");
        for (com.citec.treeLinker.core.tree.Result result : results) {
            System.out.println(result);
        }
        System.out.print("Testing...Where is the most deep point in the ocean?\n");
        results = lexicon.lookup("Where is the most deep point in the ocean?");
        for (com.citec.treeLinker.core.tree.Result result : results) {
            System.out.println(result);
        }
        System.out.print("Testing...Who is the novelist of the work a song of ice and fire?\n");
        results = lexicon.lookup("the novelist of the work a song of ice", 0.1);
        for (com.citec.treeLinker.core.tree.Result result : results) {
            System.out.println(result);
        }
        System.out.print("Testing...Where is the birthplace of Goethe?\n");
        results = lexicon.lookup("birthplace of Goethe", 0.1);
        for (com.citec.treeLinker.core.tree.Result result : results) {
            System.out.println(result);
        }
    }

}
