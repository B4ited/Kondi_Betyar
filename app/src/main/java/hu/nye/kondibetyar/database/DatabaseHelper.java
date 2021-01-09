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


    //edzes_nap
    public static final String TABLE_EDZES_NAP="edzes_nap";
    public static final String COL_EDZES_NAP_ID="id";
    public static final String COL_EDZES_NAP_HET_ID="het_id";
    public static final String COL_EDZES_NAP_TERV_NEV="terv_nev";
    public static final String COL_EDZES_NAP_LEIRAS="leiras";

    //gyakorlatok


    //étrend

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_EDZES_TERV +
                " (" + COL_EDZES_TERV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EDZES_TERV_NEV + " TEXT);";


        String query2="CREATE TABLE " + TABLE_EDZES_NAP +
                " (" + COL_EDZES_NAP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EDZES_NAP_HET_ID + " INTEGER, "+
                COL_EDZES_NAP_TERV_NEV + " TEXT,"+
                COL_EDZES_NAP_LEIRAS+" TEXT);";

        //étrend
        //gyakorlatok

        db.execSQL(query);
        db.execSQL(query2);

       insertBasicData(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EDZES_TERV);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EDZES_NAP);
        onCreate(db);
    }

    public boolean deleteData(String TABLE, String id){
        long result = 0;
        SQLiteDatabase db=this.getWritableDatabase();
        if(TABLE=="edzes_terv") {
            result = db.delete(TABLE_EDZES_TERV, COL_EDZES_TERV_ID+"=?", new String[]{id});
        }
        if(result==-1) return false;
        else return true;
    }

    public boolean updateData(String TABLE, String nap_id, String terv_nev,String leiras) {
        long result = 0;
        SQLiteDatabase db=this.getWritableDatabase();
        if(TABLE=="edzes_nap") {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_EDZES_NAP_LEIRAS, leiras);
           result = db.update(TABLE_EDZES_NAP, contentValues, COL_EDZES_NAP_HET_ID + "=? AND "+COL_EDZES_NAP_TERV_NEV+"=?", new String[]{nap_id,terv_nev});
        }
        if(result==-1) return false;
        else return true;
    }

    public boolean insertData(String TABLE,String terv_nev, String nap_het_id,String nap_terv_nev, String nap_leiras) {
        long result = 0;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        if(TABLE=="edzes_terv") {
            contentValues.put(COL_EDZES_TERV_NEV, terv_nev);
            result= db.insert(TABLE_EDZES_TERV,null,contentValues);
        }

        if(TABLE=="edzes_nap") {
            contentValues.put( COL_EDZES_NAP_HET_ID,nap_het_id);
            contentValues.put(COL_EDZES_NAP_TERV_NEV, nap_terv_nev);
            contentValues.put(COL_EDZES_NAP_LEIRAS, nap_leiras);
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
        if(Table=="edzes_nap") res = db.rawQuery("SELECT "+COL_EDZES_NAP_ID+", "+ COL_EDZES_NAP_TERV_NEV + " FROM " + TABLE_EDZES_NAP +" WHERE "+COL_EDZES_NAP_HET_ID+"="+Button_id, null);
        //étrend
        //gyakorlatok
        return res;
    }

    public Cursor getTitleId(String Table,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=null;
        if(Table=="edzes_terv") res=db.rawQuery("SELECT "+COL_EDZES_TERV_NEV+" FROM "+TABLE_EDZES_TERV+" WHERE id="+id,null);
        if(Table=="edzes_nap") res=db.rawQuery("SELECT "+COL_EDZES_NAP_TERV_NEV+" FROM "+TABLE_EDZES_NAP+" WHERE id="+id,null);
        return res;
    }

    public Cursor getTextId(String button_id,String terv_nev){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=null;
        res=db.rawQuery("SELECT "+COL_EDZES_NAP_LEIRAS+" FROM "+TABLE_EDZES_NAP+" WHERE "+COL_EDZES_NAP_HET_ID+"="+button_id+ " AND "+ COL_EDZES_NAP_TERV_NEV+"="+"'"+terv_nev+"'",null);
        return res;
    }


    public void insertBasicData(SQLiteDatabase db){
        db.execSQL(" INSERT INTO "+TABLE_EDZES_TERV+"("+COL_EDZES_TERV_NEV+") VALUES ('Haladó')");
       //Haladó
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('1','Haladó','Mell')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('2','Haladó','Hát')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('3','Haladó','Váll')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('4','Haladó','Pihenőnap')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('5','Haladó','Kar')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('6','Haladó','Pihenőnap')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('7','Haladó','Pihenőnap')");

        //Kezdő
        db.execSQL(" INSERT INTO "+TABLE_EDZES_TERV+"("+COL_EDZES_TERV_NEV+") VALUES ('Kezdő')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('1','Kezdő','Mell')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('2','Kezdő','Hát')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('3','Kezdő','Váll')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('4','Kezdő','Pihenőnap')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('5','Kezdő','Kar')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('6','Kezdő','Pihenőnap')");
        db.execSQL(" INSERT INTO "+TABLE_EDZES_NAP+"("+COL_EDZES_NAP_HET_ID+","+COL_EDZES_NAP_TERV_NEV+","+COL_EDZES_NAP_LEIRAS+") VALUES ('7','Kezdő','Pihenőnap')");

    }



}
