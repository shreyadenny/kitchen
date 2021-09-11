package com.example.kitchen;

import android.content.Intent;
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

public class EditorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    String unit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        //name input
        EditText editText_name = (EditText)findViewById(R.id.edit_ingredient_name);
        String name = editText_name.getText().toString();

        //quantity
        TextView quantity = (TextView)findViewById(R.id.edit_quantity);
        String q = "1";
        quantity.setText(q);

        //Spinner Values
        spinner = (Spinner) findViewById(R.id.spinner_gender);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);



        if(unit=="gm") {
            q = "100";
            quantity.setText(q);
        }
        else if(unit=="ml")
        {
            q = "500";
            quantity.setText(q);
        }
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        unit = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), unit, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}
