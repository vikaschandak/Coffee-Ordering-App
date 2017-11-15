package com.apps.vikas.justjava; /**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.vikas.justjava.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox creamCheckBox = (CheckBox) findViewById(R.id.cream_check_box);
        CheckBox choclateCheckBox = (CheckBox) findViewById(R.id.choclate_check_box);
        EditText nameField = (EditText) findViewById(R.id.name);
        String name = nameField.getText().toString();
        boolean hasChoclate = choclateCheckBox.isChecked();
        boolean hasCream = creamCheckBox.isChecked();
        int price = calculatePrice(hasCream, hasChoclate);
        String a = createOrderSummary(name, price, hasCream, hasChoclate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, a);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"16ucs211@lnmiit.ac.in"});


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "Number of coffee cups cannot be greater than 100", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "Number of coffee cups cannot be less than 1", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
    }

    private int calculatePrice(boolean cream, boolean choclate) {
        int basePrice = 5;
        if (cream) {
            basePrice += 1;
        }
        if (choclate) {
            basePrice += 2;
        }

        return quantity * basePrice;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */

    private String createOrderSummary(String name, int price, boolean b, boolean c) {
        String a = "Name:" + name + "\nWhipped Cream:" + b +
                "\nChoclate:" + c + "\nQuantity:" + quantity + "\nTotal Price: $" + price + "\nThank You!!";
        return a;
    }
}