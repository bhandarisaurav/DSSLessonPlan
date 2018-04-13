package com.dss.lessonplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dss.lessonplan.grades.grade1;
import com.dss.lessonplan.grades.grade2;
import com.dss.lessonplan.grades.grade3;
import com.dss.lessonplan.grades.grade4;
import com.dss.lessonplan.grades.grade5;

import java.util.ArrayList;

public class grades_list1 extends AppCompatActivity {

    ArrayList<String> grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_list);

        ListView gradesList= findViewById(R.id.grade_list);

        grades = new ArrayList<>();
        getGrades();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, grades);
        // Set The Adapter
        gradesList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        gradesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
            {

                String selectedGrade = grades.get(position);
                if (selectedGrade.equalsIgnoreCase("Grade 1")) {

                    Intent myIntent = new Intent(grades_list1.this, grade1.class);
                    startActivity(myIntent);

                } else if (selectedGrade.equalsIgnoreCase("Grade 2")) {

                    Intent myIntent;
                    myIntent = new Intent(grades_list1.this, grade2.class);
                    startActivity(myIntent);

                } else if (selectedGrade.equalsIgnoreCase("Grade 3")) {

                    Intent myIntent = new Intent(grades_list1.this, grade3.class);
                    startActivity(myIntent);

                } else if (selectedGrade.equalsIgnoreCase("Grade 4")) {

                    Intent myIntent = new Intent(grades_list1.this, grade4.class);
                    startActivity(myIntent);

                } else {
                    Intent myIntent = new Intent(grades_list1.this, grade5.class);
                    startActivity(myIntent);
                }
                Toast.makeText(getApplicationContext(), "Grade Selected : " + selectedGrade, Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(grades_list1.this, MainActivity.class);

                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getGrades() {

        grades.add("Grade 1");
        grades.add("Grade 2");
        grades.add("Grade 3");
        grades.add("Grade 4");
        grades.add("Grade 5");
    }
}