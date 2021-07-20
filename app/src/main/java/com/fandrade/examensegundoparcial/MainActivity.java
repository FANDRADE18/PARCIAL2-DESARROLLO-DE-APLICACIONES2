package com.fandrade.examensegundoparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    Button dialogopersonalizado,guardar;
    EditText nombre,edad,genero;

    String archivonombre,archivoedad,archivogenero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogopersonalizado = (Button)findViewById(R.id.btninicio);

        archivonombre="NombreNetflix";
        archivoedad="EdadNetflix";
        archivogenero="GeneroNetflix";

        dialogopersonalizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoPersonalizado();
            }
        });
    }

    private void mostrarDialogoPersonalizado() {
        AlertDialog.Builder alert = new AlertDialog.Builder( MainActivity.this);
        final View customlayout= getLayoutInflater().inflate(R.layout.activity_dialogopersonalizado, null);
        alert.setView(customlayout);
        nombre= customlayout.findViewById(R.id.edittxt_nombre);
        edad= customlayout.findViewById(R.id.edittxt_edad);
        genero= customlayout.findViewById(R.id.edittxt_genero);
        guardar= customlayout.findViewById(R.id.btnaceptar);



        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = new String(nombre.getText().toString());
                String eda = new String(edad.getText().toString());
                String gene = new String(genero.getText().toString());

                String nombres = "";
                String edades = "";
                String generos = "";

                if(!nom.equals(nombres)&&!eda.equals(edades)&&!gene.equals(generos)){

                    GuardarNombre();
                    GuardarEdad();
                    GuardarGenero();

                    Toast.makeText(getApplicationContext(), "DATOS GUARDADOS EXITOSAMENTE ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), Menu_Activity.class);
                    startActivityForResult(intent, 0);
                }else{
                    Toast.makeText(getApplicationContext(), "FALTA ALGÚN CAMPO POR CAPTURAR", Toast.LENGTH_SHORT).show();
                }



            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    public void GuardarNombre(){

        try {
            OutputStreamWriter nombrenetflix = new OutputStreamWriter(openFileOutput(archivonombre, Context.MODE_PRIVATE));
            nombrenetflix.write(nombre.getText().toString());


            nombrenetflix.close();




        } catch (Exception e) {
            Log.e("NombreNetflix", "Error al escribir archivo en la memoria interna");
        }


    }


    public void GuardarEdad(){

        try {
            OutputStreamWriter edadnetflix = new OutputStreamWriter(openFileOutput(archivoedad, Context.MODE_PRIVATE));
            edadnetflix.write(edad.getText().toString());

            edadnetflix.close();




        } catch (Exception e) {
            Log.e("EdadNetflix", "Error al escribir archivo en la memoria interna");
        }


    }


    public void GuardarGenero(){

        try {
            OutputStreamWriter generonetflix = new OutputStreamWriter(openFileOutput(archivogenero, Context.MODE_PRIVATE));
            generonetflix.write(genero.getText().toString());

            generonetflix.close();




        } catch (Exception e) {
            Log.e("GeneroNetflix", "Error al escribir archivo en la memoria interna");
        }


    }

}