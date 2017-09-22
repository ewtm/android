package com.example.policia.primeiro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by policia on 22/09/2017.
 */

public class SaudacaoActivity extends Activity {
    public static final String EXTRA_NOME_USUARIO =
            "helloandroid.EXTRA_NOME_USUARIO";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saudacao);

        TextView saudacaoTextView = (TextView) findViewById(R.id.saudacaoTextView);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_NOME_USUARIO)){
            String saudacao = getResources().getString(R.string.saudacao);
            saudacaoTextView.setText(saudacao + " "  + intent.getStringExtra(EXTRA_NOME_USUARIO));

        }else{
            saudacaoTextView.setText("O nome do usuario nao foi informado");
        }

    }
}
