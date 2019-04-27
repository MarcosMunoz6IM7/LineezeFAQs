package edu.mx.ipn.lineezefaqs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Alta extends AppCompatActivity {

    EditText id;
    EditText pre;
    EditText res;
    Button bot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        Intent i = getIntent();

        id = (EditText) findViewById(R.id.Id);
        pre = (EditText) findViewById(R.id.pregunta);
        res = (EditText) findViewById(R.id.respuesta);
        bot = (Button) findViewById(R.id.botonAlta);

        if(i.getExtras() != null) {
            int idaux = i.getExtras().getInt("id");
            String pregaux = i.getExtras().getString("pregunta");
            String respaux = i.getExtras().getString("respuesta");
            bot.setText("Modificar");
            bot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GuardaFaq(true);
                }
            });
            id.setText(Integer.toString(idaux));
            id.setEnabled(false);
            pre.setText(pregaux);
            res.setText(respaux);
        }
        else{
            bot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GuardaFaq(false);
                }
            });
        }



    }

    public void GuardaFaq(Boolean pasa){

        int vid;

        if(id.getText().toString().isEmpty()){
            vid = 0;
        }
        else {
            vid = Integer.parseInt(id.getText().toString());
        }

        String vpregunta = pre.getText().toString();
        String vrespuesta = res.getText().toString();

        if(vid==0 || vpregunta.isEmpty() || vrespuesta.isEmpty()){
            Toast.makeText(this,"No dejes campos en blanco",Toast.LENGTH_SHORT).show();
        }
        else{
            BaseManager admin = new BaseManager(this, null, null, 1);
            Faq faux = new Faq(vid, vpregunta, vrespuesta);

            if(pasa){
                Toast.makeText(this, admin.Modifica(faux), Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, admin.Alta(faux), Toast.LENGTH_LONG).show();
            }

            Intent i = new Intent(this, Menu.class);
            startActivity(i);
            finish();
        }
    }

}
