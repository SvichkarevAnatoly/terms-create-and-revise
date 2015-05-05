package com.anatoly.tcat.structure;

public class DictEntry {
    private String term;
    private String definition;

    public static DictEntry parse(String termDefinition){
        final String[] split = termDefinition.split("--");
        if (split.length != 2){
            throw new IllegalArgumentException("Term definition must contain '--'");
        }

        final String term = split[0].trim();
        final String definition = split[1].trim();

        return new DictEntry(term, definition);
    }

    public DictEntry(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    @Override
    public String toString() {
        return term + " -- " + definition;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }
}
