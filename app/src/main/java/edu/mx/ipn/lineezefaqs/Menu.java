package edu.mx.ipn.lineezefaqs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void RedirigeAltas(View v){
        Intent i = new Intent(this, Alta.class);
        startActivity(i);
    }

    public void RedirigeFaqs(View v){
        Intent i = new Intent(this, MuestraFaqs.class);
        startActivity(i);
    }

    //Aqui falta para los otros 1 botones
}
