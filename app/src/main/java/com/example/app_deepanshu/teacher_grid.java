package com.example.app_deepanshu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class teacher_grid extends AppCompatActivity {
    private Button assigment;
    private Button report;
    private Button upload;
    private long lastClickTime = 0;
    private Button student,attend;
   // private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_grid);


       // firebaseAuth = firebaseAuth.getInstance();
        assigment = (Button) findViewById(R.id.teacher_self);
        assigment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }

                lastClickTime = SystemClock.elapsedRealtime();
                Intent i = new Intent(teacher_grid.this, teacher_self_grid.class);
                startActivity(i);
            }
        });
        report = (Button) findViewById(R.id.student_detail);
        report.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }

                lastClickTime = SystemClock.elapsedRealtime();
                Intent i = new Intent(teacher_grid.this, Add_Student.class);
                startActivity(i);
            }
        });
        attend = (Button) findViewById(R.id.attendance);
        attend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }

                lastClickTime = SystemClock.elapsedRealtime();
                Intent i = new Intent(teacher_grid.this, Attendance_Activity.class);
                startActivity(i);
            }
        });
        upload = (Button) findViewById(R.id.report_show);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }

                lastClickTime = SystemClock.elapsedRealtime();
                Intent i = new Intent(teacher_grid.this, Report_Show_Activity
                        .class);
                startActivity(i);
            }
        });
        student = (Button) findViewById(R.id.report);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                Intent i = new Intent(teacher_grid.this, Report_By_Teacher.class);
                startActivity(i);
            }
        });
    }

    private void Logout()
    {
//        firebaseAuth.signOut();
//        finish();
//        startActivity(new Intent(teacher_grid.this,first_cat.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutMenu:
            {
            Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("X", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("lastActivity", getClass().getName());
        editor.commit();
    }
}