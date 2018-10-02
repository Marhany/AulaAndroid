package com.aulamobile.aulamobile_06_08_18;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

        IGIService service = RetrofitUtil.build().create(IGIService.class);

        Call<List<Repo>> listCallBack = service.listRepos("giomodiogo");

        listCallBack.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> repos = response.body();
                setListView(repos);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }

    private void setListView(List<Repo> list) {
        for(Repo repo: list){
            Toast.makeText(getApplicationContext(), repo.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
