package com.prankit.qubi7;

import com.prankit.qubi7.model.CreateUserModel;
import com.prankit.qubi7.model.GetUserModel;
import com.prankit.qubi7.model.UpdateUserModel;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface API {

    @GET("api/users?page=2")
    Call<GetUserModel> getAllUsers();

    @POST("api/users")
    @Multipart
    Call<CreateUserModel> createUser(@Part("name")RequestBody requestBody1, @Part("job")RequestBody requestBody2);

    @PUT("api/users/2")
    @Multipart
    Call<UpdateUserModel> updateUser(@Part("name")RequestBody requestBody1, @Part("job")RequestBody requestBody2);

    @DELETE("api/users/2")
    Call<ResponseBody> deleteUser();
}
