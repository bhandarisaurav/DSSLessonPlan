package com.dss.lessonplan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class about_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View aboutPage = new AboutPage(this)

                .isRTL(false)
                .setImage(R.drawable.about_image)
                .setDescription(" ")
                .addItem(new Element().setTitle("Version 6.2"))
                .addGroup("Connect with us")
                .addEmail("saurav.bhandari@deerwalk.edu.np")
                .addWebsite("http://lessonplan.sifal.dwit.edu.np")
                .addFacebook("sauravbh1")
                .addTwitter("medyo80")
                .addYoutube("UCmCfg6O8xUYz3VRHlpo6sBg")
                .addInstagram("__sauravbhandari__")
                .addGitHub("saurav529")
                .addItem(getCopyRightsElement())
                .create();

        setContentView(aboutPage);
    }


    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(about_us.this, copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }


}
