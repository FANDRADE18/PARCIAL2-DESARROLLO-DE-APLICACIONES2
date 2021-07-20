package com.fandrade.examensegundoparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import android.content.SharedPreferences;


public class Menu_Activity extends AppCompatActivity {

    TextView categoria, txtnombres, txtedades;
    ImageView desplegarmenu, caricatura, terror, romance;
    Button btncaricaturas, btnterrores, btnromances,aceptar,cancelar;

    String archivonombre1, archivoedad1, archivogenero1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        categoria = (TextView) findViewById(R.id.txtcategorias);
        desplegarmenu = (ImageView) findViewById(R.id.imagevmenu);
        caricatura = (ImageView) findViewById(R.id.imagcaricatura);
        terror = (ImageView) findViewById(R.id.imagevterror);
        romance = (ImageView) findViewById(R.id.imagevromance);
        btncaricaturas = (Button) findViewById(R.id.btncaricatura);
        btnterrores = (Button) findViewById(R.id.btnterror);
        btnromances = (Button) findViewById(R.id.btnromance);
        txtnombres = (TextView) findViewById(R.id.txtnombre);
        txtedades = (TextView) findViewById(R.id.txtedad);








        archivonombre1 = "NombreNetflix";
        archivoedad1 = "EdadNetflix";
        archivogenero1 = "GeneroNetflix";


        LeerNombre();
        LeerEdad();

        String edad = txtedades.getText().toString();
        int num = Integer.parseInt(edad);

        if(num<12){

            btnterrores.setEnabled(false);
            btnromances.setEnabled(false);
            Toast.makeText(getApplicationContext(), "ERES MENOR DE 12 AÑOS, SOLO TIENES ACCESO A LA CATEGORÍA: CARICATURA", Toast.LENGTH_LONG).show();


        }

        if(num>=12&&num<18){

            btnromances.setEnabled(false);
            Toast.makeText(getApplicationContext(), "ERES MAYOR DE 12 AÑOS Y MENOR DE 18 AÑOS, SOLO TIENES ACCESO A LAS CATEGORÍAS: CARICATURA Y TERROR", Toast.LENGTH_LONG).show();


        }
        if(num>=18){

            Toast.makeText(getApplicationContext(), "ERES MAYOR DE 18 AÑOS, TIENES ACCESO A TODAS LAS CATEGORÍAS", Toast.LENGTH_LONG).show();


        }




        desplegarmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoria.getVisibility() == View.VISIBLE) {
                    categoria.setVisibility(View.INVISIBLE);
                    btncaricaturas.setVisibility(View.INVISIBLE);
                    caricatura.setVisibility(View.INVISIBLE);
                    btnterrores.setVisibility(View.INVISIBLE);
                    terror.setVisibility(View.INVISIBLE);
                    btnromances.setVisibility(View.INVISIBLE);
                    romance.setVisibility(View.INVISIBLE);

                } else {
                    categoria.setVisibility(View.VISIBLE);
                    btncaricaturas.setVisibility(View.VISIBLE);
                    caricatura.setVisibility(View.VISIBLE);
                    btnterrores.setVisibility(View.VISIBLE);
                    terror.setVisibility(View.VISIBLE);
                    btnromances.setVisibility(View.VISIBLE);
                    romance.setVisibility(View.VISIBLE);
                }

            }
        });

        btncaricaturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dato =btncaricaturas.getText().toString();
                //se crea el archivo
                SharedPreferences preferences = getSharedPreferences("MISCATEGORIAS",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("dato",dato);
                editor.commit();



                mostrarDialogoTomarFoto();
            }
        });

        btnterrores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dato =btnterrores.getText().toString();
                //se crea el archivo
                SharedPreferences preferences = getSharedPreferences("MISCATEGORIAS",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("dato",dato);
                editor.commit();


                mostrarDialogoTomarFoto();
            }
        });

        btnromances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dato =btnromances.getText().toString();
                //se crea el archivo
                SharedPreferences preferences = getSharedPreferences("MISCATEGORIAS",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("dato",dato);
                editor.commit();


                mostrarDialogoTomarFoto();
            }
        });
    }

    //Leer los nombres
    private void LeerNombre() {
        String textoffilenombre = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(archivonombre1)));
            textoffilenombre = bufferedReader.readLine();
            bufferedReader.close();
            if (textoffilenombre != null) {
                txtnombres.setText(textoffilenombre);
               // Toast.makeText(getApplicationContext(), "Contenido Leido Con Exito", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "El Archivo Esta Vacio", Toast.LENGTH_SHORT).show();


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    //Leer las edades
    private void LeerEdad() {
        String textoffileedad = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(archivoedad1)));
            textoffileedad = bufferedReader.readLine();
            bufferedReader.close();
            if (textoffileedad != null) {
                txtedades.setText(textoffileedad);
                //Toast.makeText(getApplicationContext(), "Contenido Leido Con Exito", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "El Archivo Esta Vacio", Toast.LENGTH_SHORT).show();


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    private void mostrarDialogoTomarFoto() {
        AlertDialog.Builder alert = new AlertDialog.Builder( Menu_Activity.this);
        final View customlayout= getLayoutInflater().inflate(R.layout.activity_dialogo_foto, null);
        alert.setView(customlayout);

        aceptar= customlayout.findViewById(R.id.btnaceptar);
        cancelar= customlayout.findViewById(R.id.btncancelar);




        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "USTED A ACEPTADO ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), Activity_Reproductor.class);
                startActivityForResult(intent, 0);





            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Toast.makeText(getApplicationContext(), "USTED A CANCELADO ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), Menu_Activity.class);
                startActivityForResult(intent, 0);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }





}