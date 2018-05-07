package com.dss.lessonplan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class display_data extends AppCompatActivity {
    int day;
    String subject;
    String aclass;
    String date;

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
        subject = intent.getStringExtra("subject");
        aclass = intent.getStringExtra("class");
        date = intent.getStringExtra("date");
        day = intent.getIntExtra("day", 0);
        System.out.println("===========================================");
        System.out.println("day = " + day);
        System.out.println("===========================================");

        if ((objective == null || objective.equals("")) && (classwork == null || classwork.equals(""))) {
            System.out.println("===========================================");
            System.out.println("Null Data");
            System.out.println("===========================================");
            Intent i = new Intent(display_data.this, urlHandler.class);
            Integer val = day + 1;
            System.out.println("===========================================");
            System.out.println(" Next day = " + val);
            System.out.println("===========================================");
            i.putExtra("day", val);
            i.putExtra("class", aclass);
            i.putExtra("subject", subject);
            startActivity(i);
        } else {

            TextView t1 = findViewById(R.id.lesson);
            TextView t2 = findViewById(R.id.topic);
            TextView t3 = findViewById(R.id.objective);
            TextView t4 = findViewById(R.id.classwork);
            TextView t5 = findViewById(R.id.homework);
            TextView t6 = findViewById(R.id.time);

            Button aclass1 = findViewById(R.id.aclass);
            Button subject1 = findViewById(R.id.subject);
            Button date1 = findViewById(R.id.date);

            t1.setText(lesson);
            t2.setText(topic);
            t3.setText(objective);
            t4.setText(classwork);
            t5.setText(homework);
            t6.setText(time);
            aclass1.setText(String.format("Grade %s", aclass));
            subject1.setText(subject);
            String final_date = date;
            date1.setText(final_date);
        }



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
                Intent intent = new Intent(display_data.this, urlHandler.class);
                Integer val = day - 1;
                System.out.println("===========================================");
                System.out.println(" Previous day = " + val);
                System.out.println("===========================================");
                intent.putExtra("day", val);
                intent.putExtra("class", aclass);
                intent.putExtra("subject", subject);
                shakeItBaby();
                startActivity(intent);
            }
        });

        Button t2 = findViewById(R.id.next);
        t2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Next", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(display_data.this, urlHandler.class);
                Integer val = day + 1;
                System.out.println("===========================================");
                System.out.println(" Next day = " + val);
                System.out.println("===========================================");
                intent.putExtra("day", val);
                intent.putExtra("class", aclass);
                intent.putExtra("subject", subject);
                shakeItBaby();
                startActivity(intent);


            }
        });
    }

    private void shakeItBaby() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) Objects.requireNonNull(getSystemService(VIBRATOR_SERVICE))).vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) Objects.requireNonNull(getSystemService(VIBRATOR_SERVICE))).vibrate(150);
        }
    }
}
