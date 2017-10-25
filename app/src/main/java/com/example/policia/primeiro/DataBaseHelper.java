package com.example.policia.primeiro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by policia on 24/10/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS="BoaViagem";
    private static int VERSAO = 1;

    public DataBaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table viagem(_id integer primary key auto_increment," +
                "destino text,tipo_viagem integer,data_chegada date," +
                "data_saida date, orcamento double," +
                "quantidade_pessoas integer);");
        db.execSQL("create table gasto(_id integer primary key," +
                "categoria text, data date, valor double," +
                "foreign key(viagem_id)references viagem(_id));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
