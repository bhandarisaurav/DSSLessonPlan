package com.dss.lessonplan.grades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dss.lessonplan.R;

import java.util.ArrayList;

public class grade1 extends AppCompatActivity {

    ArrayList<String> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjects);

        ListView subjectsList = findViewById(R.id.subjects);

        subjects = new ArrayList<>();
        getSubjects();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subjects);
        // Set The Adapter
        subjectsList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        subjectsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {

                String selectedSubject = subjects.get(position);
                Toast.makeText(getApplicationContext(), "Subject Selected : " + selectedSubject, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSubjects() {

        subjects.add("English");
        subjects.add("Nepali");
        subjects.add("Science");
        subjects.add("Mathematics");
        subjects.add("Local Studies");
        subjects.add("Social Studies");
    }
}