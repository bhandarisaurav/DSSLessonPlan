package com.dss.lessonplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class urlHandler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.url_handler);

        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        String aClass = intent.getStringExtra("class");

        TextView mTextView = findViewById(R.id.textView1);
        mTextView.setText(aClass);

        TextView mTextView1 = findViewById(R.id.textView2);
        mTextView1.setText(subject);

    }
}
