package com.citec.treeLinker.app;

import com.citec.treeLinker.api.Constants;
import com.citec.treeLinker.core.tree.CreateTree;
import com.citec.treeLinker.core.tree.ResultQA;
import com.citec.treeLinker.core.tree.Tupple;
import com.citec.treeLinker.utils.FileUtils;
import java.io.IOException;
import java.util.List;

public class Test implements Constants {

    //private static String INPUT_LOCATION = "src/main/resources";
    public static void main(String[] args) throws IOException, Exception {
        String inputType = TEXT;
        String searchtype = ALL;
        String content = "";
        /*if(args.length<2){
           inputType=args[0];
           if(inputTypes.contains(inputType)){
               
           }
               else
                throw new Exception("The input type is wrong!!");
               
           searchtype=args[1];
        }
        else
            throw new Exception("the number of parameters are wrong!!");*/

        CreateTree createTree = new CreateTree(inputType);
        content = FileUtils.output(createTree.getInputTupples());
        
        if (searchtype.contains(ALL)) {
            System.out.println(content);
        }
        /*if (searchtype.contains("search")) {
            createTree.checkResultWhenTextFile();
            List<ResultQA> results = createTree.getResults("What");
            String str = "";
            for (ResultQA result : results) {
                String line = result.getMatch() + "\n";
                str += line;
            }
            System.out.println(str);

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
