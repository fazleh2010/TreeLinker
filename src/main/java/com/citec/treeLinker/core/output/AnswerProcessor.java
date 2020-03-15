/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.core.output;

import com.citec.treeLinker.core.input.Answers;
import com.citec.treeLinker.core.input.Binding;
import com.citec.treeLinker.core.input.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author elahi
 */
public class AnswerProcessor {

    private String uri = null;
    private List<Answers> answerUnits=new ArrayList<Answers>();
    private HashMap<String, String> sparql=new HashMap<String, String>();


    public AnswerProcessor(List<Answers> answerUnits, HashMap<String, String> sparql) throws Exception {
            this.answerUnits=answerUnits;
            this.sparql=sparql;
    }

    public List<String> getAnswer() {
        List<String> uriList = new ArrayList<String>();

        for (Answers answerUnit : answerUnits) {
            Result result = answerUnit.getResult();
            List<Binding> bindings = result.getBinding();
            for (Binding binding : bindings) {
                HashMap<String, String> uris = binding.getUri();
                if (!uris.isEmpty()) {
                    if (uris.containsKey("value")) {
                        uriList.add(uris.get("value"));
                    }
                }
            }

        }
        return uriList;
    }

    public String getUriListFirst() {
        List<String> uriList = getAnswer();
        if (!uriList.isEmpty()) {
            return uriList.iterator().next();
        } else {
            return sparql.get("sparql");
        }

    }

    public boolean getUriListFirstUrl() {
        List<String> uriList = getAnswer();
        if (uriList.isEmpty()) {
            return false;
        } else {
            uri = uriList.iterator().next();
            return true;
        }

    }

    public String getUri() {
        return uri;
    }

}
