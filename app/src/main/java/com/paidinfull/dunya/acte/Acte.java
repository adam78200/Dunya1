package com.paidinfull.dunya.acte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.paidinfull.dunya.R;
import com.paidinfull.dunya.emploi.Emploi;
import com.paidinfull.dunya.entreprise.Entreprendre;

public class Acte extends AppCompatActivity {

    BottomNavigationView btv;
    GridView grid;
    Custom_GridAdapter adapter1;

    String[]catAct= {"Visites", "Janaza", "Maraude", "Calculette zakat", "Dettes"};
    Integer[]logo1= {R.drawable.visites, R.drawable.janaza, R.drawable.maraudes, R.drawable.calculette, R.drawable.dettes};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acte);
        grid = (GridView)findViewById(R.id.gridview);

        adapter1 = new Custom_GridAdapter(Acte.this, catAct, logo1);
       grid.setAdapter(new Custom_GridAdapter(Acte.this, catAct, logo1));
        btv = (BottomNavigationView)findViewById(R.id.btv);


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items = (String)grid.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), items, Toast.LENGTH_LONG).show();
            }
        });

        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.entreprendre:
                        Intent intent = new Intent(Acte.this, Entreprendre.class);
                        startActivity(intent);
                        break;

                    case R.id.emploi:
                        Intent intent1 = new Intent(Acte.this, Emploi.class);
                        startActivity(intent1);
                        Toast.makeText(Acte.this, "Bientôt disponible akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.Acte:
                        Intent intent3 = new Intent(Acte.this, Acte.class);
                        startActivity(intent3);
                        Toast.makeText(Acte.this, "Bientôt disponible akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.cours:
                        Toast.makeText(Acte.this, "Bientôt dispo akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                }
                return true;
            }
        });

    }
}