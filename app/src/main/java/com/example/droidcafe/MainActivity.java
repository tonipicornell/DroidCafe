package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ImageView donuts;
    private ImageView iceCream;
    private ImageView froYo;
    private Spinner spinnerOptions;

    private FloatingActionButton buttonFloatCart;
    private ImageButton optionsButton;

    private String selectedMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        donuts = findViewById(R.id.donuts);
        iceCream = findViewById(R.id.ice_cream);
        froYo = findViewById(R.id.froyo);

        buttonFloatCart = findViewById(R.id.floatingActionButton);

        spinnerOptions = findViewById(R.id.options_spinner);

        optionsButton = findViewById(R.id.options_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item);
        spinnerOptions.setAdapter(adapter);

        optionsButton.setOnClickListener(view -> {
            if (spinnerOptions.getVisibility() == View.GONE) {
                spinnerOptions.setVisibility(View.VISIBLE);
            } else {
                spinnerOptions.setVisibility(View.GONE);
            }
        });

        donuts.setOnClickListener(view -> {
            selectedMessage = "You ordered a donut";
            Toast.makeText(this, "You ordered a donut", Toast.LENGTH_SHORT).show();
        });

        iceCream.setOnClickListener(view -> {
            selectedMessage = "You ordered a ice cream";
            Toast.makeText(this, "You ordered an ice cream sandwich", Toast.LENGTH_SHORT).show();
        });

        froYo.setOnClickListener(view -> {
            selectedMessage = "You ordered a fro yo";
            Toast.makeText(this, "You ordered a fro yo", Toast.LENGTH_SHORT).show();
        });

        buttonFloatCart.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ShoppingCart.class);
            intent.putExtra("cart_message", selectedMessage);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}