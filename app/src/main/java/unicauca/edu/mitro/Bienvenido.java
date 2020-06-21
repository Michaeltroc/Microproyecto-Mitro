package unicauca.edu.mitro;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import unicauca.edu.mitro.Fragments.Aprender;
import unicauca.edu.mitro.Fragments.Divertirse;


public class Bienvenido extends AppCompatActivity {

    BottomNavigationView btn_navegacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);



        btn_navegacion = (BottomNavigationView) findViewById(R.id.bottomNavegacion);
        mostrarfragment(new Aprender());
        btn_navegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                if (menuItem.getItemId() == R.id.nav_jugar) {
                mostrarfragment(new Divertirse());
                }
                if (menuItem.getItemId() == R.id.nav_aprender) {
                mostrarfragment(new Aprender());
                }

                return true;
            }
        });
    }

        private void mostrarfragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
        }

}



