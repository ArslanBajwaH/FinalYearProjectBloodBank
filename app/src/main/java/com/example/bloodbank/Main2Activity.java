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
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    private EditText n,c,e,p,ci;
    private Spinner blood;
    private Button create;
    private PrefManager prefManager;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.red1));

        //Alert dialoge
        builder = new AlertDialog.Builder(Main2Activity.this);
        builder.setView(LayoutInflater.from(Main2Activity.this).inflate(R.layout.loading,null));
        builder.setCancelable(false);
        alertDialog = builder.create();


        n = findViewById(R.id.name);
        c = findViewById(R.id.contact);
        e = findViewById(R.id.emaillogin);
        p = findViewById(R.id.passlogin);
        ci = findViewById(R.id.city);
        blood = findViewById(R.id.bloodgroup);
        create = findViewById(R.id.create);
        prefManager = new PrefManager(getApplicationContext());

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
                final String name = n.getText().toString().trim();
                final String contact = c.getText().toString().trim();
                final String email = e.getText().toString().trim();
                final String pass = p.getText().toString().trim();
                final String city = ci.getText().toString().trim();
                final String blooadGroup = (String) blood.getSelectedItem();

                if (name.isEmpty()){
                    n.setError("Please Enter Name");
                    return;
                }

                if (contact.isEmpty()){
                    c.setError("Please Enter Contect Number");
                    return;
                }

                if (email.isEmpty()){
                    e.setError("Please Enter Email Address");
                    return;
                }

                if (pass.isEmpty()){
                    p.setError("Please Enter Password");
                    return;
                }

                if (city.isEmpty()){
                    ci.setError("Please Enter City");
                    return;
                }

                if (blooadGroup.contains("choose")){
                    return;
                }


                Call<Pojo> call = RetrofitClint.getInstance().getApi().register(name,contact,pass,email,city,blooadGroup);
                call.enqueue(new Callback<Pojo>() {
                    @Override
                    public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                        Pojo res = response.body();
                        if (Integer.parseInt(res.getResult())==1){
                            prefManager.setUserBlood(blooadGroup);
                            prefManager.setUserContact(contact);
                            prefManager.setUserCity(city);
                            prefManager.setUserEmail(email);
                            prefManager.setUserPassword(pass);
                            prefManager.setUserName(name);
                            prefManager.setLoggedIn(true);
                            Intent intent = new Intent(getApplicationContext(),Main4Activity.class);
                            alertDialog.dismiss();
                            startActivity(intent);
                            finish();
                        }else if (Integer.parseInt(res.getResult())==2){
                            alertDialog.dismiss();
                            Toast.makeText(Main2Activity.this, "Email is already exist", Toast.LENGTH_SHORT).show();
                        }else {
                            alertDialog.dismiss();
                            Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Pojo> call, Throwable t) {
                        alertDialog.dismiss();
                        Toast.makeText(Main2Activity.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
