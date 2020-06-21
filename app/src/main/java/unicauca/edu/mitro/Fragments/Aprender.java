package unicauca.edu.mitro.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import unicauca.edu.mitro.Dividir;
import unicauca.edu.mitro.Multiplicar;
import unicauca.edu.mitro.R;
import unicauca.edu.mitro.Resta;
import unicauca.edu.mitro.Suma;


public class Aprender extends Fragment implements View.OnClickListener {

    Button btn_asumar, btn_arestar, btn_amultiplicar, btn_adividir;
    public Aprender() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view =inflater.inflate(R.layout.fragment_aprender, container, false);

        btn_asumar = (Button)view.findViewById(R.id.btn_ApreSumar);
        btn_arestar = (Button)view.findViewById(R.id.btn_ApreRestar);
        btn_adividir = (Button)view.findViewById(R.id.btn_ApreDividir);
        btn_amultiplicar = (Button)view.findViewById(R.id.btn_ApreMultiplicar);

        btn_adividir.setOnClickListener(this);
        btn_amultiplicar.setOnClickListener(this);
        btn_arestar.setOnClickListener(this);
        btn_asumar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Suma.class);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == btn_arestar.getId()) {
            Intent intent = new Intent(getActivity(), Resta.class);
            startActivity(intent);
        }
        if (v.getId() == btn_adividir.getId()) {
            Intent intent = new Intent(getActivity(), Dividir.class);
            startActivity(intent);
        }   if (v.getId() == btn_amultiplicar.getId()) {
            Intent intent = new Intent(getActivity(), Multiplicar.class);
            startActivity(intent);
        }

    }

}
