package com.dss.lessonplan.grades;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dss.lessonplan.MainActivity;
import com.dss.lessonplan.R;
import com.dss.lessonplan.urlHandler;

import java.util.ArrayList;

public class grade8 extends AppCompatActivity {

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
                Intent myIntent = new Intent(grade8.this, urlHandler.class);
                myIntent.putExtra("class", "8");
                myIntent.putExtra("subject", selectedSubject);
                startActivity(myIntent);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(grade8.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSubjects() {

        subjects.add("English");
        subjects.add("Nepali");
        subjects.add("Science");
        subjects.add("Mathematics");
        subjects.add("Computer Science");
        subjects.add("Social Studies");
        subjects.add("E.P.H");
        subjects.add("Moral Education");
        subjects.add("OBTE");
        subjects.add("Chinese");
    }
}