package com.example.policia.primeiro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by policia on 26/09/2017.
 */

public class DashboardActivity  extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }

    public void selecionarOpcao(View view){
        switch(view.getId()){
            case R.id.nova_viagem:
                startActivity(new Intent(this,ViagemActivity.class));
                break;
        }


        TextView textView = (TextView) view;
        String opcao = "Opçao : " + textView.getText().toString();
        Toast.makeText(this,opcao,Toast.LENGTH_SHORT).show();


    }
}
