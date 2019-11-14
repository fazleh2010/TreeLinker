/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.core.input;

import com.citec.treeLinker.core.input.DataSet;
import com.citec.treeLinker.constant.Constants;
import static com.citec.treeLinker.constant.Constants.UNICODE;
import com.citec.treeLinker.api.InformationFinder;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author elahi
 */
public class JsonReader implements Constants {

    private final List<Questions> questions;

    public JsonReader(InputStream inputStream) {
        this.questions = parseJson(inputStream);
    }

    private List<Questions> parseJson(InputStream inputStream) {
        DataSet dataSet = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = IOUtils.toString(inputStream, UNICODE);
            dataSet = objectMapper.readValue(jsonString, DataSet.class);
        } catch (IOException ex) {
            Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataSet.getQuestions();
    }

    public List<Questions> getQuestions() {
        return questions;
    }

}
