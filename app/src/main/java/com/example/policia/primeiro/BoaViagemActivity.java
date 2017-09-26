package com.example.policia.primeiro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by policia on 26/09/2017.
 */

public class BoaViagemActivity extends Activity {
    private EditText usuario;
    private EditText senha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario  = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
    }

    public void entrarOnClick(View v){
        String usuarioInformado = usuario.getText().toString();
        String senhaInformado = senha.getText().toString();

        if("leitor".equals(usuarioInformado)&&
                "123".equals(senhaInformado)){
            startActivity(new Intent(this,DashboardActivity.class));

        }else{
            String mensagemErro = getString(R.string.erro_autenticacao);
            Toast toast = Toast.makeText(this,mensagemErro,Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
