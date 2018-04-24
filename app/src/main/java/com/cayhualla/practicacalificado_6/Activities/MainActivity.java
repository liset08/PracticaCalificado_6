package com.cayhualla.practicacalificado_6.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cayhualla.practicacalificado_6.R;
import com.cayhualla.practicacalificado_6.models.User;
import com.cayhualla.practicacalificado_6.repositories.UserRepository;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    private TextView usernameText, usernameText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText1 = (TextView)findViewById(R.id.welcome_text);

        usernameText = (TextView)findViewById(R.id.fullname_text);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);


        Log.d(TAG, "username: " + username);

        User user = UserRepository.getUser(username);

        usernameText.setText(user.getFullname());
    }

    public void callLogout(View view){
        // remove from SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor.putBoolean("islogged", false).commit();
//        boolean success = editor.clear().commit(); // not recommended

        goDashboard();
    }
    private void goDashboard() {

        startActivity(new Intent(this, LoginActivity.class));


        finish();

    }
}
