package com.anatoly.tcat.structure;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dictionary implements Iterable, Parcelable {
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

    public Dictionary(Parcel parcel) {
        this.dictionary = parcel.readArrayList(DictEntry.class.getClassLoader());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(dictionary);
    }

    public static Creator<Dictionary> CREATOR = new Creator<Dictionary>() {
        @Override
        public Dictionary createFromParcel(Parcel source) {
            return new Dictionary(source);
        }

        @Override
        public Dictionary[] newArray(int size) {
            return new Dictionary[size];
        }

    };
}
