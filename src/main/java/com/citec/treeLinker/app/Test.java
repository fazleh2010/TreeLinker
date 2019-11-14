package com.citec.treeLinker.app;

import com.citec.treeLinker.core.tree.Result;
import com.citec.treeLinker.core.tree.TreeLexicon;
import com.citec.treeLinker.api.InformationFinder;
import com.citec.treeLinker.core.input.JsonReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    private static String INPUT_LOCATION = "src/main/resources";
    private static String INPUT_JSON = "/qald/8/data/qald-8-test-multilingual.json";
    private static String INPUT_TEXT = "entityLinking_1.tsv";

    public static void main(String[] args) throws IOException {
        inputFromJsonFile();
        //inputFromTextFile();
        
    }

    private static void inputFromJsonFile() throws IOException {
        try {
            InputStream inputStream =new FileInputStream(INPUT_LOCATION+File.separator+INPUT_JSON);
            JsonReader informationFinder = new JsonReader(inputStream);
            
           

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        //createTree();
    }

    private static void inputFromTextFile() throws FileNotFoundException, IOException {
        // Open the file that is the first
        // command line parameter
        FileInputStream fstream = new FileInputStream(INPUT_LOCATION+File.separator+INPUT_TEXT);
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

        List<Result> results = lexicon.lookup("Gabriel Filmtheater");

        for (Result result : results) {
            System.out.println(result);
        }

        System.out.print("Testing...Gabriel Frlmtheater\n");

        results = lexicon.lookup("Gabriel Frlmtheater");

        for (Result result : results) {
            System.out.println(result);
        }

        System.out.print("Testing...Gabriel Frlmtheater\n");

        results = lexicon.lookup("Gabriel Frlmtheater", 0.1);

        for (Result result : results) {
            System.out.println(result);
        }

        System.out.print("Testing...Ich liebe Gabriel Frlmtheater und Odeon\n");

        results = lexicon.lookup("Ich liebe Gabriel Frlmtheater und Odeon", 0.1);

        for (Result result : results) {
            System.out.println(result);
        }
    }
}
