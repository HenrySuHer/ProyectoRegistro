package com.henrysuher.andriod.aplicacion2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.henrysuher.andriod.aplicacion2.Modelo.Book;
import com.henrysuher.andriod.aplicacion2.Modelo.Registro;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Button mButton;
    Button mButtonSugarORM;
    TextView mTextoConcepto;
    TextView mTextoValor;
    RadioGroup mGrupoBotones;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SharedPreferences misPreferencias;

    final int MI_CODIGO_PERSMISO=100;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mButton=(Button) findViewById(R.id.BottonRegistrar);
        mTextoConcepto=(TextView)findViewById(R.id.campoConcepto);
        mTextoValor=(TextView) findViewById(R.id.CampoValor);
        mGrupoBotones=(RadioGroup)findViewById(R.id.grupoBotones);
        mButtonSugarORM=(Button)findViewById(R.id.BotonRegistrarORM);

        /*int entero=misPreferencias.getInt("MiEntero",10);
        Boolean recodarUsuario=misPreferencias.getBoolean("RecordarUsuario",false);

        SharedPreferences.Editor editarPreferencias=misPreferencias.edit();
        editarPreferencias.putInt("MiEntero",50);
        editarPreferencias.putBoolean("RecodarUsiario",true);
        editarPreferencias.commit();
        */
        iniciarlizarFirebase();

        //Accion del boton de FIREBASE
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String concepto=mTextoConcepto.getText().toString();
                String valor=mTextoValor.getText().toString();
                int opcionSeleccionada=mGrupoBotones.getCheckedRadioButtonId();
                if (concepto.isEmpty()||valor.isEmpty()){
                    validacion();
                }else{
                    if (opcionSeleccionada==R.id.RbotonIngreso){
                        Registro registro=new Registro();
                        registro.setId(UUID.randomUUID().toString());
                        registro.setConcepto(concepto);
                        registro.setValor(valor);
                        registro.setIngreso(true);
                        databaseReference.child("Registro").child(registro.getId()).setValue(registro);
                        limpiarCampos();
                        Toast mRegistro=Toast.makeText(getApplicationContext(),"Entrada registrada en firebase",Toast.LENGTH_LONG);
                        mRegistro.setGravity(Gravity.BOTTOM,0,0);
                        mRegistro.show();
                    }else{
                        Registro registro=new Registro();
                        registro.setId(UUID.randomUUID().toString());
                        registro.setConcepto(concepto);
                        registro.setValor(valor);
                        registro.setIngreso(false);
                        databaseReference.child("Registro").child(registro.getId()).setValue(registro);
                        limpiarCampos();
                        Toast mRegistro=Toast.makeText(getApplicationContext(),"Salida registrada en firebase",Toast.LENGTH_LONG);
                        mRegistro.setGravity(Gravity.BOTTOM,0,0);
                        mRegistro.show();
                    }
                }
            }
        });

        mButtonSugarORM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String concepto=mTextoConcepto.getText().toString();
                String valor=mTextoValor.getText().toString();

                int opcionSeleccionada=mGrupoBotones.getCheckedRadioButtonId();
                if(concepto.isEmpty()||valor.isEmpty()){
                    validacion();
                }else {
                    if(opcionSeleccionada==R.id.RbotonIngreso){
                        Registro registro=new Registro();
                        registro.setId(UUID.randomUUID().toString());
                        registro.setConcepto(concepto);
                        registro.setValor(valor);
                        registro.setIngreso(true);
                        //databaseReference.child("Registro").child(registro.getId()).setValue(registro);
                        limpiarCampos();
                        Toast mRegistro=Toast.makeText(getApplicationContext(),"Entrada registrada en SugarROM",Toast.LENGTH_LONG);
                        mRegistro.setGravity(Gravity.BOTTOM,0,0);
                        mRegistro.show();

                        Book book=new Book(concepto,valor);
                        book.save();
                    }else{
                        Registro registro=new Registro();
                        registro.setId(UUID.randomUUID().toString());
                        registro.setConcepto(concepto);
                        registro.setValor(valor);
                        registro.setIngreso(false);
                        //databaseReference.child("Registro").child(registro.getId()).setValue(registro);
                        limpiarCampos();
                        Toast mRegistro=Toast.makeText(getApplicationContext(),"Salida registrada en SugarORM",Toast.LENGTH_LONG);
                        mRegistro.setGravity(Gravity.BOTTOM,0,0);
                        mRegistro.show();


                        Book book=new Book(concepto,valor);
                        book.save();
                    }

                }

            }
        });
    }

    private void limpiarCampos(){
        mTextoConcepto.setText("");
        mTextoValor.setText("");
        mTextoConcepto.setActivated(true);
    }

    private void validacion() {
        String concepto=mTextoConcepto.getText().toString();
        String valor=mTextoValor.getText().toString();

        if (concepto.isEmpty()){
            mTextoConcepto.setError("Ingresar dato");
        }else if(valor.isEmpty()){
            mTextoValor.setError("Ingresar dato");
        }
    }

    private void iniciarlizarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    public void verificarPermiso(){
        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                new AlertDialog.Builder(this)
                        .setMessage("Es necesario permitir el uso de escritura en el almacenamiento")
                        .setPositiveButton("Acepto", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pedirPermiso();
                            }
                        })
                        .setNegativeButton("No acepto", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"Has rechazado",Toast.LENGTH_LONG).show();
                            }
                        });

            }
        }
    }

    private void pedirPermiso() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MI_CODIGO_PERSMISO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        switch (requestCode){
            case MI_CODIGO_PERSMISO:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    crearArchivoEnMemoriaSD();
                }else{
                    verificarPermiso();
                }
        }
        return;
    }

    private void crearArchivoEnMemoriaSD() {
    }
}
