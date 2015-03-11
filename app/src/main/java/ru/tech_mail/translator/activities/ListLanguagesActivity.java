package ru.tech_mail.translator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

import ru.tech_mail.translator.R;
import ru.tech_mail.translator.fragments.ListLanguagesFragment;

/**
 * Created by vadim on 02.03.15.
 */
public class ListLanguagesActivity extends FragmentActivity implements ListLanguagesFragment.OnItemSelectedListener {
    ArrayList<String> languagesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        languagesArrayList = getIntent().getStringArrayListExtra("languagesArrayList");
        setContentView(R.layout.languages_activity);
    }

    @Override
    public void onArticleSelected(int position) {
        Intent data = new Intent();
        data.putExtra("language", languagesArrayList.get(position));
        setResult(RESULT_OK, data);
        finish();
    }

    public String[] getLanguages() {
        int countLangs = languagesArrayList.size();
        String[] langs = new String[countLangs];
        for (int i = 0; i < countLangs; ++i) {
            langs[i] = languagesArrayList.get(i);
        }
        return langs;
    }
}
