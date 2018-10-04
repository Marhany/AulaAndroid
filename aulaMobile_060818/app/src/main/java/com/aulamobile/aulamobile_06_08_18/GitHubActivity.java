package com.aulamobile.aulamobile_06_08_18;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

        listView = findViewById(R.id.listView);
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
        if (list != null){
            String[] values = new String[list.size()];
            for(int i = 0; i < list.size(); i++){
                values[i] = list.get(i).getName();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);

            listView.setAdapter(adapter);
        }

    }
}
