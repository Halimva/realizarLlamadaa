package com.temas.selectos.guardarpreferencias;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LlamadaActivity extends AppCompatActivity {

    private ListView lvLista;
    private TextView tvSelecciona;

    private String nombres [] = {"Halima", "Yazela", "Anabella"};
    private String telefonos [] = {"5550753619", "5540670346", "5517007779"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada);

        lvLista = (ListView)findViewById(R.id.lvLista);
        tvSelecciona = (TextView)findViewById(R.id.tvSelecciona);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.elemento_lista, nombres);
        lvLista.setAdapter(adapter);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvSelecciona.setText("El telefono de " + lvLista.getItemAtPosition(position) + " es " + telefonos[position]);
                if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+telefonos[position])));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acercade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.opcnAcercaDe:
                Toast.makeText(this, "Selecciona un número para marcar", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
