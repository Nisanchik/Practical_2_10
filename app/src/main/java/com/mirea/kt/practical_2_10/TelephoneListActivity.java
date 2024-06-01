package com.mirea.kt.practical_2_10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TelephoneListActivity extends AppCompatActivity {

    private DBHelper dbHelper = new DBHelper(new MySQLiteHelper(this, "telephone_database.db", null, 1));
    private ArrayList<Telephone> telephones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephone_list);
        Log.d("TelephoneListActivity", "Created");
        Toolbar tb = findViewById(R.id.toolbar);

        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        telephones = dbHelper.loadAllTelephonesFromDatabase();
        Log.d("TelephoneListActivity", "Database loaded");

        RecyclerView rcView = findViewById(R.id.recyclerView);
        TelephoneAdapter adapter = new TelephoneAdapter(telephones);
        rcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcView.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            Log.d("TelephoneListActivity", "Finished");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}