package com.example.prueba1_iza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button boton = (Button) findViewById(R.id.btnLogin);
        boton.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String usuario = ((EditText) findViewById(R.id.et1)).getText().toString();
                String password = ((EditText) findViewById(R.id.et2)).getText().toString();
                if (usuario.equals("izajorge7@gmail.com") && password.equals("123")){
                    Intent nuevo = new Intent(MainActivity.this,activity_diarias.class);
                    startActivity(nuevo);
                } else{
                    Toast.makeText(boton.getContext(), "Usuario Incorrecto o clave incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
