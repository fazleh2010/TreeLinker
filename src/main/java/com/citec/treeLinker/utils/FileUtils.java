/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citec.treeLinker.utils;

import com.citec.treeLinker.core.tree.Tupple;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 *
 * @author elahi
 */
public class FileUtils {
    
    public static void WriteToFile(List<Tupple> inputTupples, String fileName) throws IOException {
        String str="";
        for(Tupple tuple: inputTupples){
            String line=tuple.getEntry().replace(" ", "_")+"="+tuple.getUri()+"\n";
            str+=line;
            write(str, fileName);
        }

    }

    public  static void write(String str, String fileName)
            throws IOException {
        /*File file = new File(fileName);
        if (file.exists()) {
            file.deleteOnExit();
        }*/
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(str);
        writer.close();

    }
    
    public static File[] getFiles(String fileDir, String ntriple) throws Exception {

        File dir = new File(fileDir);
        FileFilter fileFilter = new WildcardFileFilter("*" + ntriple);
        File[] files = dir.listFiles(fileFilter);
        return files;

    }

}
