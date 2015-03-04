package ru.tech_mail.translator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import ru.tech_mail.translator.api.YandexApiImpl;
import ru.tech_mail.translator.fragments.ListLanguagesFragment;


public class TranslatorActivity extends ActionBarActivity {

    private Pair<String, String> sourceLang = new Pair<>("ru", "Русский");  // Default value
    private Pair<String, String> destLang = new Pair<>("en", "Английский"); // Destination Language
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);
        final Button sourceLangBtn = (Button) findViewById(R.id.source_language);
        sourceLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TranslatorActivity.this, ListLanguagesActivity.class);
                startActivity(i);
            }
        });
        sourceLangBtn.setText(sourceLang.second);
        
        final Button destLangBtn = (Button) findViewById(R.id.destinational_language);
        destLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TranslatorActivity.this, ListLanguagesActivity.class);
                startActivity(i);
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
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_translator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
