package com.example.bloodbank;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SearchBlood extends AppCompatActivity {

    private EditText city;
    private Spinner blood;
    private Button search;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_blood);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.red1));

        city = findViewById(R.id.citysearch);
        blood = findViewById(R.id.blooadsearch);
        search = findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = city.getText().toString().trim();
                String b = (String) blood.getSelectedItem();
                Intent intent = new Intent(getApplicationContext(),SearchResult.class);
                intent.putExtra("ci",c);
                intent.putExtra("b",b);
                startActivity(intent);
            }
        });


    }
}
