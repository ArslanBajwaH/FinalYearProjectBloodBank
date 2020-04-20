package com.example.bloodbank;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResult extends AppCompatActivity {

    private String city,blood;
    private ProgressBar progressBar;
    private TextView textView;
    private RecyclerView recyclerView;
    private List<ResultData> data;
    private ImageView refresh;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.red1));

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycler);
        textView = findViewById(R.id.sorry);
        refresh = findViewById(R.id.refresh);
        data = new ArrayList<>();

        progressBar.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        city = bundle.getString("ci");
        blood = bundle.getString("b");
        searchResult();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                searchResult();
            }
        });
    }

    private void searchResult(){
        Call<List<ResultData>> call = RetrofitClint.getInstance().getApi().getResults(city,blood);
        call.enqueue(new Callback<List<ResultData>>() {
            @Override
            public void onResponse(Call<List<ResultData>> call, Response<List<ResultData>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                data = response.body();
                if (Integer.parseInt(data.get(0).getResult())==0){
                    textView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                }else {
                    textView.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.hasFixedSize();
                    MyAdapter adapter = new MyAdapter(data,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<ResultData>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                refresh.setVisibility(View.VISIBLE);
                Toast.makeText(SearchResult.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
