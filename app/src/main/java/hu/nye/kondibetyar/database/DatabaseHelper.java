package hu.nye.kondibetyar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE="kondibetyar.db";

    //edzes_terv
    public static final String TABLE_EDZES_TERV="edzes_terv";
    public static final String COL_EDZES_TERV_ID="id";
    public static final String COL_EDZES_TERV_NEV="nev";
    public static final String COL_EDZES_TERV_DATUM="datum";

    //edzes_heti
    public static final String TABLE_EDZES_HETI="edzes_heti";
    public static final String COL_EDZES_HETI_ID="id";
    public static final String COL_EDZES_HETI_TERV_ID="terv_id";
    public static final String COL_EDZES_HETI_NEV="nev";
    public static final String COL_EDZES_HETI_DATUM="datum";


    //edzes_nap
    public static final String TABLE_EDZES_NAP="edzes_nap";
    public static final String COL_EDZES_NAP_ID="id";
    public static final String COL_EDZES_NAP_HETI_ID="heti_id";
    public static final String COL_EDZES_NAP_NEV="nev";
    public static final String COL_EDZES_NAP_LEIRAS="leiras";
    public static final String COL_EDZES_NAP_DATUM="datum";

    //gyakorlatok


    //étrend

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_EDZES_TERV +
                " (" + COL_EDZES_TERV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EDZES_TERV_NEV + " TEXT, " +
                COL_EDZES_TERV_DATUM + " DATE);";

        String query2 = "CREATE TABLE " + TABLE_EDZES_HETI +
                " (" + COL_EDZES_HETI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EDZES_HETI_TERV_ID + " INTEGER, "+
                COL_EDZES_HETI_NEV + " TEXT, " +
                COL_EDZES_HETI_DATUM + " DATE,"+
                "FOREIGN KEY ("+COL_EDZES_HETI_TERV_ID+")"+
                "REFERENCES "+TABLE_EDZES_TERV+"("+COL_EDZES_TERV_ID+"));";


        String query3="CREATE TABLE " + TABLE_EDZES_NAP +
                " (" + COL_EDZES_NAP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EDZES_NAP_HETI_ID + " INTEGER, "+
                COL_EDZES_NAP_NEV + " TEXT, " +
                COL_EDZES_NAP_LEIRAS +" TEXT," +
                COL_EDZES_NAP_DATUM + " DATE,"+
                "FOREIGN KEY ("+COL_EDZES_NAP_HETI_ID+")"+
                "REFERENCES "+TABLE_EDZES_HETI+"("+COL_EDZES_HETI_ID+"));";

        //étrend
        //gyakorlatok

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EDZES_TERV);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EDZES_HETI);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EDZES_NAP);
        onCreate(db);
    }

    public boolean deleteData(String table, String id){
        return false;
    }

    public boolean updateData(String TABLE, String id,String leiras) {
        long result = 0;
        SQLiteDatabase db=this.getWritableDatabase();
        if(TABLE=="edzes_nap") {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_EDZES_NAP_LEIRAS, leiras);
            result = db.update(TABLE_EDZES_NAP, contentValues, COL_EDZES_NAP_ID + "=?", new String[]{id});
        }
        if(result==-1) return false;
        else return true;
    }

    public boolean insertData(String TABLE,String terv_nev, String terv_datum,String terv_id, String heti_nev, String heti_datum,String heti_id, String nap_nev, String nap_leiras, String nap_datum) {
        long result = 0;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        if(TABLE=="edzes_terv") {
            contentValues.put(COL_EDZES_TERV_NEV, terv_nev);
            contentValues.put(COL_EDZES_TERV_DATUM, terv_datum);
            result= db.insert(TABLE_EDZES_TERV,null,contentValues);
        }

        if(TABLE=="edzes_heti") {
            contentValues.put(COL_EDZES_HETI_TERV_ID, terv_id);
            contentValues.put(COL_EDZES_HETI_NEV, heti_nev);
            contentValues.put(COL_EDZES_HETI_DATUM, heti_datum);
            result= db.insert(TABLE_EDZES_HETI,null,contentValues);
        }

        if(TABLE=="edzes_nap") {
            contentValues.put(COL_EDZES_NAP_HETI_ID,heti_id);
            contentValues.put(COL_EDZES_NAP_NEV, nap_nev);
            contentValues.put(COL_EDZES_NAP_LEIRAS, nap_leiras);
            contentValues.put(COL_EDZES_NAP_DATUM, nap_datum);
            result= db.insert(TABLE_EDZES_NAP,null,contentValues);
        }
        //étrend
        //gyakorlatok
        if(result==-1) return false;
        else return true;
    }

    public Cursor getMenuData(String Table,String Button_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = null;
        //edzés
        if(Table=="edzes_terv") res = db.rawQuery("SELECT "+COL_EDZES_TERV_ID +", "+ COL_EDZES_TERV_NEV + " FROM " + TABLE_EDZES_TERV , null);
        if(Table=="edzes_heti") res = db.rawQuery("SELECT "+COL_EDZES_HETI_ID +", "+ COL_EDZES_HETI_NEV + " FROM " + TABLE_EDZES_HETI + " WHERE "+COL_EDZES_HETI_TERV_ID+"="+Button_id, null);
        if(Table=="edzes_nap") res = db.rawQuery("SELECT "+COL_EDZES_NAP_ID+", "+ COL_EDZES_NAP_NEV + " FROM " + TABLE_EDZES_NAP +" WHERE "+COL_EDZES_NAP_HETI_ID+"="+Button_id, null);
        //étrend
        //gyakorlatok
        return res;
    }

    public Cursor getTitleId(String Table,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=null;
        if(Table=="edzes_terv") res=db.rawQuery("SELECT "+COL_EDZES_TERV_NEV+" FROM "+TABLE_EDZES_TERV+" WHERE id="+id,null);
        if(Table=="edzes_heti") res=db.rawQuery("SELECT "+COL_EDZES_HETI_NEV+" FROM "+TABLE_EDZES_HETI+" WHERE id="+id,null);
        if(Table=="edzes_nap") res=db.rawQuery("SELECT "+COL_EDZES_NAP_NEV+" FROM "+TABLE_EDZES_NAP+" WHERE id="+id,null);
        return res;
    }

    public Cursor getTextId(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=null;
        res=db.rawQuery("SELECT "+COL_EDZES_NAP_LEIRAS+" FROM "+TABLE_EDZES_NAP+" WHERE id="+id,null);
        return res;
    }


}
