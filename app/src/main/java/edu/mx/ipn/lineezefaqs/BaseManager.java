package edu.mx.ipn.lineezefaqs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BaseManager extends SQLiteOpenHelper {

    public BaseManager(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "administrafaqs", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table faqs(id int primary key, pregunta text,respuesta text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ContentValues Traduce(Faq f){

        ContentValues c = new ContentValues();

        c.put("id",f.getId());
        c.put("pregunta",f.getPregunta());
        c.put("respuesta",f.getRespuesta());

        return c;
    }

    public String Alta(Faq f){
        String msj;

        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.insertOrThrow("faqs",null, this.Traduce(f));
            msj = "Se guardo correctamente";
        }catch (Exception e){
            msj = "No se pudo guardar";
        }

        db.close();

        return msj;
    }

    public String Modifica(Faq f){
        String msj;

        SQLiteDatabase db = this.getWritableDatabase();

        int cambiados = db.update("faqs",Traduce(f),"id="+ f.getId(),null);

        if(cambiados == 1){
            msj = "FAQ modificado correctamente";
        }
        else {
            if (cambiados > 0) {
                msj = "Se han modificado " + cambiados + "FAQs";
            }
            else{
                msj = "No existen FAQs con tal ID";
            }
        }

        db.close();

        return msj;
    }

    public String Borrar(int id){
        String msj;

        SQLiteDatabase db = this.getWritableDatabase();
        int borrados = db.delete("faqs","id="+id ,null);
        if(borrados == 1){
            msj = "FAQ eliminado correctamente";
        }
        else {
            if (borrados > 0) {
                msj = "Se han eliminado " + borrados + "FAQs";
            }
            else{
                msj = "No existen FAQs con tal ID";
            }
        }
        db.close();
        return msj;
    }

    public ArrayList traeTodo(){
        ArrayList<Faq> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor faqs = db.rawQuery("select * from faqs",null);

        if(faqs.moveToFirst()){
            do{
                Faq f = new Faq(faqs.getInt(0),faqs.getString(1),faqs.getString(2));
                list.add(f);
            }while (faqs.moveToNext());
            db.close();
        }

        return list;
    }
}
