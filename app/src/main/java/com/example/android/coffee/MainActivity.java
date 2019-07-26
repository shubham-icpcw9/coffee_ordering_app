package com.example.android.coffee;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private int x = 2;
    /**
     * hi
     * This method is called when the order button is clicked.
     */
    public int calculatePrice(int quantity)
    {
        return quantity * 5;
    }

    public void submitOrder(View view)
    {
        int price = calculatePrice(x);
        CheckBox whippedCream = findViewById(R.id.whipped_cream);
        boolean isWhippedCream = whippedCream.isChecked();
        if(isWhippedCream)
            price += x;
        CheckBox oChoco = findViewById(R.id.choco);
        boolean isChoco = oChoco.isChecked();
        if(isChoco)
            price += x * 2;

        CheckBox otm = findViewById(R.id.tm);
        boolean istm = otm.isChecked();
        if(istm)
            price += x * 3;

        EditText oName = findViewById(R.id.name);
        String  uName = oName.getText().toString();
        EditText odelName = findViewById(R.id.dname);
        String  delName = odelName.getText().toString();
        String orderSummary = createOrderSummary(price, isWhippedCream, isChoco, istm,  uName, delName);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.coffee));
        String []hello = new String[1];
        hello[0] = "JasCaafe@gmail.com";
        intent.putExtra(Intent.EXTRA_EMAIL, hello);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public String createOrderSummary(int price, boolean isWhippedCream, boolean isChoco, boolean istm, String uName, String delName)
    {
        return getString(R.string.ordersumName, uName) + "\n" +  getString(R.string.addName) + delName+ "\n" + getString(R.string.Quantity)+ ": " + x + "\n"+ getString(R.string.is_whipped) + " " + isWhippedCream + "\n"+ getString(R.string.choco)+ " " + isChoco + "\n"+ getString(R.string.tms)+ " " + istm + "\n" + getString(R.string.tot) + price + "\n" + getString(R.string.thanku);
    }

    public void Increment(View view)
    {
        if(x <= 4) {
            x++;
            display(x);
        }
        else
            Toast.makeText(getApplicationContext(), "You can't order coffee more than 5 at a time", Toast.LENGTH_SHORT).show();
    }

    public void Decrement(View view)
    {
        if(x >= 2) {
            x--;
            display(x);
        }
        else
            Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    public void map(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:21.17,72.83"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void call(View v)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "+917046920481"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}