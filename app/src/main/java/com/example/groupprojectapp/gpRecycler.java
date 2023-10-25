package com.example.groupprojectapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class gpRecycler extends RecyclerView.Adapter<gpRecycler.ViewHolder> {

    Context context;
    ArrayList<GP> arrayList = new ArrayList<>();

    FloatingActionButton fab;

    public gpRecycler(Context context, ArrayList<GP> arrayList, FloatingActionButton fab) {
        this.context = context;
        this.arrayList = arrayList;
        this.fab = fab;
    }


    @NonNull
    @Override
    public gpRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_gp_card_view, parent, false);
        gpRecycler.ViewHolder viewHolder = new gpRecycler.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull gpRecycler.ViewHolder holder, int position) {

        // Bind common data
        holder.imageView1.setImageResource(arrayList.get(position).getImage());
        holder.textView2.setText(arrayList.get(position).getName());
        holder.textViewInfo.setText(arrayList.get(position).getInfo());


        // set a click listener on the image
        holder.cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Doctor" + position, Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(context, MainActivity2.class);
                Intent intent = new Intent(view.getContext(), MainActivity4.class);

                intent.putExtra("image", arrayList.get(position).getImage());
                intent.putExtra("name", arrayList.get(position).getName());
                intent.putExtra("info", arrayList.get(position).getInfo());

                context.startActivity(intent);

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle FAB click action here


                // Start a new activity
                Intent intent = new Intent(context, gp_details.class);
                context.startActivity(intent);

                // For example, display a Toast
                Toast.makeText(context, "FAB Clicked", Toast.LENGTH_LONG).show();
            }
        });

    }

        @Override
        public int getItemCount () {
            return arrayList.size();
        }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        TextView textView2, textViewInfo;
        CardView cardView1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView1 = itemView.findViewById(R.id.imageView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textViewInfo = itemView.findViewById(R.id.textViewInfo);
            cardView1 = itemView.findViewById(R.id.cardView1);

        }


    }





}

