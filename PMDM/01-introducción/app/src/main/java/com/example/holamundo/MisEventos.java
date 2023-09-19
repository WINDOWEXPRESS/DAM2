package com.example.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MisEventos extends AppCompatActivity {

    TextView tv;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_eventos);

        tv = findViewById(R.id.tvut01Salida);
        bt = findViewById(R.id.btut01Accion);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {tv.setText("HOLAAA");
            }
        });
    }
}