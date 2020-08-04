package com.paidinfull.dunya.emploi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.paidinfull.dunya.R;
import com.paidinfull.dunya.acte.Acte;
import com.paidinfull.dunya.entreprise.Entreprendre;

public class Emploi extends AppCompatActivity {
    BottomNavigationView btv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploi);

        btv = (BottomNavigationView)findViewById(R.id.btv);

        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.entreprendre:
                        Intent intent = new Intent(Emploi.this, Entreprendre.class);
                        startActivity(intent);
                        break;

                    case R.id.emploi:
                        Intent intent1 = new Intent(Emploi.this, Emploi.class);
                        startActivity(intent1);
                        Toast.makeText(Emploi.this, "Bientôt disponible akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.Acte:
                        Intent intent3 = new Intent(Emploi.this, Acte.class);
                        startActivity(intent3);
                        Toast.makeText(Emploi.this, "Bientôt disponible akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.cours:
                        Toast.makeText(Emploi.this, "Bientôt dispo akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                }
                return true;
            }
        });
    }

}
