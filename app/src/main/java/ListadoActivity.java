import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.henrysuher.andriod.aplicacion2.Adapters.RegistroAdapter;
import com.henrysuher.andriod.aplicacion2.Modelo.Registro;
import com.henrysuher.andriod.aplicacion2.R;

import java.util.List;

public class ListadoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        setContentView(R.layout.activity_listado);
        List<Registro> datos= Registro.listAll(Registro.class);

        RegistroAdapter adapter= new RegistroAdapter(this,datos);

        ListView listaRegistro=(ListView)findViewById(R.id.listadoRegistros);

        listaRegistro.setAdapter(adapter);
        */

    }
}
