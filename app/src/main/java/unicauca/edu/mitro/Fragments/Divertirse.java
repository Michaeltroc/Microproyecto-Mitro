package unicauca.edu.mitro.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import unicauca.edu.mitro.Examen;
import unicauca.edu.mitro.R;


public class Divertirse extends Fragment implements View.OnClickListener {


    private Button btn_jugar;


    public Divertirse() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_divertirse, container, false);
        btn_jugar=(Button) view.findViewById(R.id.btn_empezar);
        btn_jugar.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()== btn_jugar.getId()){
            Intent intent = new Intent(getActivity(), Examen.class);
            startActivity(intent);
        }
    }
}