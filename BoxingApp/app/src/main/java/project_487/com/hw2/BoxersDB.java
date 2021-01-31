package project_487.com.hw2;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class BoxersDB { public static final String TABLE_NAME="Boxers";
    public static final String FIELD_DIVISION = "division";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_WINS = "wins";
    public static final String FIELD_TITLES = "titles";
    public static final String FIELD_LOSSES = "losses";
    public static final String FIELD_DIVISIONS = "divisions";
    public static final String FIELD_IMG = "imgId";
    public static final String FIELD_BOXERS = "boxers";


    public static final String CREATE_TABLE_SQL = "CREATE TABLE "+
            TABLE_NAME+" ("+FIELD_NAME+" text, "+FIELD_WINS+" number, "+
            FIELD_TITLES+" text, "+FIELD_LOSSES+" number , "+FIELD_DIVISION+" number,"+FIELD_DIVISIONS+" text,"+FIELD_IMG+" text);";
    public static final String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;


    public static List<Boxer> getAllboxers(Dbhelper db){

        Cursor cursor = db.getAllRecords(TABLE_NAME, null);
        //Cursor cursor  db.getAllRecordsMethod2("SELECT * FROM "+TABLE_NAME, null)
        List<Boxer> data=new ArrayList<>();
        Boxer anItem = null;
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            int wins = cursor.getInt(1);
            String titles = cursor.getString(2);
            int losses = cursor.getInt(3);
            String division=cursor.getString(4);
            int divisions = cursor.getInt(5);
            String imgId=cursor.getString(6);
            anItem= new Boxer(name,wins,titles,losses,division,divisions,imgId );
            data.add(anItem);
        }

        return data;
    }
    public static List<Boxer> findboxer(Dbhelper db, String key) {
        String where = FIELD_DIVISION + " like '%" + key + "%'";
        Cursor cursor = db.getSomeRecords(TABLE_NAME, null, where);
        List<Boxer> data = new ArrayList<>();
        Boxer anItem = null;
        Log.d("TAA","Before Find");
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            int wins = cursor.getInt(1);
            String titles = cursor.getString(2);
            int losses = cursor.getInt(3);
            String division=cursor.getString(4);
            int divisions = cursor.getInt(5);
            String imgId=cursor.getString(6);
            anItem= new Boxer(name,wins,titles,losses,division,divisions,imgId );
            data.add(anItem);
            Log.d("TAG",name+" Find");
        }

        return data;
    }
    public static long insertBoxer(Dbhelper db, String division,String name,int wins,String titles,int losses,int divisions,String imgId ){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_DIVISION, division);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_WINS, wins);
        contentValues.put(FIELD_TITLES, titles);
        contentValues.put(FIELD_LOSSES, losses);
        contentValues.put(FIELD_DIVISIONS, divisions);
        contentValues.put(FIELD_IMG, imgId);

        long res = db.insert(TABLE_NAME,contentValues);

        return res;
    }
    public static boolean updateBoxer(Dbhelper db,String name,int wins,String titles,int losses,String division,int divisions,String imgId){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_DIVISION, division);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_WINS, wins);
        contentValues.put(FIELD_TITLES, titles);
        contentValues.put(FIELD_LOSSES, losses);
        contentValues.put(FIELD_DIVISIONS, divisions);
        contentValues.put(FIELD_IMG, imgId);

        String where = FIELD_NAME +" = "+ name;

        boolean res = db.update(TABLE_NAME,contentValues,where);

        return res;
    }
    public static boolean deleteShoppingItem(Dbhelper db, String name){
        String where = FIELD_NAME+" = "+  name;

        boolean res = db.delete(TABLE_NAME,where);

        return res;
    }
    public static void getBoxers(Dbhelper db)  {

        JSONArray boxers;
        JSONObject bookJSONObject;
        Log.d("TAGPARSE", "\n" + Common.jsonStr);
            if (Common.jsonStr!= null) {
                try {
                    bookJSONObject = new JSONObject(Common.jsonStr);
                    // Getting JSON Array
                    boxers = bookJSONObject.getJSONArray(BoxersDB.FIELD_BOXERS);
                    Log.d("TAGPARSE", "json object taken");


                    // looping through all books
                    for (int i = 0; i < boxers.length(); i++) {
                        JSONObject c = boxers.getJSONObject(i);

                        Log.d("TAGPARSE", "for loop");


                        String name = c.getString(BoxersDB.FIELD_NAME);
                        String wins = c.getString(BoxersDB.FIELD_WINS);
                        String titles = c.getString(BoxersDB.FIELD_TITLES);
                        String losses = c.getString(BoxersDB.FIELD_LOSSES);
                        String division = c.getString(BoxersDB.FIELD_DIVISION);
                        String divisions = c.getString(BoxersDB.FIELD_DIVISIONS);
                        String img = c.getString("img");

                        Log.d("TAGPARSE", "after");


                        int winst= Integer.parseInt(wins);
                        int lossest= Integer.parseInt(losses);
                        int divisionst= Integer.parseInt(divisions);


                        ContentValues v = new ContentValues();

                        v.put("name",name);
                        v.put("wins",winst);
                        v.put("titles",titles);
                        v.put("losses",lossest);
                        v.put("division",division);
                        v.put("divisions",divisionst);
                        v.put("imageId",img);

                        Log.d("DJSONPARSE", name+" "+titles);
                        insertBoxer(db,division,name,winst,titles,lossest,divisionst,img);

                    }
                } catch (JSONException ee) {
                    ee.printStackTrace();
                }
            }


        }




}
