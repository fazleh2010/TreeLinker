/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.utils;

/**
 *
 * @author elahi
 */
public class QuesAnsElement {
    private String question=null;
    private String answer=null;
    private String type=null;
    
    public QuesAnsElement(String question,String answer,String type){
        this.question=question;
        this.answer=answer;
        this.type=type;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "QuesAnsElement{" + "question=" + question + ", answer=" + answer + ", type=" + type + '}';
    }
    
}
