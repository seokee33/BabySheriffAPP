package com.sheriffs.babysheriff.view.notification;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sheriffs.babysheriff.R;

public class Baby_info extends AppCompatActivity {

    Button btn_babyinfo_back;
    ImageButton btn_profile_plus;
    de.hdodenhof.circleimageview.CircleImageView iv_Baby_Profile;
    private final int GET_GALLERY_IMAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_info);

        iv_Baby_Profile = findViewById(R.id.iv_Baby_Profile_home);
        btn_babyinfo_back = findViewById(R.id.btn_babyinfo_back);
        btn_babyinfo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_profile_plus = findViewById(R.id.btn_profile_plus);
        btn_profile_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Intent.ACTION_PICK);
               intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
               startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri selectedLmageUri = data.getData();
            iv_Baby_Profile.setImageURI(selectedLmageUri);
        }
    }
}