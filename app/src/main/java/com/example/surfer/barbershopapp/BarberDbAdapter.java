package com.example.surfer.barbershopapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.SQLException;

public class BarberDbAdapter {
    public static final String KEY_TITLE = "title";
    public static final String KEY_BODY = "body";
    public static final String KEY_ROWID = "_id";

    private static final String TAG = "NotesDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
            "create table notes (_id integer primary key autoincrement, "
                    + "title text not null, body text not null);";

    private static final String DATABASE_CREATE_TABLE_LOCALITATION =
            "create table localizaciones (_id integer primary key, "
                    + "latitud double not null, longitud double not null, "
                    + "idBarbero integer not null);";

    private static final String DATABASE_CREATE_TABLE_BARBERS =
            "create table barberos (_id integer primary key, "
                    + "nombres text not null);";

    private static final String DATABASE_CREATE_TABLE_AGENDA =
            "create table agendas (_id integer primary key autoincrement, "
                    + "fecha long not null, idUsuario integer, "
                    + "idLocalizacion integer not null, precio integer);";

    private static final String DATABASE_CREATE_TABLE_USERS =
            "create table usuarios (_id integer primary key, "
                    + "nombres text not null);";

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "notes";
    private static final String DATABASE_TABLE_LOCALITATION = "localizaciones";
    private static final String DATABASE_TABLE_BARBERS = "barberos";
    private static final String DATABASE_TABLE_USERS = "usuarios";
    private static final String DATABASE_TABLE_AGENDA = "agendas";


    private static final int DATABASE_VERSION = 3;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);

            db.execSQL(DATABASE_CREATE_TABLE_LOCALITATION);

            db.execSQL(DATABASE_CREATE_TABLE_BARBERS);

            db.execSQL(DATABASE_CREATE_TABLE_AGENDA);

            db.execSQL(DATABASE_CREATE_TABLE_USERS);

            crearUsuarios(db);
            crearBarberos(db);
            crearLocalizaciones(db);
            crearAgendasInicial(db);

            Log.w(TAG, "INICIANDO BD");
        }

        private void crearUsuarios(SQLiteDatabase db)
        {
            ContentValues registro = new ContentValues();

            registro.put("_id",1);
            registro.put("nombres","Didier");

            db.insert(DATABASE_TABLE_USERS, null,registro);

            registro = new ContentValues();
            registro.put("_id",2);
            registro.put("nombres","Juan");

            db.insert(DATABASE_TABLE_USERS,null,registro);
        }

        private void crearBarberos(SQLiteDatabase db)
        {
            ContentValues registro = new ContentValues();

            registro.put("_id",1);
            registro.put("nombres","Brayan");

            db.insert(DATABASE_TABLE_BARBERS, null,registro);

            registro = new ContentValues();
            registro.put("_id",2);
            registro.put("nombres","Sebastian");
            db.insert(DATABASE_TABLE_BARBERS,null,registro);

            registro = new ContentValues();
            registro.put("_id",3);
            registro.put("nombres","Taty");
            db.insert(DATABASE_TABLE_BARBERS,null,registro);

            registro = new ContentValues();
            registro.put("_id",4);
            registro.put("nombres","Yeny");
            db.insert(DATABASE_TABLE_BARBERS,null,registro);

            registro = new ContentValues();
            registro.put("_id",5);
            registro.put("nombres","Juan David");
            db.insert(DATABASE_TABLE_BARBERS,null,registro);
        }

        private void crearLocalizaciones(SQLiteDatabase db)
        {
            ContentValues registro = new ContentValues();

            registro.put("_id",1);
            registro.put("latitud",6.262542D);
            registro.put("longitud",-75.594802D);
            registro.put("idBarbero",1);
            db.insert(DATABASE_TABLE_LOCALITATION, null,registro);

            registro = new ContentValues();
            registro.put("_id",2);
            registro.put("latitud",6.26229D);
            registro.put("longitud",-75.59428D);
            registro.put("idBarbero",2);
            db.insert(DATABASE_TABLE_LOCALITATION,null,registro);

            registro = new ContentValues();
            registro.put("_id",3);
            registro.put("latitud",6.262013D);
            registro.put("longitud",-75.594038D);
            registro.put("idBarbero",3);
            db.insert(DATABASE_TABLE_LOCALITATION,null,registro);

            registro = new ContentValues();
            registro.put("_id",4);
            registro.put("latitud",6.261918D);
            registro.put("longitud",-75.593785D);
            registro.put("idBarbero",4);
            db.insert(DATABASE_TABLE_LOCALITATION,null,registro);

            registro = new ContentValues();
            registro.put("_id",5);
            registro.put("latitud",6.261568D);
            registro.put("longitud",-75.593044D);
            registro.put("idBarbero",5);
            db.insert(DATABASE_TABLE_LOCALITATION,null,registro);
        }

        private void crearAgendasInicial(SQLiteDatabase db)
        {
            ContentValues registro = new ContentValues();

            long fechaInicial = 1533779575576L;

            registro.put("fecha",fechaInicial);
            registro.put("idUsuario",1);
            registro.put("idLocalizacion",1);
            registro.put("precio",10000);
            db.insert(DATABASE_TABLE_AGENDA, null,registro);

            registro.put("fecha",fechaInicial);
            registro.put("idUsuario",1);
            registro.put("idLocalizacion",3);
            registro.put("precio",15000);
            db.insert(DATABASE_TABLE_AGENDA, null,registro);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            db.execSQL("DROP TABLE IF EXISTS usuarios");
            db.execSQL("DROP TABLE IF EXISTS barberos");
            db.execSQL("DROP TABLE IF EXISTS localizaciones");
            db.execSQL("DROP TABLE IF EXISTS agendas");


            onCreate(db);
        }
    }

    /**Localizaciones*/
    public Cursor fetchAllLocalizations() {

        return mDb.query(DATABASE_TABLE_LOCALITATION, new String[] {"_id", "latitud",
                "longitud","idBarbero"}, null, null, null, null, null);
    }


    /*Barberos*/
    public Cursor fetchBarber(long rowId) throws SQLException {
        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE_BARBERS, new String[] {"_id",
                                "nombres"}, "_id" + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }


    /*Citas*/
    public Cursor fetchCitas(long rowId) throws SQLException {
        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE_AGENDA, new String[] {"_id",
                                "fecha","idUsuario","idLocalizacion","precio"}, "idUsuario" + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }


    /*Agenda*/
    public Cursor fetchAllAppoitment(long f) {

        return mDb.query(DATABASE_TABLE_AGENDA, new String[] {"fecha"},
                "fecha" + "=" + f, null, null, null, null);
    }

    public long createAppointment(Long fecha, Integer idUsuario, Integer idLocalizacion, Integer precio) {
        ContentValues registro = new ContentValues();

        registro.put("fecha", fecha);
        registro.put("idUsuario", idUsuario);
        registro.put("idLocalizacion", idLocalizacion);
        registro.put("precio", precio);


        return mDb.insert(DATABASE_TABLE_AGENDA, null, registro);
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */
    public BarberDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public BarberDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    /**
     * Create a new note using the title and body provided. If the note is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     *
     * @param title the title of the note
     * @param body the body of the note
     * @return rowId or -1 if failed
     */
    public long createNote(String title, String body) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_BODY, body);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the note with the given rowId
     *
     * @param rowId id of note to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteNote(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all notes in the database
     *
     * @return Cursor over all notes
     */
    public Cursor fetchAllNotes() {

        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_TITLE,
                KEY_BODY}, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     *
     * @param rowId id of note to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetchNote(long rowId) throws SQLException {

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_TITLE, KEY_BODY}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    /**
     * Update the note using the details provided. The note to be updated is
     * specified using the rowId, and it is altered to use the title and body
     * values passed in
     *
     * @param rowId id of note to update
     * @param title value to set note title to
     * @param body value to set note body to
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateNote(long rowId, String title, String body) {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, title);
        args.put(KEY_BODY, body);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
