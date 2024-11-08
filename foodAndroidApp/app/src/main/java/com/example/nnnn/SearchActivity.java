package com.example.nnnn;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    Button searchButton;
    Spinner spinner2;
    SQLiteHelper sqLiteHelper;
    ArrayList<String> matchingPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        searchButton = findViewById(R.id.button);
        spinner2 = findViewById(R.id.spinner2);

        sqLiteHelper = new SQLiteHelper(this, "FoodDB.sqlite", null, 1);
        matchingPrices = new ArrayList<>();

        // Set the button click listener to search and populate the spinner
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAndPopulateSpinner();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            // Handle the logout action
            Intent intent = new Intent(SearchActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
            return true;
        }
        if (id == R.id.action_Cart) {
            // Handle the logout action
            Intent intent = new Intent(SearchActivity.this, CartActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
            return true;
        }
        if (id == R.id.action_search) {
            // Handle the logout action
            Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
            return true;
        }
        if (id == R.id.action_menu) {
            // Handle the logout action
            Intent intent = new Intent(SearchActivity.this, User.class);
            startActivity(intent);
            finish(); // Close the current activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchAndPopulateSpinner() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            // No radio button is selected, do nothing or show a message
            return;
        }

        String query = "";
        if (selectedId == R.id.radioButton1) {
            // Price less than 50
            query = "SELECT * FROM FOOD WHERE CAST(price AS INTEGER) < 50";
        } else if (selectedId == R.id.radioButton2) {
            // Price higher than 50
            query = "SELECT * FROM FOOD WHERE CAST(price AS INTEGER) > 50";
        }

        matchingPrices.clear(); // Clear the list before adding new results

        Cursor cursor = sqLiteHelper.getData(query);
        while (cursor.moveToNext()) {
            String price = cursor.getString(2); // Assuming price is in the 3rd column
            matchingPrices.add(price); // Add matching prices to the list
        }
        cursor.close(); // Close the cursor to prevent memory leaks

        // Populate the spinner with the matching prices
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, matchingPrices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
    }
}
