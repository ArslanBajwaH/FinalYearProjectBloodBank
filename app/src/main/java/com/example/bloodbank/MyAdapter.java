package com.example.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MYViewHolder> {

    private List<ResultData> allResults;
    private Context context;

    public MyAdapter(List<ResultData> allResults, Context context) {
        this.allResults = allResults;
        this.context = context;
    }

    @NonNull
    @Override
    public MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_item,parent,false);
        MYViewHolder holder = new MYViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MYViewHolder holder, int position) {
        final ResultData result = allResults.get(position);
        holder.name.setText(result.getName());
        holder.city.setText(result.getCity());
        holder.forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Main3Activity.class);
                intent.putExtra("n",result.getName());
                intent.putExtra("c",result.getCity());
                intent.putExtra("b",result.getBlood());
                intent.putExtra("con",result.getContact());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allResults.size();
    }

    public class MYViewHolder extends RecyclerView.ViewHolder {

        private TextView name,city;
        private ImageView forward;

        public MYViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foundname);
            city = itemView.findViewById(R.id.foundcity);
            forward = itemView.findViewById(R.id.farword);
        }
    }
}
