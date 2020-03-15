/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.utils.helloWorld;

import com.citec.treeLinker.core.tree.CreateTree;
import java.io.File;

/**
 *
 * @author elahi
 */
public class HelloWorld {

    private static String INPUT_LOCATION = "src/main/resources";
    private static String INPUT_JSON = "/qald/8/data/qald-8-test-multilingual.json";
    private static String INPUT_ALL_JSON = "/qald/all/";
    private static String INPUT_TEXT = "entityLinking_1.tsv";

    public static void main(String[] args) throws Exception {
        CreateTree createTree;
        System.out.println("Hello, World");

        // If Json file then 
        try {
            // Protected code
            createTree = new CreateTree(INPUT_LOCATION + File.separator + INPUT_ALL_JSON, 1);
        } catch (Exception e1) {
           
        }

        //createTree = new CreateTree(INPUT_LOCATION+ File.separator +INPUT_ALL_JSON,1);
        // If text file then
        //createTree = new CreateTree(INPUT_LOCATION + File.separator + INPUT_TEXT,2);
        //System.out.println("Hello, World");
    }
}
