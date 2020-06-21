package unicauca.edu.mitro;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import unicauca.edu.mitro.EstructuraPreguntas.TabladePreguntas;

public class PreguntasSQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    public PreguntasSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                TabladePreguntas.NOMBRE_TABLA + " ( " +
                TabladePreguntas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TabladePreguntas.COLUMNA_PREGUNTA + " TEXT, " +
                TabladePreguntas.COLUMNA_OPCION1 + " TEXT, " +
                TabladePreguntas.COLUMNA_OPCION2 + " TEXT, " +
                TabladePreguntas.COLUMNA_OPCION3 + " TEXT, " +
                TabladePreguntas.COLUMNA_RESPUESTA + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TabladePreguntas.NOMBRE_TABLA);
        onCreate(db);
    }
    private void fillQuestionsTable() {
        Pregunta q1 = new Pregunta("2+6=", "9", "8", "7", 2);
        addQuestion(q1);
        Pregunta q2 = new Pregunta("5+10", "7", "8", "15", 3);
        addQuestion(q2);
        Pregunta q3 = new Pregunta("DOS CERO DOS CUATRO", "2024", "0044", "0024", 1);
        addQuestion(q3);
        Pregunta q4 = new Pregunta("15+10", "25", "23", "26", 1);
        addQuestion(q4);
        Pregunta q5 = new Pregunta("4+6", "9", "10", "11", 2);
        addQuestion(q5);
    }
    private void addQuestion(Pregunta pregunta) {
        ContentValues cv = new ContentValues();
        cv.put(TabladePreguntas.COLUMNA_PREGUNTA, pregunta.getPregunta());
        cv.put(TabladePreguntas.COLUMNA_OPCION1, pregunta.getOpcion1());
        cv.put(TabladePreguntas.COLUMNA_OPCION2, pregunta.getOpcion2());
        cv.put(TabladePreguntas.COLUMNA_OPCION3, pregunta.getOptcion3());
        cv.put(TabladePreguntas.COLUMNA_RESPUESTA, pregunta.getNrespuesta());
        db.insert(TabladePreguntas.NOMBRE_TABLA, null, cv);
    }
    public List<Pregunta> getAllQuestions() {
        List<Pregunta> preguntaList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TabladePreguntas.NOMBRE_TABLA, null);
        if (c.moveToFirst()) {
            do {
                Pregunta pregunta = new Pregunta();
                pregunta.setPregunta(c.getString(c.getColumnIndex(TabladePreguntas.COLUMNA_PREGUNTA)));
                pregunta.setOpcion1(c.getString(c.getColumnIndex(TabladePreguntas.COLUMNA_OPCION1)));
                pregunta.setOpcion2(c.getString(c.getColumnIndex(TabladePreguntas.COLUMNA_OPCION2)));
                pregunta.setOptcion3(c.getString(c.getColumnIndex(TabladePreguntas.COLUMNA_OPCION3)));
                pregunta.setNrespuesta(c.getInt(c.getColumnIndex(TabladePreguntas.COLUMNA_RESPUESTA)));
                preguntaList.add(pregunta);
            } while (c.moveToNext());
        }
        c.close();
        return preguntaList;
    }
}