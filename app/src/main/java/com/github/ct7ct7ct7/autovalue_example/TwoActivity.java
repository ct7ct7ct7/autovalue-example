package com.github.ct7ct7ct7.autovalue_example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.ct7ct7ct7.autovalue_example.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anson on 2016/12/20.
 */

public class TwoActivity extends AppCompatActivity {
    private final static String KEY = "KEY";
    @BindView(R.id.textView) TextView textView;

    public static Intent createIntent(Activity activity, User user) {
        Intent intent = new Intent(activity, TwoActivity.class);
        intent.putExtra(KEY, user);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        ButterKnife.bind(this);
        User user = getIntent().getParcelableExtra(KEY);
        String content = "Name : " + user.userName() + "\n\n" + "Web Url : " + user.webUrl();
        textView.setText(content);
    }
}
