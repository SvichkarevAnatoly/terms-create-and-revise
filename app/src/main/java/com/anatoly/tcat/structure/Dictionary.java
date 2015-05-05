package com.anatoly.tcat.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dictionary implements Iterable {
    private List<DictEntry> dictionary;

    public Dictionary() {
        dictionary = new ArrayList<>();
    }

    public Dictionary(String[] termDefinitions) {
        dictionary = new ArrayList<>();
        for (String termDefinition : termDefinitions) {
            add(termDefinition);
        }
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

    @Override
    public Iterator iterator() {
        return dictionary.iterator();
    }
}
