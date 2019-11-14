/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.core.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;

/**
 *
 * @author elahi
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class URI {

    @JsonProperty("type")
    public String type;

    @JsonProperty("value")
    public String value;

    @Override
    public String toString() {
        return "URI{" + "type=" + type + ", value=" + value + '}';
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

}
