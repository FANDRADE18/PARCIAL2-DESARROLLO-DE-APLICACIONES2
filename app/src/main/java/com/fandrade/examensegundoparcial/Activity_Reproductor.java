package com.fandrade.examensegundoparcial;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.MediaController;
import android.widget.VideoView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class Activity_Reproductor extends AppCompatActivity {
    TextView txtcategoria, txtnombrerep, txtedadesrep,txtgenerorep,txttitulopelicula;
    ImageView perfil;
    String archivonombre1, archivoedad1, archivogenero1;
    ImageButton reproducir,pausa,regresar;
    VideoView verpelicula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        perfil = (ImageView) findViewById(R.id.imavperfil);
        txtnombrerep = (TextView) findViewById(R.id.txtnombrerepro);
        txtedadesrep = (TextView) findViewById(R.id.txtedadrepro);
        txtgenerorep = (TextView) findViewById(R.id.txtgenerorepro);
        txtcategoria = (TextView) findViewById(R.id.txtcategoriarepro);
        txttitulopelicula = (TextView) findViewById(R.id.txttitulopeli);

        reproducir =(ImageButton)findViewById(R.id.btnplay);
        pausa =(ImageButton)findViewById(R.id.btnpause);
        regresar =(ImageButton)findViewById(R.id.btnreturn);

        archivonombre1 = "NombreNetflix";
        archivoedad1 = "EdadNetflix";
        archivogenero1 = "GeneroNetflix";

        SharedPreferences preferences = getSharedPreferences("MISCATEGORIAS", Context.MODE_PRIVATE);
        txtcategoria.setText(preferences.getString("dato","no guardaste nada"));


        AbrirCamara();
        LeerNombre();
        LeerEdad();
        LeerGenero();




        String categorias = new String(txtcategoria.getText().toString());
        String caricatura = "CARICATURA";
        String terror = "TERROR";
        String romance = "ROMANCE";

        if(categorias.equals(caricatura)){

            Vercaricatura();
            String titulocaricatura = "MINIONS2: EL ORIGEN DE GRU";
            txttitulopelicula.setText(titulocaricatura);
        }

        if(categorias.equals(terror)){

            VerTerror();
            String titulocaricatura = "VOCES";
            txttitulopelicula.setText(titulocaricatura);
        }
        if(categorias.equals(romance)){

            VerRomance();
            String titulocaricatura = "EL STAND DE LOS BESOS 3";
            txttitulopelicula.setText(titulocaricatura);
        }





        reproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verpelicula.start();;
            }
        });

        pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verpelicula.pause();;
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "USTED A REGRESADO AL MENÚ ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), Menu_Activity.class);
                startActivityForResult(intent, 0);
            }
        });


    }
    private void AbrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            perfil.setImageBitmap(imgBitmap);
        }

    }
    //Leer los nombres
    private void LeerNombre() {
        String textoffilenombre = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(archivonombre1)));
            textoffilenombre = bufferedReader.readLine();
            bufferedReader.close();
            if (textoffilenombre != null) {
                txtnombrerep.setText(textoffilenombre);
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
                txtedadesrep.setText(textoffileedad);
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

    //Leer las generos
    private void LeerGenero() {
        String textoffilegenero = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(archivogenero1)));
            textoffilegenero = bufferedReader.readLine();
            bufferedReader.close();
            if (textoffilegenero != null) {
                txtgenerorep.setText(textoffilegenero);
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
    private void Vercaricatura(){
        Uri myuri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.minion2elorigendegru);
        verpelicula =(VideoView) findViewById(R.id.videoverpelicula);
        verpelicula.setVideoURI(myuri);
        verpelicula.setMediaController(new MediaController(this));
        verpelicula.requestFocus();

    }
    private void VerTerror(){
        Uri myuri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.voces);
        verpelicula =(VideoView) findViewById(R.id.videoverpelicula);
        verpelicula.setVideoURI(myuri);
        verpelicula.setMediaController(new MediaController(this));
        verpelicula.requestFocus();

    }
    private void VerRomance(){
        Uri myuri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.standdelosbesos3);
        verpelicula =(VideoView) findViewById(R.id.videoverpelicula);
        verpelicula.setVideoURI(myuri);
        verpelicula.setMediaController(new MediaController(this));
        verpelicula.requestFocus();
    }

}