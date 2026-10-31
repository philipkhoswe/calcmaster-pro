package com.calcmaster.pro;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButton(R.id.basicButton, "Basic Calculator");
        setupButton(R.id.scientificButton, "Scientific Calculator");
        setupButton(R.id.studentButton, "Student Tools");
        setupButton(R.id.businessButton, "Business Tools");
        setupButton(R.id.everydayButton, "Everyday Tools");
    }

    private void setupButton(int id, String name) {
        Button button = findViewById(id);

        button.setOnClickListener(v ->
            Toast.makeText(
                this,
                name + " - Coming Soon",
                Toast.LENGTH_SHORT
            ).show()
        );
    }
}