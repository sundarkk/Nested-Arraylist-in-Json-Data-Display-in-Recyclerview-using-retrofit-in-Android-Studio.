package com.sundar.rashanpur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sundar.rashanpur.Adapter.DataAdapter;
import com.sundar.rashanpur.Model.DtaClass;
import com.sundar.rashanpur.Model.TopItem;
import com.sundar.rashanpur.Retrofit.ApiInterface;
import com.sundar.rashanpur.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //All Data List Container
    DtaClass dtaClass = new DtaClass();

    private ProgressDialog progressDialog;
    private ArrayList<DtaClass> data;
    private ArrayList<TopItem> topItems;
    private DataAdapter adapter;
    private RecyclerView recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        generateData();
    }


    private void generateData() {

        loadJson();
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);  //Size fixed
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

    }

    private void loadJson() {


        ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);

     //   Call<DtaClass> call = apiInterface.getData();
           Call<DtaClass> call = apiInterface.getData();


        call.enqueue(new Callback<DtaClass>() {
            @Override
            public void onResponse(Call<DtaClass> call, Response<DtaClass> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    Log.e("TAG", "==success==>" + response.body());
                    //  ArrayList<Banner> banners =  new ArrayList<>();

                    //  DtaClass dtaClass = new DtaClass();  // Globallu sign.

                    dtaClass = response.body();
                    List<TopItem> topItems =  new ArrayList<>();
                    topItems = dtaClass.getData().getTopItems();

                    //  banners = (ArrayList<Banner>) response.body().getBanner();

                    Log.d("Sunder",  topItems.toString());

                    adapter = new DataAdapter((ArrayList<TopItem>) topItems, MainActivity.this);
                    recyclerview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<DtaClass> call, Throwable t) {
                Log.e("TAG", "==failure==>" + t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "went wrong !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}