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


public class RegisterActivity extends AppCompatActivity {

    private EditText username,Email,Password,Password2;
    private Button register;
    private TextView loginTextView;
//    public static Repository repository;
    DBHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //repository = new Repository();
        db = new DBHelper(this);
        register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                username = (EditText) findViewById(R.id.editTextName);
                Email = (EditText) findViewById(R.id.editTextEmail);
                Password = (EditText) findViewById(R.id.editTextPassword);
                Password2 = (EditText) findViewById(R.id.editTextPassword2);
                String uName = username.getText().toString();
                String email = Email.getText().toString().trim();
                String uPassword = Password.getText().toString().trim();
                String uPassword2 = Password2.getText().toString();

                if(uName.equals("")){
                    displayToastMessages("Enter user name").show();
                }else if(email.equals("")){
                    displayToastMessages("Enter email").show();
                }else if(uPassword.isEmpty()||uPassword2.isEmpty()){
                    displayToastMessages("Enter password").show();
                }else{
                    if(uPassword.equals(uPassword2)) {
                        Boolean chkemail = db.chkemail(email);
                        if(chkemail==true){
                            boolean insert = db.insert(email, uPassword);
                            System.out.print(insert);
                            if(insert==true){
                                displayToastMessages("Register successfully").show();
                                Intent mainIntent = new Intent(RegisterActivity.this, MainScreenActivity.class);
                                startActivity(mainIntent);
                            }
                        }
                        else{
                            displayToastMessages("Email is aready exist").show();
                        }
                    }
                }
            }
        });
    }

    public void loginClick(View view){
        Intent login = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(login);
    }

    private Toast displayToastMessages(String message){
        Toast toast = Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        return toast;
    }

}
