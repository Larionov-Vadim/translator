package ru.tech_mail.translator.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import ru.tech_mail.translator.R;

/**
 * Created by Abovyan on 06.03.15.
 */
public class AboutActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        Button startBrowser = (Button) findViewById(R.id.linkAPI);
        startBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tech.yandex.ru/translate/"));
                startActivity(browserIntent);
            }
        });
    }
}
