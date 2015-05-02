package com.example.anatoly.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<DictEntry> dictionary;

    public Dictionary(List<DictEntry> dictionary) {
        this.dictionary = dictionary;
    }

    public Dictionary() {
        dictionary = new ArrayList<>();
    }

    public void add(DictEntry dictEntry) {
        dictionary.add(dictEntry);
    }

    public void add(String dictEntryStr) {
        final DictEntry dictEntry = DictEntry.parse(dictEntryStr);
        dictionary.add(dictEntry);
    }

    public String[] toStringArray() {
        final String[] terms = new String[dictionary.size()];

        int i = 0;
        for (DictEntry dictEntry : dictionary) {
            terms[i++] = dictEntry.toString();
        }

        return terms;
    }
}
