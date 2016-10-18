package com.example.my.practice_bbb7;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by My on 2016/10/17.
 */
public interface MyretrofitApi {
    @GET
    Call<ResponseBody>getHttp();



    @POST
    Call<ResponseBody>downLoad(@Url String url);

    @POST
    Call<ResponseBody>upLoad(@Url String url);


}
