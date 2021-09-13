package com.example.kitchen;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditorActivity extends AppCompatActivity{
    Spinner spinner;
    String unit;
    int q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        //name input
        EditText editText_name = (EditText)findViewById(R.id.edit_ingredient_name);
        String name = editText_name.getText().toString();

        //quantity
        TextView quantity = (TextView)findViewById(R.id.edit_quantity);


        //Spinner Values
        spinner = (Spinner) findViewById(R.id.spinner_gender);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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



//        public void insertIngredient(){
//            SQLiteDatabase db =
//        }

        //Actions on clicking the save button after entering new ingredient data
//        Button save = (Button) findViewById(R.id.save);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Save");
//                insertIngredient();
//                displayDatabaseInfo();
//            }
//        });
    }

    //increment quantity
    public void increment(View view) {
        switch (unit){
            case "Kg":
            case "L":
            case "dozen":
            case "packets":
                q=q+1;
                break;
            case "gm":
                q=q+100;
                break;
            case "ml":
                q=q+500;
                break;
            default:
                break;

        }
       display(q);
    }
    //decrement quantity
    public void decrement(View view) {
        switch (unit){
            case "Kg":
            case "L":
            case "dozen":
            case "packets":
                q=q-1;
                break;
            case "gm":
                q=q-100;
                break;
            case "ml":
                q=q-500;
                break;
            default:
                break;

        }
        if(q<0){q=0;}
        display(q);
    }

    //display quantity
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.edit_quantity);
        quantityTextView.setText("" + number);
    }

}
