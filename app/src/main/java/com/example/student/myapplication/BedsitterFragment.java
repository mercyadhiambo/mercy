package com.example.student.myapplication;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.student.myapplication.adapters.BedsitterRecylerAdapter;
import com.example.student.myapplication.dataModels.HouseModels;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BedsitterFragment extends Fragment {
    private FirebaseAuth firebaseAuth;

    int[] hostelImage = {R.drawable.bed1,R.drawable.bed2,R.drawable.bed3,R.drawable.bed4,R.drawable.bed5,R.drawable.bed6};
    String[] hostelNumber = {"B1", "B2", "B3", "B4", "B5", "B6"};
    String[] hostelContactName = {"James Niroh", "Peter Jacobs", "Sia and family", "Mourine and sons", "Angela Wekesa", "Pauline Kambi"};
    String[] hostelContactNumber = {"0798745214", "0723135678", "0713278542", "0789123786", "0701400781", "0789100113"};
    String[] hostelPrice = {"9,000", "6,000","7,000","10,000","8,000","9,000"};


    RecyclerView mRecyclerView;
    BedsitterRecylerAdapter mRecyclerAdapter;
    List<HouseModels> latestList;
    Context mContext;

    public BedsitterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bedsitter, container, false);
        firebaseAuth = FirebaseAuth.getInstance();

        mRecyclerView = view.findViewById(R.id.bedsitterRecycler);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        latestList = new ArrayList<>();

        getLatestHouses();
        return view;
    }



    private void getLatestHouses() {

        for (int i = 0; i<hostelImage.length ; i++){

            HouseModels houseModels = new HouseModels(
                    hostelImage[i],
                    hostelNumber[i],
                    hostelContactName[i],
                    hostelContactNumber[i],
                    hostelPrice[i]
            );
            latestList.add(houseModels);
        }
        mRecyclerAdapter = new BedsitterRecylerAdapter(mContext,latestList);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser mUser = firebaseAuth.getCurrentUser();
        if(mUser == null){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
           getActivity().finish();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        FirebaseUser mUser = firebaseAuth.getCurrentUser();
        if(mUser == null){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }
}
