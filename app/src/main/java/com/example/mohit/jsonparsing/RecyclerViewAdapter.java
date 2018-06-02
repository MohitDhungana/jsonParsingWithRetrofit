package com.example.mohit.jsonparsing;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context context;
    List<MenuModelClass> menuModelClasses;

    public RecyclerViewAdapter(Context context, List<MenuModelClass> menuModelClasses) {
        this.context = context;
        this.menuModelClasses = menuModelClasses;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        final MenuModelClass menuModelClass=menuModelClasses.get(position);

        //getting the item price and displaying in txt_item_price

        holder.txt_item_price.setText(menuModelClass.getItem_Price());
        holder.txt_item_name.setText(menuModelClass.getItem_Name());
        holder.txt_item_desc.setText(menuModelClass.getItem_Desc_English());

        Glide.with(context).load("http://vedisapp.berlin-webdesign-agentur.de/Image/"+menuModelClass
                .getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.item_image);

        //click listener in recycler view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast .makeText(context,"you clicked "+menuModelClass.getItem_Name(),Toast.LENGTH_SHORT).show();
                System.out.println("ItemName "+menuModelClass.getItem_Name());

                Intent intent=new Intent(context,IntentActivity.class);
                intent.putExtra("itemName",menuModelClass.getItem_Name());
                intent.putExtra("itemPrice",menuModelClass.getItem_Price());
                context.startActivity(intent);
                //context.startActivity(new Intent(context,IntentActivity.class));
            }
        });



    }

    @Override
    public int getItemCount() {
        return menuModelClasses.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_item_name,txt_item_desc,txt_item_price;
        ImageView item_image;

        public MyViewHolder(View itemView) {
            super(itemView);

            txt_item_desc=itemView.findViewById(R.id.item_desc);
            txt_item_name=itemView.findViewById(R.id.item_name);
            txt_item_price=itemView.findViewById(R.id.item_price);
            item_image=itemView.findViewById(R.id.item_image);


        }
    }

}
