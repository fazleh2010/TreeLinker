package com.citec.treeLinker.core.tree;


public class Result {

    String match;

    String uri;

    String type;

    public Result(String match, String uri, String type) {
        this.match = match;
        this.uri = uri;
        this.type = type;

    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Result [match=" + match + ", uri=" + uri + ", type=" + type
                + "]";
    }

}
