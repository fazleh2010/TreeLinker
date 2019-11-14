package com.citec.treeLinker.core.tree;
import com.citec.treeLinker.core.tree.Result;
import java.util.ArrayList;
import java.util.List;

public class TreeLexicon {

    TreeLexiconNode Root;

    public TreeLexicon() {
        Root = new TreeLexiconNode();
    }

    public void insert(String entry, String uri, String type) {
        String[] tokenized_entry = entry.split("\\s+");
        Root.insert(entry, tokenized_entry, uri, type, 0);
    }

    public List<Result> lookup(String candidate) {
        ArrayList<Result> list = new ArrayList<Result>();

        String[] tokenized_array = candidate.split("\\s+");

        for (int i = 0; i < tokenized_array.length; i++) {
            list.addAll(Root.lookup(tokenized_array, i));
        }

        return list;
    }

    public List<Result> lookup(String candidate, double threshold) {
        ArrayList<Result> list = new ArrayList<Result>();

        String[] tokenized_array = candidate.split("\\s+");

        for (int i = 0; i < tokenized_array.length; i++) {
            List<Result> results=Root.lookup(tokenized_array, i, threshold);
            for (Result result: results){
                 System.out.println("...............");
                System.out.println(result.toString());
            }
            list.addAll(results);
        }

        return list;
    }

}
