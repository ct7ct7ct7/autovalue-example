package com.github.ct7ct7ct7.autovalue_example;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.github.ct7ct7ct7.autovalue_example.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editText) EditText editText;
    @BindView(R.id.textView) TextView textView;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(GsonAdapterFactory.create()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @OnClick(R.id.button)
    public void buttonOnClick() {
        apiService.getUser(editText.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    textView.setText(user.toString());
                    gotoTwoActivity(user);
                }, throwable -> textView.setText(throwable.getMessage()));
    }

    private void gotoTwoActivity(User user) {
        Intent intent = TwoActivity.createIntent(this, user);
        startActivity(intent);
    }
}
