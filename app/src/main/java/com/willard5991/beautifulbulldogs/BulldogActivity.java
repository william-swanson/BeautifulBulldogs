package com.willard5991.beautifulbulldogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BulldogActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog);

        textView = (TextView) findViewById(R.id.textView);

        Bulldog bulldog = (Bulldog) getIntent().getSerializableExtra("bulldog");
        textView.setText(bulldog.getName());
    }
}
