/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author elahi
 */
public interface Constants {

    public static String INPUT_LOCATION = "/Users/elahi/NetBeansProjects/new/final/TreeLinker/src/main/resources";
    public static String INPUT_JSON = "/qald/8/data/qald-8-test-multilingual.json";
    public static String INPUT_ALL_JSON = "/qald/all/";
    public static String INPUT_CSV = "entityLinking.tsv";
    public static String INPUT_TEXT = "questions.txt";

    public static String TEXT = "text";
    public static String TSV = "tsv";
    public static String JSON = "json";
    public static Set<String> inputTypes = new HashSet<String>(Arrays.asList(TEXT,TSV,JSON)); 
    
    
    public static String ALL = "all";
    public static String SEARCH = "search";

}
