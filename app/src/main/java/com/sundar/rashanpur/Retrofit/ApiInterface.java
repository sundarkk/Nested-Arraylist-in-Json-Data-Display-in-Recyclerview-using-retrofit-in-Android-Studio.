package com.sundar.rashanpur.Retrofit;


import com.sundar.rashanpur.Model.DtaClass;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api/gethomeData")
    Call<DtaClass> getData();


}
