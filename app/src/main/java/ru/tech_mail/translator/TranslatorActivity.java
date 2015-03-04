package ru.tech_mail.translator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TranslatorActivity extends Activity {

    private Pair<String, String> sourceLang = new Pair<>("ru", "Русский");  // Default value
    private Pair<String, String> destLang = new Pair<>("en", "English");    // Destination Language

    private static final int REQUEST_CODE_SOURCE_LANG = 100;
    private static final int REQUEST_CODE_DEST_LANG = 101;

    private Button sourceLangBtn;
    private Button destLangBtn;

    private EditText originalText;
    private EditText translatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);

        String msg = "Source: " + sourceLang.first + " " + sourceLang.second;
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();

        sourceLangBtn = (Button) findViewById(R.id.source_language);
        sourceLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TranslatorActivity.this, ListLanguagesActivity.class);
                startActivityForResult(i, REQUEST_CODE_SOURCE_LANG);
            }
        });
        sourceLangBtn.setText(sourceLang.second);
        
        destLangBtn = (Button) findViewById(R.id.destinational_language);
        destLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TranslatorActivity.this, ListLanguagesActivity.class);
                startActivityForResult(i, REQUEST_CODE_DEST_LANG);
            }
        });
        destLangBtn.setText(destLang.second);

        Button swapLangBtn = (Button) findViewById(R.id.swap_language);
        swapLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence buf = sourceLangBtn.getText();
                sourceLangBtn.setText(destLangBtn.getText());
                destLangBtn.setText(buf);

                Pair<String, String> temp = sourceLang;
                sourceLang = destLang;
                destLang = temp;
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
        String langCode = data.getStringExtra("language_code");

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_SOURCE_LANG) {
                sourceLang = new Pair<>(lang, langCode);
                sourceLangBtn.setText(lang);
            }
            if (requestCode == REQUEST_CODE_DEST_LANG) {
                destLang = new Pair<>(lang, langCode);
                destLangBtn.setText(lang);
            }
        }
    }

}
