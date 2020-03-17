/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.utils;

import com.citec.treeLinker.core.tree.Tupple;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 *
 * @author elahi
 */
public class FileUtils {

    public static void WriteToFile(List<Tupple> inputTupples, String fileName) throws IOException {
        String str = "";
        for (Tupple tuple : inputTupples) {
            String line = tuple.getEntry().replace(" ", "_") + "=" + tuple.getUri() + "\n";
            str += line;
            write(str, fileName);
        }

    }

    public static void write(String str, String fileName)
            throws IOException {
        /*File file = new File(fileName);
        if (file.exists()) {
            file.deleteOnExit();
        }*/
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(str);
        writer.close();

    }

    public static File[] getFiles(String fileDir, String ntriple) throws Exception {

        File dir = new File(fileDir);
        FileFilter fileFilter = new WildcardFileFilter("*" + ntriple);
        File[] files = dir.listFiles(fileFilter);
        return files;

    }

    public static List<Tupple> readFileLineByLine(String fileName) {
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
        return getQuestionAnswerList(questions, answers);
    }

    public static void readFile(Map<String, String> terms, String content, String fileName) throws IOException {
        String str = "";
        for (String term : terms.keySet()) {
            String line = term + "=" + terms.get(term);
            str += line + "\n";
        }
        stringToFile(str, fileName);
    }
    
    public static String output(List<Tupple> inputTupples) throws IOException {
        String str = "";
        for (Tupple tupple : inputTupples) {
            String line = tupple.getEntry() + "=" + tupple.getUri();
            str += line + "\n";
        }
        return str;
    }

    public static void stringToFile(String str, String fileName)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(str);
        writer.close();

    }

    private static List<Tupple> getQuestionAnswerList(List<String> questions, List<String> answers) {
        List<Tupple> inputTupples = new ArrayList<Tupple>();
        for (Integer index = 0; index < questions.size(); index++) {
            String question=questions.get(index);
            String answer=answers.get(index);
            inputTupples.add(new Tupple(question, answer, "legal"));
            System.out.println(question+" "+answer+" "+"legal");
        }
         
        return inputTupples;
    }

}
