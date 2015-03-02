package ru.tech_mail.translator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import ru.tech_mail.translator.fragments.ListLanguagesFragment;

/**
 * Created by vadim on 02.03.15.
 */
public class ListLanguagesActivity extends FragmentActivity implements ListLanguagesFragment.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.languages_activity);
    }

    @Override
    public void onArticleSelected(int position) {
        Log.d("LALALA", "MSGGGGG");
    }


}
