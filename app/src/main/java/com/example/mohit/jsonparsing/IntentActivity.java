package com.example.mohit.jsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        String itemname=getIntent().getExtras().getString("itemName");
        String itemprice=getIntent().getExtras().getString("itemPrice");

        Toast.makeText(this,"Price of "+itemname+" is "+itemprice,Toast.LENGTH_SHORT).show();


    }
}
