package com.dss.lessonplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class display_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_data);

        Intent intent = getIntent();
        String lesson = intent.getStringExtra("Lesson");
        String topic = intent.getStringExtra("Topic");
        String objective = intent.getStringExtra("Objective");
        String classwork = intent.getStringExtra("Classwork");
        String homework = intent.getStringExtra("Homework");
        String time = intent.getStringExtra("Time");

        TextView t1 = findViewById(R.id.lesson);
        TextView t2 = findViewById(R.id.topic);
        TextView t3 = findViewById(R.id.objective);
        TextView t4 = findViewById(R.id.classwork);
        TextView t5 = findViewById(R.id.homework);
        TextView t6 = findViewById(R.id.time);

        t1.setText(lesson);
        t2.setText(topic);
        t3.setText(objective);
        t4.setText(classwork);
        t5.setText(homework);
        t6.setText(time);


    }

    @Override
    public void onBackPressed() {
        Intent mainActivity = new Intent(display_data.this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }


    public void onClickMethod(View view) {
        Button t1 = findViewById(R.id.previous);
        t1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Previous", Toast.LENGTH_SHORT).show();
            }
        });

        Button t2 = findViewById(R.id.next);
        t2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Next", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
