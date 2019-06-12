package com.henrysuher.andriod.aplicacion2;

import android.animation.FloatArrayEvaluator;
import android.annotation.SuppressLint;
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

import org.w3c.dom.Text;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Button mButton;
    TextView mTextoConcepto;
    TextView mTextoValor;
    RadioGroup mGrupoBotones;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mButton=(Button) findViewById(R.id.BottonRegistrar);
        mTextoConcepto=(TextView)findViewById(R.id.campoConcepto);
        mTextoValor=(TextView) findViewById(R.id.CampoValor);
        mGrupoBotones=(RadioGroup)findViewById(R.id.grupoBotones);

        iniciarlizarFirebase();

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
                        Toast mRegistro=Toast.makeText(getApplicationContext(),"Entrada registrada",Toast.LENGTH_LONG);
                        mRegistro.setGravity(Gravity.TOP,0,0);
                        mRegistro.show();

                        Book book=new Book(concepto,valor);
                        book.save();

                    }else{
                        Registro registro=new Registro();
                        registro.setId(UUID.randomUUID().toString());
                        registro.setConcepto(concepto);
                        registro.setValor(valor);
                        registro.setIngreso(false);
                        databaseReference.child("Registro").child(registro.getId()).setValue(registro);
                        limpiarCampos();
                        Toast mRegistro=Toast.makeText(getApplicationContext(),"Salida registrada",Toast.LENGTH_LONG);
                        mRegistro.setGravity(Gravity.TOP,0,0);
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



}
