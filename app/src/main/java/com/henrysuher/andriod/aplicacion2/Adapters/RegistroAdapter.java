package com.henrysuher.andriod.aplicacion2.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henrysuher.andriod.aplicacion2.R;
import com.henrysuher.andriod.aplicacion2.Modelo.Registro;

import java.text.NumberFormat;
import java.util.List;

import static android.graphics.Color.*;

public class RegistroAdapter extends ArrayAdapter <Registro>{

    public RegistroAdapter(Context context, List<Registro> registros) {
        super(context, R.layout.list_item_registro,registros);
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View item=convertView;
        if(item==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            item=inflater.inflate(R.layout.list_item_registro,null);
        }

        Registro itemActual=getItem(position);

        TextView textoConcepto=(TextView)item.findViewById(R.id.textoConcepto);
        textoConcepto.setText(itemActual.getConcepto());

        //NumberFormat formateador=NumberFormat.getCurrencyInstance();
        String valor=itemActual.getValor().toString();
        TextView textoValor=(TextView)item.findViewById(R.id.textoValor);
        if (itemActual.isIngreso()){

            textoValor.setText("+"+itemActual.getValor());
        }else {

            textoValor.setText("-"+itemActual.getValor());
        }



        LinearLayout linearPricipal=(LinearLayout)item.findViewById(R.id.LinearPricipal);
        linearPricipal.setBackgroundColor(itemActual.isIngreso() ? GREEN: RED);

        return (item);
    }


}
