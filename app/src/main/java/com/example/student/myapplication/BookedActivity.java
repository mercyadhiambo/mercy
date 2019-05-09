package com.example.student.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BookedActivity extends AppCompatActivity {
    ImageView img;
    TextView houseNumber;
    TextView houseName;
    TextView price;
    TextView no;
    Button btn;
    DatabaseReference ref;
    StorageReference sRef;
    String rand;
    Uri uri = null;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);
        img = findViewById(R.id.imageView2);
        houseNumber = findViewById(R.id.editText2);
        houseName = findViewById(R.id.editText3);
        price = findViewById(R.id.editText4);
        no = findViewById(R.id.editText5);
        btn = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar2);
        sRef = FirebaseStorage.getInstance().getReference();
        ref = FirebaseDatabase.getInstance().getReference();
        rand = UUID.randomUUID().toString();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select image"), 1);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String number = houseNumber.getText().toString();
                final String prices = price.getText().toString();
                final String name = houseName.getText().toString();
                String nos = no.getText().toString();

                if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(prices) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(nos) && uri != null) {
                    progressBar.setVisibility(View.VISIBLE);
                    StorageReference reference = sRef.child("Uploads").child(rand + ".jpg");
                    reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String dUrl = taskSnapshot.getDownloadUrl().toString();
                            Map map = new HashMap();
                            map.put("houseImageUrl", dUrl);
                            map.put("houseNumber", number);
                            map.put("houseContactName", name);
                            map.put("housePrice", prices);

                            ref.child("Houses").push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(BookedActivity.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                                    Intent mainInt = new Intent(BookedActivity.this, MainActivity.class);
                                    startActivity(mainInt);
                                    finish();
                                }
                            });
                        }
                    });
                    price.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(BookedActivity.this, "Please fill out the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            uri = data.getData();
            Glide.with(BookedActivity.this).load(uri).into(img);
        }
    }
}

