package com.example.bloodbank;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private String name, city, contact, blood;
    private TextView na, ci, bl;
    private Button call;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.red1));

        na = findViewById(R.id.textView5);
        bl = findViewById(R.id.pb);
        ci = findViewById(R.id.pc);
        call = findViewById(R.id.call);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("n");
        city = bundle.getString("c");
        contact = bundle.getString("con");
        blood = bundle.getString("b");

        na.setText(name);
        ci.setText(city);
        bl.setText(blood);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + contact));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Main3Activity.this, "Please give permission first", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(intent);
            }
        });



    }
}
