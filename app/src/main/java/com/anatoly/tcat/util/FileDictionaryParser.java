package com.anatoly.tcat.util;

import com.anatoly.tcat.structure.Dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileDictionaryParser {
    public static Dictionary parse(File file) {
        Dictionary dictionary = new Dictionary();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                dictionary.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dictionary;
    }
}
