/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.core.tree;

import com.citec.treeLinker.api.InformationFinder;
import com.citec.treeLinker.app.Test;
import com.citec.treeLinker.core.input.Answers;
import com.citec.treeLinker.core.input.DataUnit;
import com.citec.treeLinker.core.input.JsonReader;
import com.citec.treeLinker.core.input.Question;
import com.citec.treeLinker.core.input.AnswerURI;
import com.citec.treeLinker.core.tree.TreeLexicon;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author elahi
 */
public class CreateTree {

    public CreateTree(String string) throws IOException {
        this.createTreeFromJsonFile(string);
    }

    private void createTreeFromJsonFile(String fileName) throws IOException {

        InputStream inputStream = new FileInputStream(fileName);
        InformationFinder informationFinder = new JsonReader(inputStream);
        List<DataUnit> dataUnits = informationFinder.getDataUnit();
        for (DataUnit dataUnit : dataUnits) {
            System.out.println("****************************************");
            String id = dataUnit.getId();
            System.out.println("id:" + id);
            List<Question> questionUnit = dataUnit.getQuestion();
            for (Question question : questionUnit) {
                String questionString = question.getString();
                System.out.println("question:" + questionString);
            }
            List<Answers> answerUnits = dataUnit.getAnswers();
            List<String> answers = AnswerURI.getAnswer(answerUnits);
            System.out.println("answers:" + answers);
        }

    }

    private static void inputFromTextFile(String fileName) throws FileNotFoundException, IOException {
        // Open the file that is the first
        // command line parameter
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
        TreeLexicon lexicon = new TreeLexicon();
        Pattern p = Pattern.compile("(.*?)\t(.*?)\t(.*?)$");
        Matcher matcher;
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console

            matcher = p.matcher(strLine);

            if (matcher.find()) {
                entry = matcher.group(2);
                uri = matcher.group(1);
                type = matcher.group(3);
                /*System.out.print("******************************** "+"\n");
                System.out.print("entry: "+entry+"\n");
                System.out.print("uri: "+uri+"\n");
                System.out.print("type: "+type+"\n");*/
                lexicon.insert(entry, uri, type);

            }
        }

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

}
