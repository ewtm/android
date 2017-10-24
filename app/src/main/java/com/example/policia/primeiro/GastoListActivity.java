package com.example.policia.primeiro;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

/**
 * Created by policia on 11/10/2017.
 */

public class GastoListActivity extends ListActivity implements AdapterView.OnItemClickListener {

    private List<Map<String,Object>> gastos;
    private String dataAnterior = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] de ={"data","descricao","valor","categoria"};
        int[] para = {R.id.data,R.id.descricao,R.id.valor};

        SimpleAdapter adapter =
                new SimpleAdapter(this,listarGastos(),R.layout.lista_gasto,de,para);
        adapter.setViewBinder(new GastoViewBinder());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

        registerForContextMenu(getListView());


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String,Object> map = gastos.get(position);
        String descricao = (String) map.get("descricao");
        String mensagem = "Gasto selecionada:" + descricao;
        Toast.makeText(this,mensagem,Toast.LENGTH_SHORT).show();



    }

    private List<Map<String,Object>> listarGastos(){

        gastos = new ArrayList<Map<String,Object>>();

        Map<String,Object> item = new HashMap<String,Object>();
        item.put("data","04/02/2012");
        item.put("descricao","Diária Hotel");
        item.put("valor","R$260,00");
        gastos.add(item);

        item = new HashMap<String,Object>();
        item.put("data","03/02/2012");
        item.put("descricao","Diária Hotel");
        item.put("descricao","Diária Motel");
        item.put("valor","R$280,00");
        gastos.add(item);

        return gastos;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gasto_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //invoca menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gasto_menu,menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.remover){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            gastos.remove(info.position);
            getListView().invalidateViews();
            dataAnterior = "";
            return true;
        }

        return super.onContextItemSelected(item);
    }

    private class GastoViewBinder implements SimpleAdapter.ViewBinder{

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            if(view.getId() == R.id.data){
                if(!dataAnterior.equals(data)){
                    TextView textView = (TextView) view;
                    textView.setText(textRepresentation);
                    dataAnterior = textRepresentation;
                    view.setVisibility(View.VISIBLE);
                }else{
                    view.setVisibility(View.GONE);
                }
                return true;
            }
            if(view.getId() == R.id.categoria){
                Integer id = (Integer)data;
                view.setBackgroundColor(getResources().getColor(id));
                return true;
            }
            return false;
        }

    }
}
