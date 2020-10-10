package com.example.primerparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrarNotasActivity extends AppCompatActivity {
    DataBaseHelperNotas mydb;
    DataBaseHelper mydbm;
    ArrayList<String> materias;
    EditText editQuizP,editPorcentajeQuizP,editTallerP,editPorcentajeTallerP,editId;
    EditText editParcialTP,editPorcentajeParcialTP,editParcialPP,editPorcentajeParcialPP;
    EditText editQuizS,editPorcentajeQuizS,editTallerS,editPorcentajeTallerS;
    EditText editParcialTS,editPorcentajeParcialTS,editParcialPS,editPorcentajeParcialPS;
    EditText editQuizT,editPorcentajeQuizT,editTallerT,editPorcentajeTallerT;
    EditText editParcialTT,editPorcentajeParcialTT,editParcialPT,editPorcentajeParcialPT;
    Button btnRegistrar,btnVerTodos,btnActualizar,btnEliminar;
    Spinner materia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_notas);
        mydb = new DataBaseHelperNotas(this);
        mydbm = new DataBaseHelper(this);
        materia = (Spinner)findViewById(R.id.spinnerMateria);
        editQuizP = (EditText)findViewById(R.id.editTextNotaQuizP);
        editPorcentajeQuizP=(EditText)findViewById(R.id.editTextPorcentajeQuizP);
        editTallerP = (EditText)findViewById(R.id.editTextNotaTallerP);
        editPorcentajeTallerP = (EditText)findViewById(R.id.editTextPorcentajeTallerP);
        editParcialPP = (EditText)findViewById(R.id.editTextNotaParcialPP);
        editPorcentajeParcialPP = (EditText)findViewById(R.id.editTextPorcentajeParcialPP);
        editParcialTP = (EditText)findViewById(R.id.editTextNotaParcialTP);
        editPorcentajeParcialTP = (EditText)findViewById(R.id.editTextPorcentajeParcialTP);
        editId = (EditText)findViewById(R.id.editTextId);

        editQuizS = (EditText)findViewById(R.id.editTextNotaQuizS);
        editPorcentajeQuizS=(EditText)findViewById(R.id.editTextPorcentajeQuizS);
        editTallerS = (EditText)findViewById(R.id.editTextNotaTallerS);
        editPorcentajeTallerS = (EditText)findViewById(R.id.editTextPorcentajeTallerS);
        editParcialPS = (EditText)findViewById(R.id.editTextNotaParcialPS);
        editPorcentajeParcialPS = (EditText)findViewById(R.id.editTextPorcentajeParcialPS);
        editParcialTS = (EditText)findViewById(R.id.editTextNotaParcialTS);
        editPorcentajeParcialTS = (EditText)findViewById(R.id.editTextPorcentajeParcialTS);

        editQuizT = (EditText)findViewById(R.id.editTextNotaQuizT);
        editPorcentajeQuizT=(EditText)findViewById(R.id.editTextPorcentajeQuizT);
        editTallerT = (EditText)findViewById(R.id.editTextNotaTallerT);
        editPorcentajeTallerT = (EditText)findViewById(R.id.editTextPorcentajeTallerT);
        editParcialPT = (EditText)findViewById(R.id.editTextNotaParcialPT);
        editPorcentajeParcialPT = (EditText)findViewById(R.id.editTextPorcentajeParcialPT);
        editParcialTT = (EditText)findViewById(R.id.editTextNotaParcialTT);
        editPorcentajeParcialTT = (EditText)findViewById(R.id.editTextPorcentajeParcialTT);

        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnVerTodos = (Button)findViewById(R.id.btnVerTodos);
        btnActualizar = (Button)findViewById(R.id.btnActualizar);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);
        obtenerMaterias();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,materias);
        materia.setAdapter(adaptador);
        Registrar();
        VerTodas();
        Actualizar();
        Eliminar();
    }


    public void onClick(View view){
        Intent miIntent = new Intent(RegistrarNotasActivity.this,MainActivity.class);
        startActivity(miIntent);
    }
    public void Registrar(){
        btnRegistrar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String quizP = editPorcentajeQuizP.getText().toString();
                        String tallerP = editPorcentajeTallerP.getText().toString();
                        String teoricoP = editPorcentajeParcialPP.getText().toString();
                        String practicoP =  editPorcentajeParcialTP.getText().toString();
                        String quizS = editPorcentajeQuizS.getText().toString();
                        String tallerS = editPorcentajeTallerS.getText().toString();
                        String teoricoS = editPorcentajeParcialPS.getText().toString();
                        String practicoS =  editPorcentajeParcialTS.getText().toString();
                        String quizT = editPorcentajeQuizT.getText().toString();
                        String tallerT = editPorcentajeTallerT.getText().toString();
                        String teoricoT = editPorcentajeParcialPT.getText().toString();
                        String practicoT =  editPorcentajeParcialTT.getText().toString();
                        String notaquizP = editQuizP.getText().toString();
                        String notatallerP = editTallerP.getText().toString();
                        String notapracticoP = editParcialPP.getText().toString();
                        String notateoricoP = editParcialTP.getText().toString();
                        String notaquizS = editQuizS.getText().toString();
                        String notatallerS = editTallerS.getText().toString();
                        String notapracticoS = editParcialPS.getText().toString();
                        String notateoricoS = editParcialTS.getText().toString();
                        String notaquizT = editQuizT.getText().toString();
                        String notatallerT = editTallerT.getText().toString();
                        String notapracticoT = editParcialPT.getText().toString();
                        String notateoricoT = editParcialTT.getText().toString();
                        boolean verificar = VerificarPorcentajes(Integer.parseInt(quizP),Integer.parseInt(tallerP),Integer.parseInt(teoricoP),Integer.parseInt(practicoP));
                        boolean verificar2 = VerificarPorcentajes(Integer.parseInt(quizS),Integer.parseInt(tallerS),Integer.parseInt(teoricoS),Integer.parseInt(practicoS));
                        boolean verificar3 = VerificarPorcentajes(Integer.parseInt(quizT),Integer.parseInt(tallerT),Integer.parseInt(teoricoT),Integer.parseInt(practicoT));
                        if(verificar == false){
                            Toast.makeText(RegistrarNotasActivity.this,"Los porcentajes del primer corte no son correctos",Toast.LENGTH_LONG).show();
                        }else if(verificar2 == false){
                            Toast.makeText(RegistrarNotasActivity.this,"Los porcentajes del segundo corte no son correctos",Toast.LENGTH_LONG).show();
                        }else if(verificar3 == false){
                            Toast.makeText(RegistrarNotasActivity.this,"Los porcentajes del tercer corte no son correctos",Toast.LENGTH_LONG).show();
                        }else{
                            double nota1 = calcularNota(Double.parseDouble(notaquizP),Integer.parseInt(quizP),Double.parseDouble(notatallerP),
                                    Integer.parseInt(tallerP),Double.parseDouble(notapracticoP),Integer.parseInt(practicoP),
                                    Double.parseDouble(notateoricoP),Integer.parseInt(teoricoP));
                            double nota2 = calcularNota(Double.parseDouble(notaquizS),Integer.parseInt(quizS),Double.parseDouble(notatallerS),
                                    Integer.parseInt(tallerS),Double.parseDouble(notapracticoS),Integer.parseInt(practicoS),
                                    Double.parseDouble(notateoricoS),Integer.parseInt(teoricoS));
                            double nota3 = calcularNota(Double.parseDouble(notaquizT),Integer.parseInt(quizT),Double.parseDouble(notatallerT),
                                    Integer.parseInt(tallerT),Double.parseDouble(notapracticoT),Integer.parseInt(practicoT),
                                    Double.parseDouble(notateoricoT),Integer.parseInt(teoricoT));
                            boolean esInsertado = mydb.insertarDatos(materia.getSelectedItem().toString(),String.valueOf(nota1),String.valueOf(nota2),String.valueOf(nota3));
                            if(esInsertado == true)

                                Toast.makeText(RegistrarNotasActivity.this,"Datos Insertados",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(RegistrarNotasActivity.this,"Error al insertar los datos",Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }


    public double calcularNota(double quiz,int porQuiz,double taller,int porTaller,double parcialP, int porParcialP,double parcialT, int porParcialT){
        double resultado = (quiz*porQuiz+taller*porTaller+parcialP*porParcialP+parcialT*porParcialT)/100;
        return resultado;
    }

    public boolean VerificarPorcentajes(int porQuiz, int porTaller, int porParcialT,int porParcialP){
        int sumatoria = porQuiz+porTaller+porParcialP+porParcialT;
        if(sumatoria==100) {
            return true;
        }else{
            return false;}
    }

    private void obtenerMaterias(){
        materias = new ArrayList<String>();
        Cursor res = mydbm.verTodos();
        materias.add("Seleccione");
        while(res.moveToNext()){
            materias.add(res.getString(1));
        }

    }
    public void VerTodas(){
        btnVerTodos.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res = mydb.verTodos();
                        if(res.getCount()==0){
                            showMessage("Error","no hay datos para mostrar");
                            return;
                        }else{
                            StringBuffer buffer = new StringBuffer();
                            while(res.moveToNext()){
                                buffer.append("Id: "+res.getString(0)+"\n");
                                buffer.append("Materia: "+res.getString(1)+"\n");
                                buffer.append("Nota Primer Corte: "+res.getString(2)+"\n");
                                buffer.append("Nota Segundo Corte:"+res.getString(3)+"\n");
                                buffer.append("Nota Tercer Corte:"+res.getString(4)+"\n");
                                buffer.append("Nota Final:"+((Double.parseDouble(res.getString(2)) * 30)+(Double.parseDouble(res.getString(3))*30)+(Double.parseDouble(res.getString(4))*30))/100+"\n\n");
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
                        double nota1 = calcularNota(Double.parseDouble(editQuizP.getText().toString()),Integer.parseInt(editPorcentajeQuizP.getText().toString()),Double.parseDouble(editTallerP.getText().toString()),
                                Integer.parseInt(editPorcentajeTallerP.getText().toString()),Double.parseDouble(editParcialPP.getText().toString()),Integer.parseInt(editPorcentajeParcialPP.getText().toString()),
                                Double.parseDouble(editParcialTP.getText().toString()),Integer.parseInt(editPorcentajeParcialTP.getText().toString()));
                        double nota2 = calcularNota(Double.parseDouble(editQuizS.getText().toString()),Integer.parseInt(editPorcentajeQuizS.getText().toString()),Double.parseDouble(editTallerS.getText().toString()),
                                Integer.parseInt(editPorcentajeTallerS.getText().toString()),Double.parseDouble(editParcialPS.getText().toString()),Integer.parseInt(editPorcentajeParcialPS.getText().toString()),
                                Double.parseDouble(editParcialTS.getText().toString()),Integer.parseInt(editPorcentajeParcialTS.getText().toString()));
                        double nota3 = calcularNota(Double.parseDouble(editQuizT.getText().toString()),Integer.parseInt(editPorcentajeQuizT.getText().toString()),Double.parseDouble(editTallerT.getText().toString()),
                                Integer.parseInt(editPorcentajeTallerT.getText().toString()),Double.parseDouble(editParcialPT.getText().toString()),Integer.parseInt(editPorcentajeParcialPT.getText().toString()),
                                Double.parseDouble(editParcialTT.getText().toString()),Integer.parseInt(editPorcentajeParcialTT.getText().toString()));
                        boolean esActualizado = mydb.actualizarDato(editId.getText().toString(),materia.getSelectedItem().toString(),
                                String.valueOf(nota1),String.valueOf(nota2),String.valueOf(nota3));
                        if(esActualizado == true)
                            Toast.makeText(RegistrarNotasActivity.this,"Datos actualizados",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(RegistrarNotasActivity.this,"Error al actualizar los datos",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    public void Eliminar(){
        btnEliminar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteRows = mydb.borrarDato(editId.getText().toString());
                        if (deleteRows > 0)
                            Toast.makeText(RegistrarNotasActivity.this,"Dato Eliminado",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(RegistrarNotasActivity.this,"Error al eliminar los datos",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}