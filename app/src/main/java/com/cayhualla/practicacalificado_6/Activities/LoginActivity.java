package com.cayhualla.practicacalificado_6.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cayhualla.practicacalificado_6.R;
import com.cayhualla.practicacalificado_6.models.User;
import com.cayhualla.practicacalificado_6.repositories.UserRepository;

public class LoginActivity extends AppCompatActivity {
    private static final int REGISTER_FORM_REQUEST=100;

    private SharedPreferences sharedPreferences;

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
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

//        User user = UserRepository.getUser(username);

        // username remember
        String username = sharedPreferences.getString("username", null);
        if(username != null){
            usernameInput.setText(username);
            passwordInput.requestFocus();
        }
        // islogged remember
        if(sharedPreferences.getBoolean("islogged", false)){
            // Go to Dashboard
            goDashboard();
        }
       // usernameText.setText(user.getFullname());
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

// Save to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor
                .putString("username", user.getUsername())
                .putBoolean("islogged", true)
                .commit();
// Go to Dashboard
        goDashboard();


    }
    private void goDashboard() {

        startActivity(new Intent(this, MainActivity.class));


        finish();

    }

    public void callRegisterFormUsuario(View view){
        startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), REGISTER_FORM_REQUEST);

    }

}
