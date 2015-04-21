package com.example.ipcs.parent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

    LinearLayout background;
    Button btnLook, btnSchool;
    ImageView pic;

    //private final String TAG="TKT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background= (LinearLayout)findViewById(R.id.background);
        btnLook=(Button)findViewById(R.id.btnLook);
        btnSchool=(Button)findViewById(R.id.btnSchool);
        btnSchool.setOnClickListener(SchoolListener);

    }

    public View.OnClickListener SchoolListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent login=new Intent(MainActivity.this,Login.class);
            startActivity(login);

        }
    };

    }





