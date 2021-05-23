package com.example.imagedatabase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.imagedatabase.Params.Params;
import com.example.imagedatabase.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "(" + Params.KEY_ID +
                " INTEGER PRIMARY KEY, " + Params.KEY_NAME + " TEXT, " + Params.BRANCH +
                " TEXT, " + Params.HOME + " TEXT, " + Params.DATE + " TEXT, " + Params.ABOUT
                + " TEXT, " + Params.IMAGE + " TEXT " + ")";
        // Log.d("dbdb", "Query being run is : " + create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.BRANCH, contact.getBranch());
        values.put(Params.HOME, contact.getHome());
        values.put(Params.DATE, contact.getDate());
        values.put(Params.ABOUT, contact.getAbout());
        values.put(Params.IMAGE, contact.getImage());

        db.insert(Params.TABLE_NAME, null, values);
        Log.d("dbdb", "Successfully inserted!");
        db.close();
    }

    public List<Contact> AllContacts() {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Params.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                //contact.setPhoneNumber(cursor.getString(2));
                contact.setBranch(cursor.getString(2));
                contact.setHome(cursor.getString(3));
                contact.setDate(cursor.getString(4));
                contact.setAbout(cursor.getString(5));
                contact.setImage(cursor.getBlob(6));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Params.KEY_NAME, contact.getName());
//        values.put(Params.KEY_PHONE, contact.getPhoneNumber());
        values.put(Params.BRANCH, contact.getBranch());
        values.put(Params.HOME, contact.getHome());
        values.put(Params.DATE, contact.getDate());
        values.put(Params.ABOUT, contact.getAbout());
        values.put(Params.IMAGE, contact.getImage());

        return db.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContactByID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int getCount() {
        String query = "SELECT  * FROM " + Params.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

}
