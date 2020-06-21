package unicauca.edu.mitro;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Examen extends AppCompatActivity {

    public static final String EXTRA_SCORE = "Puntaje extra";
    private static final long COUNTDOWN_IN_MILLIS = 20000;
    private TextView textViewPregunta;
    private TextView textViewPuntaje;
    private TextView textViewRecuentodepreguntas;
    private TextView textViewCuentaRegresiva;
    private RadioGroup rbGroup;
    private Button rb1;
    private Button rb2;
    private Button rb3;
    private Button buttonConfirmar;
    private ColorStateList textColorRb;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer Temporizador;
    private long tiemporestantes;
    private List<Pregunta> preguntaList;
    private int contadorPreguntas;
    private int TotalPreguntas;
    private Pregunta preguntaActual;
    private int cpuntaje;
    private boolean respondida;
    private long tiempor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen);
        textViewPregunta = findViewById(R.id.text_view_pregunta);
        textViewPuntaje = findViewById(R.id.text_view_puntuacion);
        textViewRecuentodepreguntas = findViewById(R.id.text_view_contadorpreguntas);
        textViewCuentaRegresiva = findViewById(R.id.text_view_temporizador);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.button);
        rb2 = findViewById(R.id.button2);
        rb3 = findViewById(R.id.button3);
        buttonConfirmar = findViewById(R.id.button_confirmar);
        textColorRb = rb1.getTextColors();
        textColorDefaultCd = textViewCuentaRegresiva.getTextColors();
        PreguntasSQLite dbAyuda = new PreguntasSQLite(this);
        preguntaList = dbAyuda.getAllQuestions();
        TotalPreguntas = preguntaList.size();
        Collections.shuffle(preguntaList);
        mostrarsiguientep();
        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!respondida) {
                    if (rb1.is() || rb2.onCheckIsTextEditor() || rb3.onCheckIsTextEditor()) {
                        comprobarrespuesta();
                    } else {
                        Toast.makeText(Examen.this, "Seleccione una opcion no sea estupido!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mostrarsiguientep();
                }
            }
        });
    }
    private void mostrarsiguientep() {
        rb1.setTextColor(textColorRb);
        rb2.setTextColor(textColorRb);
        rb3.setTextColor(textColorRb);
        rbGroup.clearCheck();
        if (contadorPreguntas < TotalPreguntas) {
            preguntaActual = preguntaList.get(contadorPreguntas);
            textViewPregunta.setText(preguntaActual.getPregunta());
            rb1.setText(preguntaActual.getOpcion1());
            rb2.setText(preguntaActual.getOpcion2());
            rb3.setText(preguntaActual.getOptcion3());
            contadorPreguntas++;
            textViewRecuentodepreguntas.setText("Pregunta: " + contadorPreguntas + "/" + TotalPreguntas);
            respondida = false;
            buttonConfirmar.setText("Confirmar");
            tiemporestantes = COUNTDOWN_IN_MILLIS;
            ecuentaatras();
        } else {
            terminarexamen();
        }
    }
    private void ecuentaatras() {
        Temporizador = new CountDownTimer(tiemporestantes, 1000) {
            @Override
            public void onTick(long trestante) {
                tiemporestantes = trestante;
                actualizarcuenta();
            }
            @Override
            public void onFinish() {
                tiemporestantes = 0;
                actualizarcuenta();
                comprobarrespuesta();
            }
        }.start();
    }
    private void actualizarcuenta() {
        int minutes = (int) (tiemporestantes / 1000) / 60;
        int seconds = (int) (tiemporestantes / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCuentaRegresiva.setText(timeFormatted);
        if (tiemporestantes < 10000) {
            textViewCuentaRegresiva.setTextColor(Color.RED);
        } else {
            textViewCuentaRegresiva.setTextColor(textColorDefaultCd);
        }
    }
    private void comprobarrespuesta () {
        respondida = true;
        Temporizador.cancel();
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        if (answerNr == preguntaActual.getNrespuesta()) {
            cpuntaje++;
            textViewPuntaje.setText("Puntaje: " + cpuntaje);
        }
        mostrarsolucion();
    }
    private void mostrarsolucion() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        switch (preguntaActual.getNrespuesta()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewPregunta.setText("Es correcto");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewPregunta.setText("Es correcto");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewPregunta.setText("Es correcto");
                break;
        }
        if (contadorPreguntas < TotalPreguntas) {
            buttonConfirmar.setText("Siguiente");
        } else {
            buttonConfirmar.setText("Terminar");
        }
    }
    private void terminarexamen() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, cpuntaje);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    @Override
    public void onBackPressed() {
        if (tiempor + 2000 > System.currentTimeMillis()) {
            terminarexamen();
        } else {
            Toast.makeText(this, "Presione nuevamente para finalizar", Toast.LENGTH_SHORT).show();
        }
        tiempor = System.currentTimeMillis();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Temporizador != null) {
            Temporizador.cancel();
        }
    }
}