package com.example.primerparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        Intent miIntent = null;
        switch (view.getId()){
            case R.id.btnRegistrarmateria:
                miIntent = new Intent(MainActivity.this,RegistrarMateriasActivity.class);
                break;
            case R.id.btnRegistrarNotas:
                miIntent = new Intent(MainActivity.this,RegistrarNotasActivity.class);
                break;
        }
        startActivity(miIntent);
    }
}