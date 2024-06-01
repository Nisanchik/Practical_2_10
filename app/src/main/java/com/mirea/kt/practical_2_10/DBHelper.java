package com.mirea.kt.practical_2_10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import java.util.ArrayList;

public class DBHelper {
    private SQLiteOpenHelper sqLiteHelper;

    public DBHelper(SQLiteOpenHelper sqLiteHelper){
        this.sqLiteHelper = sqLiteHelper;
    }

    public boolean saveTelephoneToDatabase(Telephone telephone){
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("model", telephone.getModel());
        cv.put("serialNumber", telephone.getSerialNumber());
        cv.put("price", telephone.getPrice());

        long rowId = db.insert("TABLE_TELEPHONES",null,cv);
        cv.clear();
        db.close();
        return rowId != -1;
    }

    public ArrayList<Telephone> loadAllTelephonesFromDatabase(){
        ArrayList<Telephone> telephones = new ArrayList<>();
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        Cursor dbCursor = db.query("TABLE_TELEPHONES", null, null, null,null,null,null);
        if (dbCursor.moveToFirst()){
            do {
                String model = dbCursor.getString(dbCursor.getColumnIndexOrThrow("model"));
                String serialNumber = dbCursor.getString(dbCursor.getColumnIndexOrThrow("serialNumber"));
                int price = dbCursor.getInt(dbCursor.getColumnIndexOrThrow("price"));

                telephones.add(new Telephone(model, serialNumber, price));
            }while (dbCursor.moveToNext());
        }
        dbCursor.close();
        db.close();
        return telephones;
    }
}
