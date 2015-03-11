package ru.tech_mail.translator.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ru.tech_mail.translator.R;
import ru.tech_mail.translator.api.YandexApiImpl;


public class TranslatorActivity extends ActionBarActivity {

    private String sourceLang = "ru";    // Default value
    private String destLang = "en";      // Destination Language

    private static final int REQUEST_CODE_SOURCE_LANG = 100;
    private static final int REQUEST_CODE_DEST_LANG = 101;

    private Button sourceLangBtn;
    private Button destLangBtn;
    private Button translateBtn;

    private EditText originalText;
    private EditText translatedText;

    public void setTranslateResult(String res){
        this.translatedText.setText(res);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);

        final ArrayList<String> languagesArrayList = new ArrayList<>();
        languagesArrayList.add("ru");
        languagesArrayList.add("en");
        languagesArrayList.add("de");
        languagesArrayList.add("it");
        languagesArrayList.add("fr");

        sourceLangBtn = (Button) findViewById(R.id.source_language);
        sourceLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TranslatorActivity.this, ListLanguagesActivity.class);
                i.putStringArrayListExtra("languagesArrayList", languagesArrayList);
                startActivityForResult(i, REQUEST_CODE_SOURCE_LANG);
            }
        });
        sourceLangBtn.setText(sourceLang);

        destLangBtn = (Button) findViewById(R.id.destinational_language);
        destLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TranslatorActivity.this, ListLanguagesActivity.class);
                i.putStringArrayListExtra("languagesArrayList", languagesArrayList);
                startActivityForResult(i, REQUEST_CODE_DEST_LANG);
            }
        });
        destLangBtn.setText(destLang);

        Button swapLangBtn = (Button) findViewById(R.id.swap_language);
        swapLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = sourceLang;
                sourceLang = destLang;
                destLang = temp;

                sourceLangBtn.setText(sourceLang);
                destLangBtn.setText(destLang);
            }
        });

        translateBtn = (Button) findViewById(R.id.translate_button);
        translateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new TranslateTask().execute(originalText.getText().toString(), sourceLang, destLang);
            }
        });

        originalText = (EditText) findViewById(R.id.original_text);
        translatedText = (EditText) findViewById(R.id.translated_text);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;

        String lang = data.getStringExtra("language");
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_SOURCE_LANG) {
                sourceLang = lang;
                sourceLangBtn.setText(lang);
            }
            if (requestCode == REQUEST_CODE_DEST_LANG) {
                destLang = lang;
                destLangBtn.setText(lang);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_translator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_item:
                Intent i = new Intent(TranslatorActivity.this, AboutActivity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    // 0 - текст, 1 - язык с которого переводим, 2 - язык на который переводим
    public class TranslateTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... params) {
            YandexApiImpl api = new YandexApiImpl();
            return  api.translate(params[0],params[1],params[2]);
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(String result) {
            setTranslateResult(result);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("sourceLang", sourceLang);
        outState.putString("destLang", destLang);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        sourceLang = savedInstanceState.getString("sourceLang", "ru");
        destLang = savedInstanceState.getString("destLang", "en");
        sourceLangBtn.setText(sourceLang);
        destLangBtn.setText(destLang);
    }

}
