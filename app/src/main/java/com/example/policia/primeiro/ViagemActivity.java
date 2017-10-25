package com.example.policia.primeiro;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by policia on 11/10/2017.
 */

public class ViagemActivity extends Activity {
    private int ano,mes,dia;
    private Button dataChegadaButton,dataSaidaButton;
    private DataBaseHelper helper;
    private EditText destino,quantidadePessoas, orcamento;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_viagem);

        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        dataChegadaButton = (Button) findViewById(R.id.dataChegada);
        dataSaidaButton = (Button) findViewById(R.id.dataSaida);

        dataChegadaButton.setText(dia + "/" + (mes+1) + "/" + ano);
        dataSaidaButton.setText(dia + "/" + (mes+1) + "/" + ano);

        destino = (EditText) findViewById(R.id.destino);
        quantidadePessoas = (EditText) findViewById(R.id.quantidadePessoas);

        orcamento = (EditText) findViewById(R.id.orcamento);
        radioGroup = (RadioGroup) findViewById(R.id.tipoViagem);

        helper = new DataBaseHelper(this);
    }

    public void selecionarData(View view){
        showDialog(view.getId());
    }

    protected Dialog onCreateDialog(int id){
        if(R.id.dataChegada == id){
            return new DatePickerDialog(this,listener,ano,mes,dia);
        }
        if(R.id.dataSaida == id){
            return new DatePickerDialog(this,listener,ano,mes,dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ano = year;
            mes = month;
            dia = dayOfMonth;
            dataChegadaButton.setText(dia + "/" + (mes+1) + "/" + ano);
            dataSaidaButton.setText(dia + "/" + (mes+1) + "/" + ano);

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.viagem_menu,menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()){
            case R.id.novo_gasto:
                startActivity(new Intent(this,GastoActivity.class));
                return true;
            case R.id.remover:
                //remover do banco de dados
                Toast.makeText(this,"Removido",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return  super.onMenuItemSelected(featureId,item);
        }
    }

    public void salvarViagem(View view){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("destino",destino.getText().toString());
        //values.put("data_chegada",dataChegadaButton.getText().toString());
        //values.put("data_saida",dataSaidaButton.getText().toString());
        values.put("orcamento",orcamento.getText().toString());
        values.put("quantiade_pessoas",quantidadePessoas.getText().toString());

        int tipo = radioGroup.getCheckedRadioButtonId();
        if(tipo == R.id.lazer){
            values.put("tipo_viagem",Constantes.VIAGEM_LAZER);
        }else{
            values.put("tipo_viagem", Constantes.VIAGEM_NEGOCIOS);
        }

        long resultado = db.insert("viagem",null,values);

        if(resultado != -1){
            Toast.makeText(this,getString(R.string.registro_salvo),
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,getString(R.string.erro_salvar),
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
