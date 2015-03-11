package ru.tech_mail.translator.activities;

import org.apache.http.util.LangUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vadim on 04.03.15.
 */
public class Languages {

    private ArrayList<String> languages;

    public Languages() {
        languages = new ArrayList<>();
        languages.add("ru");
        languages.add("en");
        languages.add("fr");
    }

    public Languages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public ArrayList<String> getArrayListLanguages() {
        return languages;
    }

}
