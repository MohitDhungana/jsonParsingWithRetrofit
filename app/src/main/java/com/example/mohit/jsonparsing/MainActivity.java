package com.example.mohit.jsonparsing;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        getMenuJson();

    }

    public void getMenuJson(){

        RetrofitApiInterface retrofitApiInterface=RetrofitClient.getRetrofit().create(RetrofitApiInterface.class);
        final Call<List<MenuModelClass>> modelClassCall=retrofitApiInterface.getMenu();
        modelClassCall.enqueue(new Callback<List<MenuModelClass>>() {
            @Override
            public void onResponse(Call<List<MenuModelClass>> call, Response<List<MenuModelClass>> response) {

                //first way
//                List<MenuModelClass> modelClasses=response.body();
//                RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,modelClasses);

                //second way
                RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,response.body());
//                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);

                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(horizontalLayoutManager);


                recyclerView.setHasFixedSize(true);
                //for recycler
                //recyclerView.setLayoutManager(layoutManager);

                recyclerView.setItemAnimator(new DefaultItemAnimator());
                //for recycler
                recyclerView.setAdapter(recyclerViewAdapter);

                //for recycler
                recyclerViewAdapter.notifyDataSetChanged();


                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<MenuModelClass>> call, Throwable throwable) {

            }
        });

    }
}
