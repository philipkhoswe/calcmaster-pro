package com.calcmaster.pro;
import android.app.Activity; import android.os.Bundle; import android.content.Intent; import android.widget.Button; import android.widget.Toast;
public class MainActivity extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button basic = findViewById(R.id.basicButton);

    basic.setOnClickListener(v -> {
        Intent intent = new Intent(
            MainActivity.this,
            BasicCalculatorActivity.class
        );
        startActivity(intent);
    });

    setupOtherButtons(R.id.scientificButton, "Scientific Calculator");
    setupOtherButtons(R.id.studentButton, "Student Tools");
    setupOtherButtons(R.id.businessButton, "Business Tools");
    setupOtherButtons(R.id.everydayButton, "Everyday Tools");
}

private void setupOtherButtons(int id, String name) {
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