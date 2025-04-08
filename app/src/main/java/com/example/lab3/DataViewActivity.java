package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DataViewActivity extends AppCompatActivity {

    private TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        textViewData = findViewById(R.id.textViewData);

        String content = FileHelper.readFromFile(this, "data.txt");
        if (content.isEmpty()) {
            textViewData.setText("Дані відсутні у файлі");
        } else {
            textViewData.setText(content);
        }
    }
}
