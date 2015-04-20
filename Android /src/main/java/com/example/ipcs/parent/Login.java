package com.example.ipcs.parent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.Activity;



public class Login extends Activity {

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin=(Button)findViewById(R.id.loginBtn);
        btnLogin.setOnClickListener(LoginListener);
    }



    public View.OnClickListener LoginListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent=new Intent(Login.this,Home2.class);

            startActivity(intent);

        }
    };

}
