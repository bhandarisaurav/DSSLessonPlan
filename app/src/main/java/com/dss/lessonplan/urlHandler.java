package com.dss.lessonplan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dss.lessonplan.domain.LessonPlan;
import com.dss.lessonplan.utils.HttpGetRequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class urlHandler extends AppCompatActivity {

    private String TAG = urlHandler.class.getSimpleName();
    List<LessonPlan> lessonPlanList;
    LessonPlan lessonPlan = new LessonPlan();
    String aclass;
    String subject;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_data);

        Intent intent = getIntent();
        aclass = intent.getStringExtra("class");
        subject = intent.getStringExtra("subject");
        url = "http://192.168.4.84/lessonplan_api/getData.php?Class=" + aclass + "&Subject=" + subject + "&Date=01-May";
        url = url.replaceAll(" ", "%20");

        System.out.println(url);

        lessonPlanList = new ArrayList<>();
        new fetchData().execute();
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

    public class fetchData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        fetchData() {
            progressDialog = new ProgressDialog(urlHandler.this);
        }

        @Override
        protected void onPreExecute() {
            /**
             * Execute first before fetching
             * then doInBackground method executed
             */
            progressDialog.setMessage("Fetching Data...");
            progressDialog.show();
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpGetRequestHandler httpGetRequestHandler = new HttpGetRequestHandler();

            // Making a request to url and getting response
            String jsonStr = httpGetRequestHandler.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
//                    System.out.println("test");
                    JSONArray jsonArray = new JSONArray(jsonStr);

                    for (int i = 0; i < jsonArray.length(); i++) {

//                        Admin admin = new Admin();
                        JSONObject jo = (JSONObject) jsonArray.get(i);

                        Log.e(TAG, "Response from url: " + jo);


//                        System.out.println(jo);
//
//                        String fullName = jo.getString("fullName");
//                        String post = jo.getString("post");
//                        String roomNo = jo.getString("roomNo");
//                        String status = jo.getString("status");
//
//
//                        admin.setFullName(fullName);
//                        admin.setPost(post);
//                        admin.setRoomNo(roomNo);
//                        admin.setStatus(status);
//
//                        adminList.add(admin);

//                        System.out.println("test" + jo.getString("Classwork")); working


                        lessonPlan.setLesson(jo.getString("Lesson"));
                        lessonPlan.setTopic(jo.getString("Topic"));
                        lessonPlan.setObjective(jo.getString("Objective"));
                        lessonPlan.setClasswork(jo.getString("Classwork"));
                        lessonPlan.setHomework(jo.getString("Homework"));
                        lessonPlan.setTime(jo.getString("Time"));
                        lessonPlanList.add(lessonPlan);

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
//            System.out.println("AdminList Inside DoBackground is : "+adminList);
            if (lessonPlanList != null) {
//                System.out.println("success");
                System.out.println(lessonPlanList);
                return "Successfully Fetched";
            }
//            }else{
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            System.out.println("s = " + s);
            progressDialog.dismiss();
            if (lessonPlanList == null) {
                String errorMessage = "Couldn't get json from server. Check Internet Connectivity!!";
                Intent intent = new Intent(urlHandler.this, MainActivity.class);
                intent.putExtra("errorMessage", errorMessage);
                startActivity(intent);
            } else {

                Intent intent = new Intent(urlHandler.this, display_data.class);
                intent.putExtra("Lesson", lessonPlan.getLesson());
                intent.putExtra("Topic", lessonPlan.getTopic());
                intent.putExtra("Objective", lessonPlan.getObjective());
                intent.putExtra("Classwork", lessonPlan.getClasswork());
                intent.putExtra("Homework", lessonPlan.getHomework());
                intent.putExtra("Time", lessonPlan.getTime());
                startActivity(intent);


            }
        }
    }
}



