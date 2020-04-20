package com.example.bloodbank;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button login,create;
    private EditText e,p;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    private PrefManager prefManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(getApplicationContext());
        if (prefManager.getLoggedin()){
            startActivity(new Intent(getApplicationContext(),Main4Activity.class));
            finish();
        }
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.red1));

        create = findViewById(R.id.createone);
        login = findViewById(R.id.login);
        e = findViewById(R.id.emaillogin);
        p = findViewById(R.id.passlogin);

        //Alert dialoge
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(LayoutInflater.from(MainActivity.this).inflate(R.layout.loading,null));
        builder.setCancelable(false);
        alertDialog = builder.create();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
                String email = e.getText().toString().trim();
                String password = p.getText().toString().trim();
                if (email.isEmpty()){
                    e.setError("Please Enter email");
                    return;
                }

                if (password.isEmpty()){
                    p.setError("Please Enter Password");
                    return;
                }
                Call<Pojo> call = RetrofitClint.getInstance().getApi().login(email,password);
                call.enqueue(new Callback<Pojo>() {
                    @Override
                    public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                        Pojo pojo = response.body();
                        if (Integer.parseInt(pojo.getResult())==1){
                            prefManager.setUserName(pojo.getName());
                            prefManager.setUserContact(pojo.getContact());
                            prefManager.setUserCity(pojo.getCity());
                            prefManager.setUserEmail(pojo.getEmail());
                            prefManager.setUserPassword(pojo.getPass());
                            prefManager.setUserBlood(pojo.getBlood());
                            prefManager.setLoggedIn(true);
                            alertDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(),Main4Activity.class));
                            finish();
                        }else {
                            alertDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Pojo> call, Throwable t) {
                        alertDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
