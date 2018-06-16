package pjatk.pl.bicycles.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pjatk.pl.bicycles.model.Bicycle;

/**
 * Created by mateuszsielawa on 14.06.2018.
 */

public class BicycleDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Bicycles.db";
    private static BicycleDbHelper INSTANCE;

    public static final String BICYCLE_TABLE_NAME = "BICYCLE";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String PRICE = "PRICE";
    public static final String PICTURE = "PICTURE";

    private static final String SQL_CREATE_BICYCLE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BICYCLE_TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NAME + " TEXT," +
                    DESCRIPTION + " TEXT," +
                    PRICE + " NUMBER(6,2)," +
                    PICTURE + " TEXT)";


    private static final String SQL_DELETE_BICYCLE_TABLE =
            "DROP TABLE IF EXISTS " + BICYCLE_TABLE_NAME;

    public static BicycleDbHelper getInstance(Context ctx) {
        if (INSTANCE == null) {
            INSTANCE = new BicycleDbHelper(ctx.getApplicationContext());
        }
        return INSTANCE;
    }

    private BicycleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_BICYCLE_TABLE);
        db.execSQL(SQL_CREATE_BICYCLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_BICYCLE_TABLE);
        onCreate(db);
    }


    public boolean create(Bicycle bicycle) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, bicycle.getName());
        contentValues.put(DESCRIPTION, bicycle.getDescription());
        contentValues.put(PRICE, bicycle.getPrice().doubleValue());
        contentValues.put(PICTURE, bicycle.getPicture());

        long result = db.insert(BICYCLE_TABLE_NAME, null, contentValues);
        db.close();

        return result != -1;
    }

    public List<Bicycle> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sortOrder =
                ID + " DESC";

        Cursor cursor = db.query(
                BICYCLE_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List<Bicycle> bicycles = new ArrayList<>();

        while(cursor.moveToNext()) {
            Integer id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(ID));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(NAME));
            String description = cursor.getString(
                    cursor.getColumnIndexOrThrow(DESCRIPTION));
            Double price = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(PRICE));
            String picture = cursor.getString(
                    cursor.getColumnIndexOrThrow(PICTURE));

            bicycles.add(new Bicycle(id, name, description, BigDecimal.valueOf(price),picture));
        }
        cursor.close();
        db.close();

        return bicycles;
    }

    public Optional<Bicycle> get(Integer bicycleId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                BICYCLE_TABLE_NAME,
                null,
                ID + "=" + bicycleId,
                null,
                null,
                null,
                null
        );

        Optional<Bicycle> bicycle = Optional.empty();

        if(cursor.getCount() == 1) {
            cursor.moveToNext();
            Integer id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(ID));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(NAME));
            String description = cursor.getString(
                    cursor.getColumnIndexOrThrow(DESCRIPTION));
            Double price = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(PRICE));
            String picture = cursor.getString(
                    cursor.getColumnIndexOrThrow(PICTURE));

            bicycle = Optional.of(new Bicycle(id, name, description, BigDecimal.valueOf(price), picture));
        }
        cursor.close();
        db.close();

        return bicycle;
    }
}
