package com.github.ct7ct7ct7.autovalue_example.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class User implements Parcelable {
    @SerializedName("name")
    public abstract String userName();

    @SerializedName("blog")
    public abstract String webUrl();

    @SerializedName("public_repos")
    public abstract int publicRepos();

    public static User create(String userName, String webUrl, int publicRepos) {
        return new AutoValue_User(userName, webUrl, publicRepos);
    }

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }
}