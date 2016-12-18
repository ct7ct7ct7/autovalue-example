package com.github.ct7ct7ct7.autovalue_example;


import com.github.ct7ct7ct7.autovalue_example.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("users/{account}")
    Observable<User> getUser(@Path("account") String account);
}
