package com.cayhualla.practicacalificado_6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cayhualla.practicacalificado_6.models.User;
import com.cayhualla.practicacalificado_6.repositories.UserRepository;

public class LoginActivity extends AppCompatActivity {
    private static final int REGISTER_FORM_REQUEST=100;

    private EditText usernameInput;
    private EditText passwordInput;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = (EditText)findViewById(R.id.username_input);
        passwordInput = (EditText)findViewById(R.id.password_input);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
    }

    public void callLogin(View view) {
        progressBar.setVisibility(View.VISIBLE);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Login logic
        User user = UserRepository.login(username, password);

        if(user == null){
            Toast.makeText(this, "Username or password invalid", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        Toast.makeText(this, "Welcome " + user.getFullname(), Toast.LENGTH_SHORT).show();


// Go to Dashboard
        goDashboard();


    }
    private void goDashboard() {
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

    public void callRegisterForm(View view){
        startActivityForResult(new Intent(this, RegisterActivity.class), REGISTER_FORM_REQUEST);
    }

}
