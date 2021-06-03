package com.prankit.qubi7.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.prankit.qubi7.API;
import com.prankit.qubi7.ShowUserAdapter;
import com.prankit.qubi7.model.CreateUserModel;
import com.prankit.qubi7.model.GetUserModel;
import com.prankit.qubi7.R;
import com.prankit.qubi7.model.UpdateUserModel;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "https://reqres.in/";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Qubi 7 Assignment");

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        getUsers();
    }

    public void getUsers() {
        ((API) retrofit.create(API.class)).getAllUsers().enqueue(new Callback<GetUserModel>() {
            @Override
            public void onResponse(Call<GetUserModel> call, Response<GetUserModel> response) {
                if (response.isSuccessful()) {
                    adapter = new ShowUserAdapter(MainActivity.this, response.body().getData());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(layoutManager);
                } else Log.i("getDataError", response.message());
            }

            @Override
            public void onFailure(Call<GetUserModel> call, Throwable t) {
                Log.i("getDatFail", t.getMessage());
            }
        });
    }

    public void createUser(String name, String job) {
        RequestBody nameBody = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody jobBody = RequestBody.create(MediaType.parse("multipart/form-data"), job);
        ((API) retrofit.create(API.class)).createUser(nameBody, jobBody).enqueue(new Callback<CreateUserModel>() {
            @Override
            public void onResponse(Call<CreateUserModel> call, Response<CreateUserModel> response) {
                if (response.isSuccessful()) {
                    Log.i("createUser", response.body().getCreateAt() + " " + response.body().getId());
                } else Log.i("createUserError", response.message());
            }

            @Override
            public void onFailure(Call<CreateUserModel> call, Throwable t) {
                Log.i("createUserFail", t.getMessage());
            }
        });
    }

    public void updateUser(String name, String job) {
        RequestBody nameBody = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody jobBody = RequestBody.create(MediaType.parse("multipart/form-data"), job);
        retrofit.create(API.class).updateUser(nameBody, jobBody).enqueue(new Callback<UpdateUserModel>() {
            @Override
            public void onResponse(Call<UpdateUserModel> call, Response<UpdateUserModel> response) {
                if (response.isSuccessful()) {
                    Log.i("updateUser", response.body().getName() + " " + response.body().getJob()
                            + " " + response.body().getUpdatedAt());
                } else Log.i("updateUserError", response.message());
            }

            @Override
            public void onFailure(Call<UpdateUserModel> call, Throwable t) {
                Log.i("UpdateUserFail", t.getMessage());
            }
        });
    }

    public void deleteUser() {
        retrofit.create(API.class).deleteUser().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) Log.i("deleteUser", response.message() + "");
                else Log.i("deleteUserError", response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("deleteUserFail", t.getMessage());
            }
        });
    }
}