package unicauca.edu.mitro;

import android.provider.BaseColumns;
public final class EstructuraPreguntas {

    private EstructuraPreguntas() {
    }
    public static class TabladePreguntas implements BaseColumns {
        public static final String NOMBRE_TABLA = "Examen_preguntas";
        public static final String COLUMNA_PREGUNTA = "pregunta";
        public static final String COLUMNA_OPCION1 = "opcion1";
        public static final String COLUMNA_OPCION2 = "opcion2";
        public static final String COLUMNA_OPCION3 = "opcion3";
        public static final String COLUMNA_RESPUESTA = "respuesta";
    }
}