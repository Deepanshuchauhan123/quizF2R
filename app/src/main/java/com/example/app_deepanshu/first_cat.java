package com.example.app_deepanshu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.app_deepanshu.api.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class first_cat extends AppCompatActivity implements View.OnClickListener {

    EditText aadhar, password;
    private long lastClickTime = 0;
   // private FirebaseAuth mAuth;
    ProgressBar simpleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_cat);

        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);


        aadhar = findViewById(R.id.edittext_adhaar);
        password = findViewById(R.id.edittext_password);
       // mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button_register1).setOnClickListener(this);
        findViewById(R.id.button_login).setOnClickListener(this);
        findViewById(R.id.edittext_password).setOnClickListener(this);

    }

    private void teacher_login() {
        String username = aadhar.getText().toString().trim();
        String pass1 = password.getText().toString().trim();

        if (username.isEmpty()) {
            simpleProgressBar.setVisibility(View.INVISIBLE);
            aadhar.setError("आधार अनिवार्य है!");
            aadhar.requestFocus();
            return;
        }

        //for Password
        if (pass1.isEmpty()) {
            simpleProgressBar.setVisibility(View.INVISIBLE);
            password.setError("पासवर्ड अनिवार्य है!");
            password.requestFocus();
            return;
        }
        if (pass1.length() < 6) {
            simpleProgressBar.setVisibility(View.INVISIBLE);
            password.setError("Minimum length of Password is 6");
            password.requestFocus();
            return;
        }

        Call<stu_login> call= RetrofitClient
                .getInstance().getApi().teacherLogin(username,pass1,"Teacher");
        call.enqueue(new Callback<stu_login>() {
            @Override
            public void onResponse(Call<stu_login> call, Response<stu_login> response) {

                if (response.isSuccessful()) {
                    Intent intent = new Intent(first_cat.this, teacher_grid.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    simpleProgressBar.setVisibility(View.INVISIBLE);
                    aadhar.setText("");
                    password.setText("");

                } else {

                    simpleProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(first_cat.this,"Invalid User Credential",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<stu_login> call, Throwable t) {
                simpleProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

//        mAuth.signInWithEmailAndPassword(adhar1, pass1).addOnCompleteListener(first_cat.this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful())
//                {
//                    simpleProgressBar.setVisibility(View.VISIBLE);
//                    Intent intent = new Intent(first_cat.this, teacher_grid.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    finish();
//
//                } else {
//                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.button_login:
                simpleProgressBar.setVisibility(View.VISIBLE);
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                teacher_login();
                break;

            case R.id.button_register1:

                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                Intent i = new Intent(first_cat.this, teacher_reg.class);
                startActivity(i);
                break;
        }
    }
}



