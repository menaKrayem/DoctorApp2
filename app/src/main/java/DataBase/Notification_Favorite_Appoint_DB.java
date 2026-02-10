package DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import models.Appoint_Model;
import models.Notification_Model;
import models.Tips_Model;

public class Notification_Favorite_Appoint_DB extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public static final String DATABASE_NAME = "DoctorApp.db";
    public static final int DATABASE_VERSION = 1;

    public Notification_Favorite_Appoint_DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Tips_Model.CREATE_TABLE);
        sqLiteDatabase.execSQL(Notification_Model.CREATE_TABLE);
        sqLiteDatabase.execSQL(Appoint_Model.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tips_Model.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Notification_Model.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Appoint_Model.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
// الدوال الحاصة للمفضلة النصائح
    public boolean insertFavorite(String title, String image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tips_Model.TITLE, title);
        contentValues.put(Tips_Model.IMAGE, image);
        long count = db.insert(Tips_Model.TABLE_NAME, null, contentValues);
        return count > 0;
    }
    @SuppressLint("Range")
    public ArrayList<Tips_Model> getAllFavorite() {
        ArrayList<Tips_Model> tips = new ArrayList<>();
        String sqlquery = "SELECT * FROM " + Tips_Model.TABLE_NAME + " ORDER BY " + Tips_Model.ID;
        Cursor cursor = db.rawQuery(sqlquery, null);
        if (cursor.moveToFirst()) {
            do {
                Tips_Model tip1 = new Tips_Model();
                String image =cursor.getString(cursor.getColumnIndex(Tips_Model.IMAGE));
                tip1.setImage(image);
                String title =cursor.getString(cursor.getColumnIndex(Tips_Model.TITLE));
                tip1.setTitle(title);
                tips.add(tip1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tips;
    }

    public boolean deleteFavorite(String title) {
        int count = db.delete(Tips_Model.TABLE_NAME, Tips_Model.TITLE + "= ?", new
                String[]{title});
        return count > 0;
    }
    public boolean updateFavorite(String title, int image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tips_Model.TITLE, title);
        contentValues.put(Tips_Model.IMAGE, image);

        int count = db.update(Tips_Model.TABLE_NAME, contentValues, Tips_Model.TITLE + " = ?",
                new String[]{title});
        return count > 0;
    }
    // الدوال للاشعارات
    public boolean insertNotification( String title,String description,String time,String data,int image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Notification_Model.TITLE, title);
        contentValues.put(Notification_Model.DESC, description);
        contentValues.put(Notification_Model.TIME, time);
        contentValues.put(Notification_Model.DATE, data);
        contentValues.put(Notification_Model.IMAGE, image);
        long count = db.insert(Notification_Model.TABLE_NAME, null, contentValues);
        return count > 0;
    }
    @SuppressLint("Range")
    public ArrayList<Notification_Model> getAllNotification() {
        ArrayList<Notification_Model> notiefication = new ArrayList<>();
        String sqlquery = "SELECT * FROM " + Notification_Model.TABLE_NAME + " ORDER BY " + Notification_Model.ID + " DESC";
        Cursor cursor = db.rawQuery(sqlquery, null);
        if (cursor.moveToFirst()) {
            do {
                Notification_Model notif1 = new Notification_Model();
                notif1.setId(cursor.getInt(cursor.getColumnIndex(Notification_Model.ID)));
                notif1.setTitle(cursor.getString(cursor.getColumnIndex(Notification_Model.TITLE)));
                notif1.setDescription(cursor.getString(cursor.getColumnIndex(Notification_Model.DESC)));
                notif1.setTime(cursor.getString(cursor.getColumnIndex(Notification_Model.TIME)));
                notif1.setDate(cursor.getString(cursor.getColumnIndex(Notification_Model.DATE)));
                notif1.setImage(cursor.getInt(cursor.getColumnIndex(Notification_Model.IMAGE)));
                notiefication.add(notif1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return notiefication;
    }

    public boolean deleteNotification(int id) {
        int count = db.delete(Notification_Model.TABLE_NAME, Notification_Model.ID + "= ?",
                new String[]{String.valueOf(id)});
        return count > 0;
    }
    public boolean updateNotification(int id, String Title, String Desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Notification_Model.TITLE, Title);
        contentValues.put(Notification_Model.DESC, Desc);
        int count = db.update(Notification_Model.TABLE_NAME, contentValues, Notification_Model.ID + " = ?",
                new String[]{String.valueOf(id)});
        return count > 0;
    }
    public void closeDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    // الدوال الخاصة للمواعيد
    public boolean insertAppointment(String name, String specilize,String date, String time,String state ,int image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Appoint_Model.NAME, name);
        contentValues.put(Appoint_Model.SPEC ,specilize);
        contentValues.put(Appoint_Model.TIME ,time);
        contentValues.put(Appoint_Model.DATE ,date);
        contentValues.put(Appoint_Model.STATE ,state);
        contentValues.put(Appoint_Model.IMAGE ,image);
        long count = db.insert(Appoint_Model.TABLE_NAME, null, contentValues);
        return count > 0;
    }

    @SuppressLint("Range")
    public ArrayList<Appoint_Model> getAppointState(String StateName) {
        ArrayList<Appoint_Model> appoint = new ArrayList<>();
        String sqlquery = "SELECT * FROM appointments_table WHERE state = ?";
        Cursor cursor = db.rawQuery(sqlquery, new String[]{StateName});

        if (cursor.moveToFirst()) {
            do {
                Appoint_Model appoint1 = new Appoint_Model();
                appoint1.setImage(cursor.getInt(cursor.getColumnIndex(Appoint_Model.IMAGE)));
                appoint1.setId(cursor.getInt(cursor.getColumnIndex(Appoint_Model.ID)));
                appoint1.setName(cursor.getString(cursor.getColumnIndex(Appoint_Model.NAME)));
                appoint1.setSpecialize(cursor.getString(cursor.getColumnIndex(Appoint_Model.SPEC)));
                appoint1.setDate(cursor.getString(cursor.getColumnIndex(Appoint_Model.DATE)));
                appoint1.setTime(cursor.getString(cursor.getColumnIndex(Appoint_Model.TIME)));
                appoint1.setState(cursor.getString(cursor.getColumnIndex(Appoint_Model.STATE)));
                appoint.add(appoint1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return appoint;
    }

    public boolean canclAppoint(int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Appoint_Model.STATE, "Cancelled");
        int count = db.update(Appoint_Model.TABLE_NAME, contentValues, Appoint_Model.ID + "= ?",
                new String[]{String.valueOf(id)});
        return count > 0;
    }

    public  boolean checkIfTitle(String title, String image){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Tips_Model.TABLE_NAME + " WHERE " +
                Tips_Model.TITLE + " = ? OR "+ Tips_Model.IMAGE + " = ?", new String[]{title, image});
        boolean isDuplicate = (cursor.getCount() > 0);
        cursor.close();
        return isDuplicate;
    }

}