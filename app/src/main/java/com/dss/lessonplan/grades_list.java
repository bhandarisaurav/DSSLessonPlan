package com.dss.lessonplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class grades_list extends AppCompatActivity {

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
                String selectedMovie=grades.get(position);
                Toast.makeText(getApplicationContext(), "Grade Selected : "+selectedMovie,   Toast.LENGTH_LONG).show();
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