package com.example.nnnn;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class User extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<FoodUser> foodList;
    FoodUserAdapter adapter;
    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        foodList = new ArrayList<>();

        adapter = new FoodUserAdapter(this, foodList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sqLiteHelper = new SQLiteHelper(this, "FoodDB.sqlite", null, 1);

        // Get data from SQLite
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM FOOD");
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            foodList.add(new FoodUser(name, price, image));
        }
        cursor.close(); // Close the cursor to prevent memory leaks

        adapter.notifyDataSetChanged();
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
            Intent intent = new Intent(User.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
            return true;
        }
        if (id == R.id.action_Cart) {
            // Handle the logout action
            Intent intent = new Intent(User.this, CartActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
            return true;
        }
        if (id == R.id.action_search) {
            // Handle the logout action
            Intent intent = new Intent(User.this, SearchActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
            return true;
        }
        if (id == R.id.action_menu) {
            // Handle the logout action
            Intent intent = new Intent(User.this, User.class);
            startActivity(intent);
            finish(); // Close the current activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}