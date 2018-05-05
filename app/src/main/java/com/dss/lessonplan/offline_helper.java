package com.dss.lessonplan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.dss.lessonplan.database.DatabaseHelper;
import com.dss.lessonplan.domain.LessonPlan;
import com.dss.lessonplan.utils.HttpGetRequestHandler;
import com.dss.lessonplan.utils.IP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class offline_helper extends AppCompatActivity {

    String ip = IP.getIP();
    String url = "http://" + ip + "/lessonplan_api/getAll.php";

    private String TAG = urlHandler.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_data);
        System.out.println(url);
        new fetch_all().execute();
    }

//    public void onClickMethod(View view) {
//        Button t1 = findViewById(R.id.previous);
//        t1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Previous", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Button t2 = findViewById(R.id.next);
//        t2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Next", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

    public class fetch_all extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        fetch_all() {
            progressDialog = new ProgressDialog(offline_helper.this);
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setTitle("Offline Data");
            progressDialog.setMessage("Downloading Data Please Wait...");
            progressDialog.show();
            progressDialog.setCancelable(true);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpGetRequestHandler httpGetRequestHandler = new HttpGetRequestHandler();

            String jsonStr = httpGetRequestHandler.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        LessonPlan lessonPlan = new LessonPlan();
                        JSONObject jo = (JSONObject) jsonArray.get(i);
                        Log.e(TAG, "Response from url: " + jo);

                        lessonPlan.setAclass(jo.getString("Class"));
                        lessonPlan.setSubject(jo.getString("Subject"));
                        lessonPlan.setLesson(jo.getString("Lesson"));
                        lessonPlan.setTopic(jo.getString("Topic"));
                        lessonPlan.setObjective(jo.getString("Objective"));
                        lessonPlan.setClasswork(jo.getString("Classwork"));
                        lessonPlan.setHomework(jo.getString("Homework"));
                        lessonPlan.setTime(jo.getString("Time"));
                        lessonPlan.setDate(jo.getString("Date"));

                        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                        dbHelper.insertData(lessonPlan);

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check Internet Connectivity!!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            Intent intent = new Intent(offline_helper.this, MainActivity.class);
            startActivity(intent);
        }
    }
}



