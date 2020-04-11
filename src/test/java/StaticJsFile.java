
import static com.citec.treeLinker.api.Constants.TEXT;
import com.citec.treeLinker.core.tree.CreateTree;
import com.citec.treeLinker.utils.FileRelatedUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elahi
 */
public class StaticJsFile {

    public static Set<String> browserSet;
    private static Set<String> lang = new TreeSet<String>();
    private static String BASE_PATH = "src/main/resources/html/";
    private String javaScriptTemp = "autoComp.js";
    private String tempFile = "temp.txt";

    @Test
    public void testAutoCompletion() throws IOException, Exception {
        String inputType = TEXT;
        CreateTree createTree = new CreateTree(inputType);
        String content = FileRelatedUtils.output(createTree.getInputTupples());
        FileRelatedUtils.writeFile(content, BASE_PATH+tempFile);
        Map<String, String> allkeysValues = FileRelatedUtils.getHash(BASE_PATH+tempFile);
        String str = this.getTerms(allkeysValues);
        File templateFile = new File(BASE_PATH + javaScriptTemp);
        System.out.println(str);
        String outputFileName = BASE_PATH + "js/" + "ques.js";
        this.createAutoCompletionTemplate(templateFile, str, outputFileName);

    }

    private void createAutoCompletionTemplate(File templatefileName, String str, String outputFile) throws FileNotFoundException, IOException {
        InputStream input = new FileInputStream(templatefileName);
        String line = IOUtils.toString(input, "UTF-8");
        str += line + "\n";
        FileRelatedUtils.stringToFile_DeleteIf_Exists(str, outputFile);
    }

    private String getTerms(Map<String, String> allKeysValues) throws IOException {
        String str = "window.termUrls = new Map();" + "\n";
        str += "";
        List<String> termList = new ArrayList<String>(allKeysValues.keySet());
        Collections.sort(termList);

        for (int i = 0; i < termList.size(); i++) {
            String term = termList.get(i);
            //This is recently changed when writing test. Check whether it works fine when running from main.
            //term = term.trim();
            String value = allKeysValues.get(term);
            term = quote(term);
            if (i == termList.size()) {
                str += "\"" + term + "\"" + "];" + "\n";
            } else {
                str += "termUrls.set(" + "\"" + term + "\"" + "," + "\"" + value + "\"" + ");" + "\n";
            }
        }
        str += "\n";

        return str;
    }

    private String quote(String text) {
        if (text.contains("\"")) {
            return text.replaceAll("\"", "");
        }
        if (text.contains("\'")) {
            return text.replaceAll("\'", "");
        }
        if (text.contains("\\[")) {
            return text.replaceAll("[", "");
        }
        if (text.contains("\\]")) {
            return text.replaceAll("]", "");
        }
        if (text.contains("\\,")) {
            return text.replaceAll(",", "");
        }

        return text;
    }

}
