package com.paidinfull.dunya.entreprise.Todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.paidinfull.dunya.R;

import java.util.List;

public class Todolist extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
TaskHandler db;
ListView rere;
TextView texjkk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        rere = (ListView) findViewById(R.id.listview);
        rere.setOnItemClickListener(this);
        rere.setOnItemLongClickListener(this);
        texjkk = (TextView) findViewById(R.id.textView6);
        loadListData();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ajout:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("TO DO LIST")
                        .setMessage("inscrit ici la tâche à faire.")
                        .setView(taskEditText)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String label = taskEditText.getText().toString();

                                if (label.trim().length() > 0) {
                                    TaskHandler db = new TaskHandler(getApplicationContext());
                                    db.insertTask(label);
                                    loadListData();
                                    Toast.makeText(Todolist.this, "c'est noté !!!", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(Todolist.this, "écris quelque chose !!!", Toast.LENGTH_SHORT).show();

                                }
                            }
                        })
                        .setNegativeButton("retour", null)
                        //.setNeutralButton("non urgent", null)
                        .create();
                dialog.show();
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadListData() {
        TaskHandler db = new TaskHandler(getApplicationContext());
        List tasks = db.getAllTask();

        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, android.R.id.text1, tasks);

        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        rere.setAdapter(dataAdapter);

    }

    private void loadListView() {
        TaskHandler db = new TaskHandler(getApplicationContext());
        List listv = db.getAllTask();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, final View view, final int position, final long id) {
        final TaskHandler db = new TaskHandler(getApplicationContext());
        List tasks = db.getAllTask();
        final ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, android.R.id.text1, tasks);
        rere.setAdapter(dataAdapter);
        final String val = String.valueOf(dataAdapter.getItem(position));
        Toast.makeText(getApplicationContext(), val, Toast.LENGTH_LONG).show();
        //rere.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        rere.setItemChecked(position, true);


        AlertDialog aD = new AlertDialog.Builder(this)
                .setTitle(val)
                .setIcon(R.drawable.sablier)
                .setMessage("Sélectionnez un temps de concentration pour effectuer cette tâche  ")
                .setNegativeButton("en 25 min", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        new  CountDownTimer(1500000, 1000){

                            public void onTick(long millisUntilFinished) {

                                texjkk.setText(val + "\n" + (millisUntilFinished / 1000)/60 + " min" + " et "+(millisUntilFinished/1000)%60 + " seconde");
                            }

                            public void onFinish() {
                                texjkk.setText(val +" terminé");
                            }
                        }.start();
                    }
                })
                .setPositiveButton("en 10 min", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        new CountDownTimer(600000, 1000) {


                            public void onTick(long millisUntilFinished) {

                                texjkk.setText( val + "\n" + (millisUntilFinished / 1000) / 60 + " min" + " et " + (millisUntilFinished / 1000) % 60 + " seconde");
                            }

                            public void onFinish() {
                                texjkk.setText(val + " terminé");
                            }


                            //  }.cancel();
                        }.start();


                        // Chronomètre en 45min dans l'alerteBox
                    }

                })
                .setNeutralButton("En 45min", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CountDownTimer chrono = new CountDownTimer(2700000, 1000) {


                            public void onTick(long millisUntilFinished) {

                                texjkk.setText(val + "\n" + (millisUntilFinished / 1000) / 60 + " min" + " et " + (millisUntilFinished / 1000) % 60 + " seconde");
                            }

                            public void onFinish() {
                                texjkk.setText(val + " terminé");
                            }


                            //  }.cancel();
                        }.start();
                    }
                })


                .create();
        aD.show();

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final TaskHandler db = new TaskHandler(getApplicationContext());
        List tasks = db.getAllTask();
        final ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, android.R.id.text1, tasks);
        rere.setAdapter(dataAdapter);
        final String val = String.valueOf(dataAdapter.getItem(position));
        Toast.makeText(getApplicationContext(), val, Toast.LENGTH_LONG).show();
        //rere.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        rere.setItemChecked(position, true);

        AlertDialog a = new AlertDialog.Builder(this)
                .setTitle(val)
                .setIcon(R.drawable.todo)
                .setMessage("Cette tâche sera supprimer définitivement")
                .setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaskHandler db = new TaskHandler(getApplicationContext());
                        db.deleteData(val);
                        loadListData();

                    }
                })
                .create();
        a.show();


        return true;

    }
}