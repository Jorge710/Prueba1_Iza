package com.example.prueba1_iza;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class activity_diarias extends AppCompatActivity {

    private EditText et1,et2;

    private ArrayList<String> actividadesRealizar;
    private ArrayAdapter<String> adaptador1;
    private ListView lv1;
    //private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarias);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);

        actividadesRealizar=new ArrayList<String>();

        adaptador1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,actividadesRealizar);
        lv1=(ListView)findViewById(R.id.listView);
        lv1.setAdapter(adaptador1);

        et1=(EditText)findViewById(R.id.editText);

        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion=i;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(activity_diarias.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Quiere ELIMINAR esta actividad ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        actividadesRealizar.remove(posicion);
                        adaptador1.notifyDataSetChanged();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

                return false;
            }
        });
    }

    public void agregar(View v) {
        if(et1.getText().toString().equals("")){

            System.out.println("Ingrese una ctividad!");
            String titulo = "No se puede guardar";
            String mensajeAlerta= "Debe ingresar una actividad.";
            alerta(titulo,mensajeAlerta);
        }else {
            actividadesRealizar.add(et1.getText().toString());
            adaptador1.notifyDataSetChanged();
            et1.setText("");
            Toast.makeText(this, "Realizando",
                    Toast.LENGTH_SHORT).show();

            String nomarchivo = "actividades.txt";
            String contenido = String.valueOf(actividadesRealizar);
            System.out.println("contenido del array: "+contenido);
            try {
                File tarjeta = Environment.getExternalStorageDirectory();
                Toast.makeText(this,tarjeta.getAbsolutePath(),Toast.LENGTH_LONG).show();
                File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
                OutputStreamWriter osw = new OutputStreamWriter(
                        new FileOutputStream(file));
                osw.write(contenido);
                osw.flush();
                osw.close();
                Toast.makeText(this, "Realizado se guardo en la memoria del telefono =)",
                        Toast.LENGTH_SHORT).show();
                //et1.setText("");
                //et2.setText("");
            } catch (IOException ioe) {
                Toast.makeText(this, "No se pudo grabar",
                        Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void alerta(String titulo, String cadena) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(cadena);
        dialogBuilder.setCancelable(true).setTitle(titulo);
        dialogBuilder.create().show();
    }




    public void recuperar(View v) {
        String nomarchivo = et1.getText().toString();
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
        try {
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader archivo = new InputStreamReader(fIn);
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String todo = "";
            while (linea != null) {
                todo = todo + linea + " ";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            et2.setText(todo);

        } catch (IOException e) {
            Toast.makeText(this, "No se pudo leer",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
