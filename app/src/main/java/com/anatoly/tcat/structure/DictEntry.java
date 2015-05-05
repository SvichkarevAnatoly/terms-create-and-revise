package com.anatoly.tcat.structure;

import android.os.Parcel;
import android.os.Parcelable;

public class DictEntry implements Parcelable {
    private String term;
    private String definition;

    public DictEntry(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public DictEntry(Parcel parcel) {
        this.term = parcel.readString();
        this.definition = parcel.readString();
    }

    public static DictEntry parse(String termDefinition){
        final String[] split = termDefinition.split("--");
        if (split.length != 2){
            throw new IllegalArgumentException("Term definition must contains '--'");
        }

        final String term = split[0].trim();
        final String definition = split[1].trim();

        return new DictEntry(term, definition);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(term);
        dest.writeString(definition);
    }

    public static Creator<DictEntry> CREATOR = new Creator<DictEntry>() {
        @Override
        public DictEntry createFromParcel(Parcel source) {
            return new DictEntry(source);
        }

        @Override
        public DictEntry[] newArray(int size) {
            return new DictEntry[size];
        }

    };
}
