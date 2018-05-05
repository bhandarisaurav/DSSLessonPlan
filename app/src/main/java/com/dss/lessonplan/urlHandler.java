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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class urlHandler extends AppCompatActivity {

    private String TAG = urlHandler.class.getSimpleName();
    List<LessonPlan> lessonPlanList;
    LessonPlan lessonPlan = new LessonPlan();
    String aclass;
    String subject;
    String url;
    String date;
    String date1;
    int day;
    String ip = IP.getIP();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_data);

        Intent intent = getIntent();
        aclass = intent.getStringExtra("class");
        subject = intent.getStringExtra("subject");
        day = intent.getIntExtra("day", 0);
        if (day == 0)
            url = "http://" + ip + "/lessonplan_api/getData.php?Class=" + aclass + "&Subject=" + subject;
        else
            url = "http://" + ip + "/lessonplan_api/getData.php?Class=" + aclass + "&Subject=" + subject + "&day=" + day;

        url = url.replaceAll(" ", "%20");

        System.out.println(url);

        lessonPlanList = new ArrayList<>();
        new fetchData().execute();
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
                        lessonPlan.setDate(jo.getString("Date"));
                        day = jo.getInt("day");
                        date = jo.getString("month");
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
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check Internet Connectivity!!",
//                                Toast.LENGTH_LONG)
//                                .show();
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Getting From Local Database",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

                //offline data loader
                NumberFormat formatter = new DecimalFormat("00");

                java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("MMM");
                java.text.SimpleDateFormat f1 = new java.text.SimpleDateFormat("d");

                String m = f.format(new Date());
                int d = Integer.parseInt(f1.format(new Date()));

                if (day == 0)
                    date1 = String.format("%s-%s", formatter.format(d), m);
                else
                    date1 = String.format("%s-%s", formatter.format(d + day), m);
                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                lessonPlan = dbHelper.viewData(aclass, subject, date1);
            }

            if (lessonPlan != null) {

                System.out.println(lessonPlan);
                return "Successfully Fetched";
            }

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
                intent.putExtra("day", day);
                intent.putExtra("subject", subject);
                intent.putExtra("class", aclass);
                if (date == null || date.equals(""))
                    intent.putExtra("date", lessonPlan.getDate());
                else
                    intent.putExtra("date", lessonPlan.getDate());

                startActivity(intent);


            }
        }
    }


}



