package com.example.icms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText ETestado;
    private EditText ETvalor;
    private TextView TVporcentagem;
    private TextView TVtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETestado = findViewById(R.id.editTextEstado);
        ETvalor = findViewById(R.id.editTextValor);
        TVporcentagem = findViewById(R.id.textViewPorcentagem);
        TVtotal = findViewById(R.id.textViewTotal);
    }

    public void calcular(View view){
        String estado = ETestado.getText().toString();
        String valorString = ETvalor.getText().toString();

        float valor = Float.parseFloat(valorString);
        float porcentagem = 0;

        switch (estado) {
            case "RO":
                porcentagem = 17.5f;
                break;
            case "SP":
                porcentagem = 18;
                break;
        }
        float total = valor + (valor * porcentagem/100);
        TVporcentagem.setText(String.valueOf(porcentagem) + "%");
        TVtotal.setText(String.format("R$ %.2f", total));

        Toast.makeText(this, "Valor Do ICMS Calculado", Toast.LENGTH_SHORT).show();
    }
}