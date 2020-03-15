package com.citec.treeLinker.app;

import com.citec.treeLinker.core.tree.CreateTree;
import com.citec.treeLinker.core.tree.ResultQA;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Test {

    //private static String INPUT_LOCATION = "src/main/resources";
    private static String INPUT_LOCATION = "/Users/elahi/NetBeansProjects/new/final/TreeLinker/src/main/resources";
    private static String INPUT_JSON = "/qald/8/data/qald-8-test-multilingual.json";
    private static String INPUT_ALL_JSON = "/qald/all/";
    private static String INPUT_TEXT = "entityLinking_1.tsv";

    public static void main(String[] args) throws IOException, Exception {
        CreateTree createTree;
        // If Json file then 
         //createTree = new CreateTree(INPUT_LOCATION + File.separator + INPUT_ALL_JSON,1);
         //CreateTree createTree1 = new CreateTree(search);
         createTree = new CreateTree(INPUT_LOCATION + File.separator + INPUT_TEXT,2);
         List<ResultQA>results=createTree.getResults("Gabriel Filmtheater");
         String str="";
         for (ResultQA result : results) {
             String line=result.getMatch()+"\n";
             str+=line;
        }
         System.out.println(str);
       
         //createTree = new CreateTree(INPUT_LOCATION+ File.separator +INPUT_ALL_JSON,1);

        // If text file then
        //createTree = new CreateTree(INPUT_LOCATION + File.separator + INPUT_TEXT,2);

    }

}
