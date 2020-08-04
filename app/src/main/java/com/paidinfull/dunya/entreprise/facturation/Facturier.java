package com.paidinfull.dunya.entreprise.facturation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.paidinfull.dunya.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Facturier extends AppCompatActivity {
    EditText soc, siret, tel, clicli, adres, date, tva, desig, qté, prix;
    Bitmap btm, scalebtm;
    int pagewidth = 1200;
    Date dateObject;
    DateFormat dateFormat;
    Spinner spinner;
    /*Switch aSwitch;*/

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturier);

        ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
        PackageManager.PERMISSION_GRANTED);



        soc = findViewById(R.id.editTextTextPersonName);
        siret = findViewById(R.id.editTextTextPersonName2);
        tel = findViewById(R.id.editTextTextPersonName3);
        clicli = findViewById(R.id.editTextTextPersonName4);
        adres = findViewById(R.id.editTextTextPersonName5);
        tva = findViewById(R.id.editTextNumber);
        desig = findViewById(R.id.editTextTextPersonName6);
        qté = findViewById(R.id.editTextTextPersonName7);
        prix = findViewById(R.id.editTextTextPersonName8);
        spinner = findViewById(R.id.spinner2);
      /*  aSwitch = (findViewById(R.id.switch1));*/

       /* String [] prod = {"produit 1", "produit 2", "produit 3", "produit 4", "produit 5", "produit 6", "produit 7", "produit 8", "produit 9", "produit 10",
        "produit 11", "produit 12", "produit 13", "produit 14", "produit 15", "produit 16", "produit 17", "produit 18", "produit 19", "produit 20"};

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, prod);
        spinner.setAdapter(adapter);*/


    }

//                      ******** TELECHARGER LE DOCUMENT  *******
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.valide_facture, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final String nomsoc = soc.getText().toString();
        final String nsirte = siret.getText().toString();
        final String ntel = tel.getText().toString();
        final Float taxe = Float.valueOf(tva.getText().toString());
        String client = clicli.getText().toString();
        String adresse = adres.getText().toString();
        final String objet = desig.getText().toString();
        final double nombre = Integer.parseInt(qté.getText().toString());
        final double tarif = Double.parseDouble(prix.getText().toString());


        dateObject = new Date();

        if (item.getItemId() == R.id.download && soc.getText().toString().length()==0|| siret.getText().toString().length() == 0 || tel.getText().toString().length()==0||
        clicli.getText().toString().length()==0|| adres.getText().toString().length()==0||
                tva.getText().toString().length()==0|| desig.getText().toString().length()==0|| qté.getText().toString().length()==0||
                prix.getText().toString().length()==0){
            Toast.makeText(getApplicationContext(),"renseignement manquant", Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId() == R.id.download && soc.getText().toString().length()!=0|| siret.getText().toString().length() != 0 || tel.getText().toString().length()!=0||
                clicli.getText().toString().length()!=0|| adres.getText().toString().length()!=0||
                tva.getText().toString().length()!=0|| desig.getText().toString().length()!=0|| qté.getText().toString().length()!=0||
                prix.getText().toString().length()!=0) {
            Toast.makeText(getApplicationContext(), "Facture téléchargé", Toast.LENGTH_LONG).show();

            //           **********  CREATION DU DOCUMENT  *************

            PdfDocument doc = new PdfDocument();
            final Paint paint = new Paint();
            final Paint titlePaint = new Paint();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
            PdfDocument.Page page = doc.startPage(pageInfo);
            final Canvas canvas = page.getCanvas();



            //**********************TAILLE TEXTE ENCADRE *****************************
            paint.setTextSize(30f);
            //*********************** ENCADRER ****************************************
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            canvas.drawRect(20, 780, 1180, 860, paint);
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText("N°", 40, 830, paint);
            canvas.drawText("désignation", 200, 830, paint);
            canvas.drawText("Qté", 700, 830, paint);
            canvas.drawText("Prix", 900, 830, paint);
            canvas.drawText("Total", 1050, 830, paint);

            //*************************** DATE **************************************
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            canvas.drawText("Date: "+ dateFormat.format(dateObject), 880, 640, paint);

            //************************* Input user ********************************
            canvas.drawText("Facturé à: "+ client, 40, 640, paint);
            canvas.drawText(adresse, 40, 670, paint);
            canvas.drawText(nomsoc, 840, 40, paint);
            canvas.drawText("n°siret: "+nsirte, 840, 70, paint);
            canvas.drawText("contact: "+ ntel, 840, 100, paint);

            //************************* Line encadré *************************************
            canvas.drawLine(180, 790, 180, 840, paint);
            canvas.drawLine(680, 790, 680, 840, paint);
            canvas.drawLine(880,790, 880, 840, paint);
            canvas.drawLine(1030, 790, 1030, 840, paint);

            //********************** TITRE***********************************************
            titlePaint.setTextAlign(Paint.Align.CENTER);
            titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            titlePaint.setTextSize(70);
            canvas.drawText("FACTURE", 1200/2, 270, titlePaint);
            //********************** Commande ************************************************
            final double total1 = nombre*tarif;
            canvas.drawText("1.", 40, 950, paint);
                            canvas.drawText(objet, 200 , 950, paint);
                            canvas.drawText(String.valueOf(nombre), 700, 950, paint);
                            canvas.drawText(tarif+" €", 900, 950, paint);

                            canvas.drawText(String.valueOf(total1),1050, 950, paint);

                            paint.setColor(Color.BLACK);


                            //********* SORTIE DU DOCUMENT *******************
            doc.finishPage(page);

            File file = new File(Environment.getExternalStorageDirectory(), client+".pdf");

            try {
                doc.writeTo(new FileOutputStream(file));

            } catch (IOException e) {
                e.printStackTrace();
            }
            doc.close();
        }

        return super.onOptionsItemSelected(item);

    }
}
