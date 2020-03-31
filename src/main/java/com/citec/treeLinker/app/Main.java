package com.citec.treeLinker.app;

import com.citec.treeLinker.api.Constants;
import com.citec.treeLinker.core.tree.CreateTree;
import com.citec.treeLinker.utils.FileUtils;
import java.io.IOException;

public class Main implements Constants {

    public static void main(String[] args) throws IOException, Exception {
        String inputType = TEXT;
        String searchtype = ALL;
        String content = "";
                    
        System.out.println("Test..");

        CreateTree createTree = new CreateTree(inputType);
        content = FileUtils.output(createTree.getInputTupples());
        
         System.out.println(content);

        /*if (searchtype.contains(ALL)) {
            System.out.println(content);
        }*/
         /*if (searchtype.contains(SEARCH)) {
            createTree.checkResultWhenTextFile();
            /*List<ResultQA> results = createTree.getResults("Gabriel Frlmtheater");
            String str = "";
            for (ResultQA result : results) {
                String line = result.getMatch() + "\n";
                str += line;
            }
            //System.out.println(str);

        }*/

        /*List<ResultQA>results=createTree.getResults("what regulations exist with respect to probationary period?");
         String str="";
         for (ResultQA result : results) {
             String line=result.getMatch()+"\n";
             str+=line;
        }
         System.out.println(str);
         */
        /////// important codes..
        //createTree = new CreateTree(INPUT_LOCATION+ File.separator +INPUT_ALL_JSON,1);
        // If text file then
        //createTree = new CreateTree(INPUT_LOCATION + File.separator + INPUT_TEXT,2);
    }

}
