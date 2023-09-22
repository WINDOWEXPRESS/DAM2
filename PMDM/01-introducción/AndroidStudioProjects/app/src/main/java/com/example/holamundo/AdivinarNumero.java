package com.example.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdivinarNumero extends AppCompatActivity {
    //Declarar las variables
    TextView resultado;
    Button btReinicar;
    Button btConfirmar;
    EditText eTNumero;
    int bomba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_adivinar_numero);
/*
        //vincular las variables con los botones
        resultado.findViewById(R.id.textViewLove);
        btReinicar.findViewById(R.id.btReiniciar);
        btConfirmar.findViewById(R.id.btConfirmar);
        eTNumero.findViewById(R.id.eTNumero);


        //Generar un numero aleatorio
        bomba = (int) (Math.random()*100);
/*
        //Reiniciar el juego
        btReinicar.setOnClickListener((View view)->{
            bomba = (int) (Math.random()*100);
        });

 */
//        resultado.setText(bomba);
    }
}