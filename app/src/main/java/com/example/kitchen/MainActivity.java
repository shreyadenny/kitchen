package com.example.kitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;




import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private ingredientDbHelper mDbHelper;
    public Cursor cursor;
    int q;
    // Array of strings...
    ListView simpleList;
    String animalList[] = {"rice", "wheat", "oil", "milk", "salt", "sugar", "tomatoes", "tea", "coffee", "turmeric", "chilli powder"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button Clicked");
                startActivity(new Intent(MainActivity.this, EditorActivity.class));
            }
        });


        displayDatabaseInfo();

        //to display the list of ingredients
        simpleList = (ListView) findViewById(R.id.simpleListView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.textView, animalList);
        simpleList.setAdapter(arrayAdapter);

    }
    private void displayDatabaseInfo(){
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new ingredientDbHelper(this);


        TextView displayView = (TextView) findViewById(R.id.text);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String projection[]={IngredientContract.IngredientEntry._ID,
                IngredientContract.IngredientEntry.COLUMN_INGREDIENT_NAME,
                IngredientContract.IngredientEntry.COLUMN_INGREDIENT_MEASUREMENT,
                IngredientContract.IngredientEntry.COLUMN_INGREDIENT_QUANTITY};
        Cursor cursor = db.query(
            IngredientContract.IngredientEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null);
        try {
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(IngredientContract.IngredientEntry._ID + " - " +
                   IngredientContract.IngredientEntry.COLUMN_INGREDIENT_NAME + " - " +
                   IngredientContract.IngredientEntry.COLUMN_INGREDIENT_MEASUREMENT + " - " +
                    IngredientContract.IngredientEntry.COLUMN_INGREDIENT_QUANTITY + " - " + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(IngredientContract.IngredientEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_INGREDIENT_NAME);
            int measurementIndex = cursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_INGREDIENT_MEASUREMENT);
            int quantityIndex = cursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_INGREDIENT_QUANTITY);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentMeasurement = cursor.getString(measurementIndex);
                String currentQuantity = cursor.getString(quantityIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " + currentMeasurement + " - " + currentQuantity));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
    //insert Ingredient
    public void insertIngredient(){


        ingredientDbHelper mDbHelper = new ingredientDbHelper(this);
        //Spinner Values
        Spinner spinner = (Spinner) findViewById(R.id.spinner_gender);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String unit;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit = parent.getItemAtPosition(position).toString();


                switch(position){
                    case 0:
                    case 2:
                    case 4:
                    case 5:
                        q=1;
                        break;
                    case 1:
                        q=100;
                        break;
                    case 3:
                        q=500;
                        break;
                    default:
                        break;
                }
                display(q);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(IngredientContract.IngredientEntry.COLUMN_INGREDIENT_NAME, "Rice");
        values.put(IngredientContract.IngredientEntry.COLUMN_INGREDIENT_MEASUREMENT, IngredientContract.IngredientEntry.MEASUREMENT_GM);
        values.put(IngredientContract.IngredientEntry.COLUMN_INGREDIENT_QUANTITY, q);


        long newRowId = db.insert(IngredientContract.IngredientEntry.TABLE_NAME, null, values);

        if(newRowId == -1){
            Toast.makeText(this,"Error with saving ingredient", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Ingredient saved with row id:" + newRowId, Toast.LENGTH_SHORT).show();
        }

    }
    //display quantity
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.edit_quantity);
        quantityTextView.setText("" + number);
    }


}
