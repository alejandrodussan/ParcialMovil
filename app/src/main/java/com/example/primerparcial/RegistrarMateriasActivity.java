package com.example.primerparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarMateriasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_materias);
        mydb = new DataBaseHelper(this);
        editNombre = (EditText)findViewById(R.id.editTextNombre);
        editId = (EditText)findViewById(R.id.editTextId);
        btnGuardar= (Button)findViewById(R.id.btnGuardar);
        btnVerTodos= (Button)findViewById(R.id.btnVerTodos);
        btnActualizar= (Button)findViewById(R.id.btnActualizar);
        btnEliminar= (Button)findViewById(R.id.btnEliminar);
        Agregar();
        VerTodos();
        Actualizar();
        Eliminar();
    }

    DataBaseHelper mydb;
    EditText editNombre,editId;
    Button btnGuardar,btnVerTodos,btnActualizar,btnEliminar;

    public void onClick(View view){
        Intent miIntent = new Intent(RegistrarMateriasActivity.this,MainActivity.class);
        startActivity(miIntent);
    }

    public void Agregar(){
        btnGuardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean esInsertado = mydb.insertarDato(editNombre.getText().toString());
                        if (esInsertado == true)
                            Toast.makeText(RegistrarMateriasActivity.this, "Materia agregada", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(RegistrarMateriasActivity.this, "Error al agregar materia", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void VerTodos(){
        btnVerTodos.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.verTodos();
                        if(res.getCount()==0){
                            showMessage("Error","no hay datos para mostrar");
                            return;
                        }else{
                            StringBuffer buffer = new StringBuffer();
                            while(res.moveToNext()){
                                buffer.append("Id: "+res.getString(0)+"\n");
                                buffer.append("Name: "+res.getString(1)+"\n");
                            }
                            showMessage("Datos",buffer.toString());
                        }
                    }
                }
        );
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void Actualizar(){
        btnActualizar.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean esActualizado = mydb.actualizarDato(editId.getText().toString(),
                                editNombre.getText().toString());
                        if(esActualizado == true)
                            Toast.makeText(RegistrarMateriasActivity.this,"Materia actualizada",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(RegistrarMateriasActivity.this,"Error al actualizar la materia",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void Eliminar(){
        btnEliminar.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Integer deleteRows = mydb.borrarDato(editId.getText().toString());
                        if (deleteRows > 0)
                            Toast.makeText(RegistrarMateriasActivity.this,"Dato Eliminado",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(RegistrarMateriasActivity.this,"Error al eliminar los datos",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}