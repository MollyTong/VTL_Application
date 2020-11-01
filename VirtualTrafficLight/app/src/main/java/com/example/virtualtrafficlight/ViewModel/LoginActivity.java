package com.example.virtualtrafficlight.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualtrafficlight.DBHelper;
import com.example.virtualtrafficlight.R;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText,passwordEditText;
    private TextView registerText;
    private Button loginButton;
    DBHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.Login);
        registerText = findViewById(R.id.registertextView);
        db = new DBHelper(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailEditText = (EditText) findViewById(R.id.editTextEmail);
                passwordEditText = (EditText) findViewById(R.id.editTextPassword);
                String uemail = emailEditText.getText().toString();
                String uPassword = passwordEditText.getText().toString();
                System.out.print("onclick");
                if (uemail.equals("")) {
                    displayToastMessages("Enter email").show();
                } else if (uPassword.isEmpty()) {
                    displayToastMessages("Enter password").show();
                } else {
                    boolean checkuser = db.checkuser(uemail,uPassword);
                    if(checkuser==true){
                        displayToastMessages("Log in successfully").show();
                        Intent mainIntent = new Intent(LoginActivity.this, MainScreenActivity.class);
                        startActivity(mainIntent);
                    } else {
                        displayToastMessages("Please register").show();
                    }
                }
            }
        });
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(register);
            }
        });
    }

    private Toast displayToastMessages(String message){
        Toast toast = Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        return toast;
    }

}
