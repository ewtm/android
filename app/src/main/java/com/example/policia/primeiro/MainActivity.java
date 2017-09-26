package com.example.policia.primeiro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText nomeEditText;
    private TextView saudacaoTextView;
    //private Editable texto;
    private String saudacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeEditText = (EditText) findViewById(R.id.nomeEditText);
        saudacaoTextView = (TextView) findViewById(R.id.saudacaoTextView);
        saudacao = getResources().getString(R.string.saudacao);


    }

    public void surpreenderUsuario(View v){
        Intent intent = new Intent(SaudacaoActivity.ACAO_EXIBIR_SAUDACAO);
        intent.addCategory(SaudacaoActivity.CATEGORIA_SAUDACAO);

        String texto = this.nomeEditText.getText().toString();
        intent.putExtra(SaudacaoActivity.EXTRA_NOME_USUARIO, texto);

        startActivity(intent);

    }


}
