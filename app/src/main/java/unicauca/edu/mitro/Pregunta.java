package unicauca.edu.mitro;

public class Pregunta {
    private String Pregunta;
    private String opcion1;
    private String opcion2;
    private String optcion3;
    private int nrespuesta;

    public Pregunta () {
    }

    public Pregunta(String pregunta, String opcion1, String opcion2, String optcion3, int nrespuesta) {
        Pregunta = pregunta;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.optcion3 = optcion3;
        this.nrespuesta = nrespuesta;
    }

    public String getPregunta() {
        return Pregunta;
    }

    public void setPregunta(String pregunta) {
        Pregunta = pregunta;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOptcion3() {
        return optcion3;
    }

    public void setOptcion3(String optcion3) {
        this.optcion3 = optcion3;
    }

    public int getNrespuesta() {
        return nrespuesta;
    }

    public void setNrespuesta(int nrespuesta) {
        this.nrespuesta = nrespuesta;
    }
}