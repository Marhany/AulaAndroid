package com.aulamobile.aulamobile_06_08_18;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IGIService {

    @GET("users/(user)/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

}
