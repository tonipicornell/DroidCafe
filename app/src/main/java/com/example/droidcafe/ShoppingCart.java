package com.example.droidcafe;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShoppingCart extends AppCompatActivity {

    private Spinner mSpinner;
    private Toolbar mToolbar;

    private TextView informationCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopping_cart);

        mSpinner = findViewById(R.id.phone_choose);
        mToolbar = findViewById(R.id.toolbarShopping);

        informationCart = findViewById(R.id.informationCart);

        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        mToolbar.setNavigationContentDescription("Back");

        mToolbar.setNavigationOnClickListener(new View.OnClickListener()  {
            public void onClick(View v){
                Intent intent = new Intent(ShoppingCart.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        String cartMessage = getIntent().getStringExtra("cart_message");

        if (cartMessage != null && !cartMessage.isEmpty()) {
            informationCart.setText(cartMessage);
        } else {
            informationCart.setText("No items added to the shopping cart");
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.phone_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}