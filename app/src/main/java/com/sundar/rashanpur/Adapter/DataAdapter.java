package com.sundar.rashanpur.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sundar.rashanpur.Model.TopItem;
import com.sundar.rashanpur.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private ArrayList<TopItem> dataModelList;
    private Context context;



    public DataAdapter(ArrayList<TopItem> dataModelList, Context context) {
        this.dataModelList = dataModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lists, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


       /* Picasso.with(context).load(dataModelList.get(position).getImage())
               .error(R.drawable.ic_launcher_background)
              .into(holder.imgproducts);*/
       if(!dataModelList.get(position).getImage().isEmpty()){
           Picasso.with(context)
                   .load(dataModelList.get(position).getImage())
                   .placeholder(R.drawable.ic_launcher_background) //this is optional the image to display while the url image is downloading
                   .error(R.drawable.ic_launcher_background)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                   .into(holder.Image_Product);
       }



        holder.Tv_Product_Name.setText(String.valueOf(dataModelList.get(position).getName()));
        holder.Tv_Quantity.setText(String.valueOf(dataModelList.get(position).getQuantity()));
        holder.Tv_Price.setText(String.valueOf(dataModelList.get(position).getPrice()));



    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Image_Product;
        TextView Tv_Product_Name,Tv_Quantity,Tv_Price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Image_Product = (ImageView) itemView.findViewById(R.id.Image_Product);
            Tv_Product_Name = (TextView) itemView.findViewById(R.id.Tv_Product_Name);
            Tv_Quantity = (TextView) itemView.findViewById(R.id.Tv_Quantity);
            Tv_Price = (TextView) itemView.findViewById(R.id.Tv_Price);


        }
    }
}

