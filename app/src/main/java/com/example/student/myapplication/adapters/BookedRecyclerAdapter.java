package com.example.student.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.student.myapplication.dataModels.HouseModels;

import java.util.List;

public class BookedRecyclerAdapter extends RecyclerView.Adapter<BookedRecyclerAdapter.ViewHolder> {
    List<HouseModels> r_list;

    public BookedRecyclerAdapter(List<HouseModels> r_list) {
        this.r_list = r_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return r_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
