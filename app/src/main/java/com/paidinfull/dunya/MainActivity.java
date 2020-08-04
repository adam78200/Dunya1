package com.paidinfull.dunya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.paidinfull.dunya.emploi.Emploi;
import com.paidinfull.dunya.entreprise.Entreprendre;
import com.paidinfull.dunya.acte.Acte;

public class MainActivity extends AppCompatActivity {
BottomNavigationView btv;
TextView titi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titi = (TextView)findViewById(R.id.textView);
        titi.setText("notre bien aimé le dernier Messager dit : « Dieu aime que l’un de vous qui fait un travail qu’il le perfectionne. » Rapporté par At-tabarani dans Al-awsat (891). Et il dit: « Dieu a prescrit l’Excellence en toute chose... » rapporté authentiquement par Muslim.");
        btv = (BottomNavigationView)findViewById(R.id.btv);

        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.entreprendre:
                        Intent intent = new Intent(MainActivity.this, Entreprendre.class);
                        startActivity(intent);
                        break;

                    case R.id.emploi:
                        Intent intent1 = new Intent(MainActivity.this, Emploi.class);
                        startActivity(intent1);
                        Toast.makeText(MainActivity.this, "Bientôt disponible akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.Acte:
                        Intent intent4 = new Intent(MainActivity.this, Acte.class);
                        startActivity(intent4);
                        Toast.makeText(MainActivity.this, "Bientôt disponible akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.cours:
                        Toast.makeText(MainActivity.this, "Bientôt dispo akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                }
                return true;
            }
        });
    }

}