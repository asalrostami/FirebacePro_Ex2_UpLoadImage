package com.example.asal.firebacepro_ex2_uploadimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextEmail,mEditTextPassword;
    private Button mButtonSignin,mButtonSignout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextEmail = (EditText)findViewById(R.id.editTextEmail);
        mEditTextPassword = (EditText)findViewById(R.id.editTextPassword);

        mButtonSignin = (Button)findViewById(R.id.btnSignin);
        mButtonSignout = (Button)findViewById(R.id.btnSignout);

    }
}
