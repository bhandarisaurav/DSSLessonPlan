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

import com.dss.lessonplan.grades.grade10;
import com.dss.lessonplan.grades.grade9;

import java.util.ArrayList;

public class grades_list3 extends AppCompatActivity {

    ArrayList<String> grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_list);

        ListView gradesList = findViewById(R.id.grade_list);

        grades = new ArrayList<>();
        getGrades();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, grades);
        // Set The Adapter
        gradesList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        gradesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {

                String selectedGrade = grades.get(position);
                if (selectedGrade.equalsIgnoreCase("Grade 1")) {

                    Intent myIntent = new Intent(grades_list3.this, grade9.class);
                    startActivity(myIntent);

                } else {
                    Intent myIntent = new Intent(grades_list3.this, grade10.class);
                    startActivity(myIntent);
                }
                Toast.makeText(getApplicationContext(), "Grade Selected : " + selectedGrade, Toast.LENGTH_LONG).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(grades_list3.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getGrades() {

        grades.add("Grade 9");
        grades.add("Grade 10");
    }
}