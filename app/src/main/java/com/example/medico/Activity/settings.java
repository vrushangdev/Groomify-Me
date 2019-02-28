package com.example.medico.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;


import com.example.medico.R;

public class settings extends AppCompatActivity{

    Spinner language;
    Locale myLocale;
    String currentLanguage, currentLang;
    Toolbar settingToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settingToolbar=findViewById(R.id.settingToolbar);
        setSupportActionBar(settingToolbar);
        getSupportActionBar().setTitle(R.string.setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        language=(Spinner)findViewById(R.id.language);

        ArrayAdapter<String> newadapter = new ArrayAdapter<String>(
                settings.this, R.layout.spinner_layout_test, getResources().getStringArray(R.array.lang));
        language.setAdapter(newadapter);

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                SharedPreferences.Editor editor = getSharedPreferences("pref", MODE_PRIVATE).edit();
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        currentLanguage = "en";
                        editor.putString("lang", "en");
                        editor.apply();


                        break;
                    case 2:
                        setLocale("hi");
                        currentLanguage = "hi";
                        editor.putString("lang", "hi");
                        editor.apply();

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    public void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, settings.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(settings.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }

}
