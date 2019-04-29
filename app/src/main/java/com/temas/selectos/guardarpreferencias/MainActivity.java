package com.temas.selectos.guardarpreferencias;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.CoreComponentFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtUsuario;
    EditText edtContraseña;
    boolean Correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtContraseña = findViewById(R.id.edtContraseña);
        cargarPreferencias();
    }

    public void onClick(View v) {guardarPreferencias();}

    public void onClickAcceder(View v) {
        verificarDatos();
        if (Correcto == true){
            abrirActivityLlamada();
        }
    }

    private void guardarPreferencias(){
        //Declarando objeto y creando archivo credencial en modo privado para la aplicación
        SharedPreferences preferences = getSharedPreferences("credencial", Context.MODE_PRIVATE);
        String Usuario = edtUsuario.getText().toString();
        String Contraseña = edtContraseña.getText().toString();
        //Editor Objeto para editar el archivo XML
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario", Usuario);
        editor.putString("contraseña", Contraseña);
        //Cierra la etiqueta de contenido
        editor.commit();
        Toast.makeText(this, "Datos guardados \n " +Usuario +"\n" + Contraseña, Toast.LENGTH_LONG).show();
    }

    private void cargarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("credencial", Context.MODE_PRIVATE);
        //Extraer información del archivo XML
        String Usuario = preferences.getString("usuario", "No existe información");
        String Contraseña = preferences.getString("contraseña", "No existe información");
        edtUsuario.setText(Usuario);
        edtContraseña.setText(Contraseña);
    }

    private void verificarDatos(){
        SharedPreferences preferences = getSharedPreferences("credencial", Context.MODE_PRIVATE);
        String Usuario = preferences.getString("usuario", "No existe información");
        String Contraseña = preferences.getString("contraseña", "No existe información");

        String tmpUsuario = edtUsuario.getText().toString();
        String tmpContraseña = edtContraseña.getText().toString();

        if (tmpUsuario.equals(Usuario) && tmpContraseña.equals(Contraseña)){
            Toast.makeText(this, "Accediste como Jesusa", Toast.LENGTH_LONG).show();
            Correcto = true;
        }
        else {
            Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
            Correcto = false;
        }
    }

    public void abrirActivityLlamada(){
        Intent intenta_Activity_lamada = new Intent(this, LlamadaActivity.class);
        startActivity(intenta_Activity_lamada);
    }


}