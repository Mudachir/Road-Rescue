package com.example.roadrescue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorSpace;
import android.provider.SyncStateContract;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SavedContactsHelper extends SQLiteOpenHelper {


    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+SavedContacts.TABLE_NAME;



    private Context context;
    public SavedContactsHelper(@Nullable Context context) {
        super(context, SavedContacts.DATABASE_NAME, null, SavedContacts.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Toast.makeText(context, "On create is working", Toast.LENGTH_SHORT).show();

            sqLiteDatabase.execSQL(SavedContacts.CREATE_TABLE);

        }catch (Exception e)
        {
           Toast.makeText(context, "Exception:"+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {
            Toast.makeText(context, "On upgrade is working", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }catch (Exception e)
        {
            Toast.makeText(context, "Exception:"+e, Toast.LENGTH_SHORT).show();
        }



    }

    public long addContact(String name, String phone, String email){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SavedContacts.CONTACT_NAME, name);
        cv.put(SavedContacts.CONTACT_PHONE, phone);
        cv.put(SavedContacts.CONTACT_EMAIL, email);

        long id = sqLiteDatabase.insert(SavedContacts.TABLE_NAME, null, cv);

        sqLiteDatabase.close();

        return id;


    }

    public void editContact(String id,String name, String phone, String email){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SavedContacts.CONTACT_NAME, name);
        cv.put(SavedContacts.CONTACT_PHONE, phone);
        cv.put(SavedContacts.CONTACT_EMAIL, email);

       sqLiteDatabase.update(SavedContacts.TABLE_NAME, cv, SavedContacts.CONTACT_ID+" =? ", new String[]{id});

      sqLiteDatabase.close();




    }


    public void deleteContact(String id){

        SQLiteDatabase db = getWritableDatabase();


        db.delete(SavedContacts.TABLE_NAME, SavedContacts.CONTACT_ID+" =? ",new String[]{id});
        db.close();
    }




    public ArrayList<ModelContact> getAllData(){

        ArrayList<ModelContact> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+ SavedContacts.TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                ModelContact modelContact =new ModelContact(
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(SavedContacts.CONTACT_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(SavedContacts.CONTACT_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(SavedContacts.CONTACT_PHONE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(SavedContacts.CONTACT_EMAIL))

                );
                arrayList.add(modelContact);


            }while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }
}
