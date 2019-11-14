/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.core.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author elahi
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Answers {

    @JsonProperty("head")
    public HashMap<String, List> head = new HashMap<String, List>();

    @JsonProperty("results")
    public Result result;

    @Override
    public String toString() {
        return "Answers{" + "head=" + head + ", result=" + result + '}';
    }

    public HashMap<String, List> getHead() {
        return head;
    }

    public Result getResult() {
        return result;
    }

    public static String getAnswer(Answers answerUnit) {
        Result result = answerUnit.getResult();
        System.out.println("result:" + result);
        return null;
    }

}
