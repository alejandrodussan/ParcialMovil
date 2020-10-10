package com.example.primerparcial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelperNotas  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="Notas.db";
    public static final String TABLE_NAME ="tabla_notas";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="MATERIA";
    public static final String COL_3 ="CORTE_1";
    public static final String COL_4 ="CORTE_2";
    public static final String COL_5 ="CORTE_3";
    public DataBaseHelperNotas(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"( ID INTEGER PRIMARY KEY AUTOINCREMENT, MATERIA STRING, CORTE_1 FLOAT, CORTE_2 FLOAT, CORTE_3 FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertarDatos(String materia, String corte_1, String corte_2, String corte_3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,materia);
        contentValues.put(COL_3,corte_1);
        contentValues.put(COL_4,corte_2);
        contentValues.put(COL_5,corte_3);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor verTodos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean actualizarDato(String id,String materia,  String corte_1, String corte_2, String corte_3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,materia);
        contentValues.put(COL_3,corte_1);
        contentValues.put(COL_4,corte_2);
        contentValues.put(COL_5,corte_3);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        return true;
    }
    public Integer borrarDato(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }
}
