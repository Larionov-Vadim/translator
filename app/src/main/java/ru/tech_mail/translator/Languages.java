package ru.tech_mail.translator;

import java.util.HashMap;

/**
 * Created by vadim on 04.03.15.
 */
public class Languages {

    private static String[] languages;
    private static HashMap<String, String> LanguageCodeMap = new HashMap<>();
    private static HashMap<String, String> CodeLanguageMap = new HashMap<>();

    static {
        initLanguages();
        initLanguagesMaps();
    }

    private static void initLanguagesMaps() {
        LanguageCodeMap.put("Русский", "ru");
        CodeLanguageMap.put("ru", "Русский");
        LanguageCodeMap.put("English", "en");
        CodeLanguageMap.put("en", "English");
        LanguageCodeMap.put("French", "fr");
        CodeLanguageMap.put("fr", "French");
    }

    private static void initLanguages() {
        languages = new String[] {
                "Русский", "English", "French"
        };
    }

    public static String getKeyByLanguage(String language) {
        return LanguageCodeMap.get(language);
    }
    public static String getLanguageByKey(String key) {
        return CodeLanguageMap.get(key);
    }
    public static String getCodeLangByPosition(int position) {
        if (position >= languages.length)
            return null;
        String language = languages[position];
        return getKeyByLanguage(language);
    }
    public static String getLanguageByPosition(int position) {
        if (position >= languages.length)
            return null;
        return languages[position];
    }

    public static String[] getLanguages() {
        return languages;
    }
}
