package com.henrysuher.andriod.aplicacion2;

import android.animation.FloatArrayEvaluator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button mButton;
    TextView mTextoConcepto;
    TextView mTextoValor;
    TextView mTextoIngreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast mensaje=Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG);
        //mensaje.setText("ERROR");
        //mensaje.setDuration(Toast.LENGTH_LONG);
        mensaje.setGravity(Gravity.TOP,0,0);
        mensaje.show();
        mButton=(Button) findViewById(R.id.BottonRegistrar);
        mTextoConcepto=(TextView)findViewById(R.id.campoConcepto);
        mTextoValor=(TextView) findViewById(R.id.CampoValor);
        mTextoIngreso=(TextView)findViewById(R.id.CampoIngreso);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registro registro=new Registro();
                registro.setConcepto(mTextoConcepto.getText().toString());
                registro.setValor(Float.parseFloat(mTextoValor.getText().toString()));

            }
        });
    }


    public void miMetodo(View view) {

    }
}
