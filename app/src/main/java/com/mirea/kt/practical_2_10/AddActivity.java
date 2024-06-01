package com.mirea.kt.practical_2_10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    private EditText telephoneModel;
    private EditText telephoneSerialNumber;
    private EditText telephonePrice;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        Log.d("AddActivity", "Created");
        telephoneModel = findViewById(R.id.telephone_model);
        telephoneSerialNumber = findViewById(R.id.serial_number);
        telephonePrice = findViewById(R.id.telephone_price);
        Button buttonAdd = findViewById(R.id.add_button);
        Button buttonContinue = findViewById(R.id.continue_button);

        buttonAdd.setOnClickListener(v -> {
            if (this.dbHelper != null){
                String model = telephoneModel.getText().toString();
                String serialNumber = telephoneSerialNumber.getText().toString();
                String price = telephonePrice.getText().toString();

                if (!model.isEmpty() && !serialNumber.isEmpty() && !price.isEmpty()){
                    boolean result = dbHelper.saveTelephoneToDatabase(new Telephone(model, serialNumber, Integer.parseInt(price)));
                    if (result){
                        Toast.makeText(this, "Данные успешно добавлены",Toast.LENGTH_LONG).show();
                        Log.d("AddActivity", "Telephone added");
                    }else{
                        Toast.makeText(this, "При добавлении данных произошла ошибка",Toast.LENGTH_LONG).show();
                        Log.d("AddActivity", "Adding error");
                    }
                }else {
                    Toast.makeText(this, "Непраильно введены значения",Toast.LENGTH_LONG).show();
                    Log.d("AddActivity", "Values Error");
                }
            }
        });

        this.dbHelper = new DBHelper(new MySQLiteHelper(this, "telephone_database.db", null, 1));

        buttonContinue.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), TelephoneListActivity.class));
        });

    }
}