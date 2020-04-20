package com.example.bloodbank;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main4Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView nevi;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private PrefManager prefManager;
    private AlertDialog alertDialog;
    private View header;
    private AlertDialog.Builder builder;
    private TextView drawerName,drawerEmail,pname,pcity,pcontact,pblood,pemail,ppass;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_drawer_layout);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.red1));

        prefManager = new PrefManager(getApplicationContext());
        nevi = findViewById(R.id.m);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nevi);
        header = navigationView.getHeaderView(0);
        drawerName = header.findViewById(R.id.hname);
        drawerEmail = header.findViewById(R.id.hemail);
        pname = findViewById(R.id.textView51);
        pcity = findViewById(R.id.pcity);
        pcontact = findViewById(R.id.textView17);
        pblood = findViewById(R.id.pblood);
        pemail = findViewById(R.id.textView15);
        ppass = findViewById(R.id.textView16);

        //Alert dialoge
        builder = new AlertDialog.Builder(Main4Activity.this);
        builder.setView(LayoutInflater.from(Main4Activity.this).inflate(R.layout.loading,null));
        builder.setCancelable(false);
        alertDialog = builder.create();

        drawerName.setText(prefManager.getUserName());
        drawerEmail.setText(prefManager.getUserEmail());
        pname.setText(prefManager.getUserName());
        pemail.setText(prefManager.getUserEmail());
        ppass.setText(prefManager.getUserPassword());
        pblood.setText(prefManager.getUserBlood());
        pcontact.setText(prefManager.getUserContact());
        pcity.setText(prefManager.getUserCity());

        nevi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.se:
                startActivity(new Intent(getApplicationContext(),SearchBlood.class));
                break;
            case R.id.pro:
                startActivity(new Intent(getApplicationContext(),Main4Activity.class));
                break;
            case R.id.tips:
                startActivity(new Intent(getApplicationContext(),Tips.class));
                break;
            case R.id.facts:
                startActivity(new Intent(getApplicationContext(),Facts.class));
                break;
            case R.id.com:
                startActivity(new Intent(getApplicationContext(),BloodCompatibility.class));
                break;
            case R.id.logout:
                prefManager.setLoggedIn(false);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                break;
            case R.id.delete:
                MyalertDialoge();
                break;
            case R.id.share:
                shareMyApp();
                break;
            case R.id.rate:
                rateMyApp();
                break;
        }
        return true;
    }

    private void rateMyApp() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + this.getPackageName())));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }

    private void deleteMyAccount() {
        Call<DeletePojo> call = RetrofitClint.getInstance().getApi().deleteMyAccount(prefManager.getUserEmail());
        call.enqueue(new Callback<DeletePojo>() {
            @Override
            public void onResponse(Call<DeletePojo> call, Response<DeletePojo> response) {
                DeletePojo pojo = response.body();
                if (Integer.parseInt(pojo.getResult())==1){
                    prefManager.setLoggedIn(false);
                    prefManager.setUserEmail(null);
                    prefManager.setUserContact(null);
                    prefManager.setUserBlood(null);
                    prefManager.setUserPassword(null);
                    prefManager.setUserCity(null);
                    prefManager.setUserName(null);
                    alertDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }else {
                    alertDialog.dismiss();
                    Toast.makeText(Main4Activity.this, "Error! ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeletePojo> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(Main4Activity.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void  shareMyApp(){
        Intent sharelink = new Intent(Intent.ACTION_SEND);
        sharelink.setType("text/*");
        sharelink.putExtra(Intent.EXTRA_SUBJECT,"Blood Bank App (Download it From play store)");
        sharelink.putExtra(Intent.EXTRA_TEXT,"http://play.google.com/store/apps/details?id=com.example.bloodbank");
        startActivity(Intent.createChooser(sharelink,"Share with friends via "));
    }

    private void MyalertDialoge(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(Main4Activity.this);
        builder.setCancelable(false);
        builder.setMessage("Are You sure to delete your account?");
        builder.setTitle("Blood Bank");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.show();
                deleteMyAccount();
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
