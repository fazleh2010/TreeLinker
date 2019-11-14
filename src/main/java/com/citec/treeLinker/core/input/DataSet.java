/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.core.input;

import com.citec.treeLinker.core.input.Questions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author elahi
 */
public class DataSet {

    @JsonProperty("dataset")
    public HashMap<String, String> dataset = new HashMap<String, String>();
    @JsonProperty("questions")
    public List<Questions> questions = new ArrayList<Questions>();

    @Override
    public String toString() {
        return "Questions{" + "dataset=" + dataset + ", QuestionData=" + questions + '}';
    }

    public HashMap<String, String> getDataset() {
        return dataset;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

}
