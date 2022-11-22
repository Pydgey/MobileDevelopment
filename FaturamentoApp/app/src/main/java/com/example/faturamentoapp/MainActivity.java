package com.example.faturamentoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String ARQUIVO_MEUS_DADOS = "DADOS";
    Button btConfirmar;
    TextView texto;
    NumberPicker numberPicker;
    EditText editText;
    RadioGroup radioGroup;
    Button btNomeFantasia;

    private void adicionarValor(int ano, float valor) {
        SharedPreferences sharedPreferences =
                getSharedPreferences(ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE);

        float valorAtual = sharedPreferences.getFloat(String.valueOf(ano), 0);
        float novoValor = valorAtual + valor;

        sharedPreferences.edit().putFloat(String.valueOf(ano), novoValor).apply();
    }

    private void excluirValor (int ano, float valor) {
        SharedPreferences sharedPreferences =
                getSharedPreferences(ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE);

        float valorAtual = sharedPreferences.getFloat(String.valueOf(ano), 0);
        float novoValor = valorAtual - valor;

        if(novoValor < 0){
            novoValor = 0;
        }

        sharedPreferences.edit().putFloat(String.valueOf(ano), novoValor).apply();
    }

    private void exibirSaldo(int ano) {
        SharedPreferences sharedPreferences =
                getSharedPreferences(ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE);

        float saldo = sharedPreferences.getFloat(String.valueOf(ano), 0);
        texto.setText(String.format("R$ %.2f", saldo));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btConfirmar = findViewById(R.id.button2);
        texto=findViewById(R.id.textView);
        numberPicker = findViewById(R.id.numberPicker);
        editText = findViewById(R.id.editTextTextPersonName);
        radioGroup = findViewById(R.id.radioGroup2);
        btNomeFantasia = findViewById(R.id.fantasia);

        numberPicker.setMinValue(2000);
        numberPicker.setMaxValue(2022);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                exibirSaldo(numberPicker.getValue());
            }
        });

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!editText.getText().toString().isEmpty()){
                    int ano = numberPicker.getValue();
                    float valor = Float.parseFloat(editText.getText().toString());

                    switch (radioGroup.getCheckedRadioButtonId()){
                        case R.id.radioButton5:
                            adicionarValor(ano, valor);
                            break;
                        case R.id.radioButton6:
                            excluirValor(ano, valor);
                            break;
                    }

                    exibirSaldo(ano);
                }
            }
        });

        btNomeFantasia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PersonalizarActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE);
        String nomeFantasia = sharedPreferences.getString("nomeFantasia", null);
        if(nomeFantasia != null) {
            setTitle(nomeFantasia);
        }

        int ano = numberPicker.getValue();
        exibirSaldo(ano);
    }


}























