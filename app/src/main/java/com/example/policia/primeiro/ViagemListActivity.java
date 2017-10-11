package com.example.policia.primeiro;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by policia on 27/09/2017.
 */

public class ViagemListActivity extends ListActivity  implements
        AdapterView.OnItemClickListener{

    private List<Map<String,Object>> viagens;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] de ={"imagem","destino","data","total"};
        int[] para = {R.id.tipoViagem,R.id.destino,R.id.data,
        R.id.valor};

        SimpleAdapter adapter =
                new SimpleAdapter(this,listarViagens(),R.layout.lista_viagem,
                        de,para);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        /*setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listarViagens()));
        ListView listView = getListView();
        listView.setOnItemClickListener(this);*/


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String,Object> map = viagens.get(position);
        String destino = (String) map.get("destino");
        String mensagem = "Viagem selecionada:" + destino;
        Toast.makeText(this,mensagem,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,GastoListActivity.class));


    }

    private List<Map<String,Object>> listarViagens(){

        viagens = new ArrayList<Map<String,Object>>();

        Map<String,Object> item = new HashMap<String,Object>();
        item.put("imagem",R.drawable.negocios);
        item.put("destino","Sao Paulo");
        item.put("data","02/02/2012 a 04/02/2012");
        item.put("total","Gasto total R$ 314,98");
        viagens.add(item);

        item = new HashMap<String,Object>();
        item.put("imagem",R.drawable.lazer);
        item.put("destino","Maceió");
        item.put("data","14/02/2012 a 22/05/2012");
        item.put("total","Gasto total R$ 25.834,67");
        viagens.add(item);

        return viagens;
    }
}
