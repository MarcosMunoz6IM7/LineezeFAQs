package edu.mx.ipn.lineezefaqs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MuestraFaqs extends AppCompatActivity {

    LinearLayout sv;
    Context c;
    LinearLayout lTodo, lBotones;
    android.support.v7.widget.CardView card;
    TextView tvPreg, tvResp;
    Button btnMod, btnBorr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_faqs);
        sv = (LinearLayout) findViewById(R.id.layaut);
        c = sv.getContext();
        CreaCards();
    }

    private void CreaCards() {
        BaseManager bm = new BaseManager(this,null,null,1);

        ArrayList<Faq> faqs = bm.traeTodo();

        if(!faqs.isEmpty()){
            for(int i=0; i < faqs.size();i++){
                CreaCard(faqs.get(i));
            }
        }else {
            Toast.makeText(this,"No existen FAQs registrados", Toast.LENGTH_SHORT).show();
        }
    }

    private void CreaCard(Faq f) {
            CreaCarta();
            CreaLinearTodo();
            InsertaPregunta(f.getPregunta());
            InsertaRespuesta(f.getRespuesta());
            CreaLinearBotones(f);
            card.addView(lTodo);
            sv.addView(card);
    }

    private void CreaCarta() {
        card = new android.support.v7.widget.CardView(c);
        card.setBackgroundColor(Color.parseColor("#BDBDBD"));
        /*LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );
        card.setLayoutParams(param);*/
    }

    private void CreaLinearBotones(final Faq f) {
        lBotones = new LinearLayout(c);
        lBotones.setOrientation(LinearLayout.HORIZONTAL);
        lBotones.setWeightSum(2);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );

        btnBorr = new Button(c);
        btnBorr.setText("Eliminar");
        btnBorr.setLayoutParams(param);
        final Context cF = this;
        btnBorr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseManager db = new BaseManager(cF,null,null,1);
                Toast.makeText(cF,db.Borrar(f.getId()),Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        });
        lBotones.addView(btnBorr);

        btnMod = new Button(c);
        btnMod.setText("Modificar");
        btnMod.setLayoutParams(param);
        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("id",f.getId());
                b.putString("pregunta",f.getPregunta());
                b.putString("respuesta",f.getRespuesta());
                Intent i = new Intent(cF,Alta.class);
                i.putExtras(b);
                startActivity(i);
            }
        });
        lBotones.addView(btnMod);

        lTodo.addView(lBotones);
    }

    private void InsertaRespuesta(String pregunta) {
        tvResp = new TextView(c);
        tvResp.setText(pregunta);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        tvResp.setLayoutParams(param);

        lTodo.addView(tvResp);
    }

    private void InsertaPregunta(String respuesta) {
        tvPreg = new TextView(c);
        tvPreg.setText(respuesta);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        tvPreg.setLayoutParams(param);

        tvPreg.setTextSize(18);
        tvPreg.setTypeface(null, Typeface.BOLD);
        lTodo.addView(tvPreg);
    }

    private void CreaLinearTodo() {
        lTodo = new LinearLayout(c);
        lTodo.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        param.setMargins(50,15,50,15);

        lTodo.setLayoutParams(param);
    }


}

/* Lo del xml

<android.support.v7.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

        <LinearLayout style="@style/LinearCard">

            <TextView
                android:id="@+id/textView"
                android:text="Pregunta"
                style="@style/pregunta" />

            <TextView
                android:id="@+id/textView2"
                android:text="Respuesta"
                style="@style/respuesta" />

            <LinearLayout style="@style/LinearBtns">

                <Button
                    android:id="@+id/button3"
                    android:text="Modificar"
                    style="@style/btnModBor" />

                <Button
                    android:id="@+id/button2"
                    android:text="Eliminar"
                    style="@style/btnModBor"/>
            </LinearLayout>

        </LinearLayout>

    </android.support.v7.widget.CardView>

* */
