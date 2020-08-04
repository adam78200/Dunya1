package com.paidinfull.dunya.entreprise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.paidinfull.dunya.R;
import com.paidinfull.dunya.acte.Acte;
import com.paidinfull.dunya.emploi.Emploi;
import com.paidinfull.dunya.entreprise.Todolist.Todolist;
import com.paidinfull.dunya.entreprise.facturation.Facturier;

public class Entreprendre extends AppCompatActivity {
ListView listView;
BottomNavigationView btv;
String[] cat= {"n'oublie pas tes tâches importantes", "factures facile",
        "calcule la rentabilité de ton affaire \n \n'Pense à la prière de consutation'",
        "Pense à regler les problèmes avec \n les clients ici-bas"};
Integer[] logo={R.drawable.todolist, R.drawable.facture, R.drawable.rentabilite, R.drawable.litige};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreprendre);

        btv = findViewById(R.id.btv);

        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.entreprendre:
                        Intent intent = new Intent(Entreprendre.this, Entreprendre.class);
                        startActivity(intent);
                        break;

                    case R.id.emploi:
                        Intent intent1 = new Intent(Entreprendre.this, Emploi.class);
                        startActivity(intent1);
                        Toast.makeText(Entreprendre.this, "Bientôt disponible akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.Acte:
                        Intent intent2 = new Intent(Entreprendre.this, Acte.class);
                        startActivity(intent2);
                        Toast.makeText(Entreprendre.this, "Bientôt disponible akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.cours:
                        Toast.makeText(Entreprendre.this, "Bientôt dispo akhy el karim", Toast.LENGTH_LONG).show();
                        break;

                }
                return true;
            }
        });


        CustomAdapter adapter = new CustomAdapter(Entreprendre.this, cat, logo);
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String val = (String) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), val, Toast.LENGTH_LONG).show();
                switch (position){
                    case 0:
                        Intent re = new Intent(getApplicationContext(), Todolist.class);
                        startActivity(re);
                        break;

                    case 1:
                        Intent i = new Intent(getApplicationContext(), Facturier.class);
                        startActivity(i);
                        break;
                }


            }
        });

    }
}