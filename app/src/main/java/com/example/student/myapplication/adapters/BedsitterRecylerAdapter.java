package com.example.student.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.student.myapplication.R;
import com.example.student.myapplication.dataModels.HouseModels;

import java.util.List;

public class BedsitterRecylerAdapter  extends RecyclerView.Adapter<BedsitterRecylerAdapter.ViewHolder> {

    Context context;
    List<HouseModels> mHouseList;

    public BedsitterRecylerAdapter(List<HouseModels> mHouseList) {
        // this.mContext = mContext;
        this.mHouseList = mHouseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_house, viewGroup, false);
        context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        HouseModels latestHouses = mHouseList.get(i);

        final int houseImageUrl = latestHouses.getHouseImageUrl();
        String houseNumber = latestHouses.getHouseNumber();
        String houseContactName = latestHouses.getHouseContactName();
        final String houseContactNumber = latestHouses.getHouseContactNumber();
        final String housePrice = latestHouses.getHousePrice();

        Glide.with(context)
                .load(houseImageUrl)
                .into(viewHolder.mHouseImage);
        viewHolder.mHouseNumber.setText(String.format("House Number: %s", houseNumber));
        viewHolder.mHousePrice.setText(String.format("Price:  Ksh %s", housePrice));
        viewHolder.mHouseContact.setText(String.format("%s %s", "Contact: " , houseContactNumber));
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Booked Successfully", Toast.LENGTH_SHORT).show();
                int pos = viewHolder.getAdapterPosition();
                mHouseList.remove(pos);
                notifyItemRemoved(pos);
                notifyItemRangeRemoved(pos, mHouseList.size());
            }
        });


    }

    @Override
    public int getItemCount() {
        return mHouseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mHouseImage;
        TextView mHouseNumber;
        TextView mHousePrice;
        TextView mHouseContact;
        Button btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mHouseImage = itemView.findViewById(R.id.houseImage);
            mHouseNumber = itemView.findViewById(R.id.houseNumber);
            mHousePrice = itemView.findViewById(R.id.housePrice);
            mHouseContact = itemView.findViewById(R.id.houseContact);
            btn = itemView.findViewById(R.id.buttonBook);
        }
    }
}
