package pjatk.pl.bicycles.view.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

import pjatk.pl.bicycles.R;
import pjatk.pl.bicycles.helper.BicycleDbHelper;
import pjatk.pl.bicycles.model.Bicycle;

public class CreateBicycleActivity extends AppCompatActivity {

    BicycleDbHelper dbHelper = BicycleDbHelper.getInstance(this);

    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextPrice;
    private EditText editTextPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bicycle);

        editTextName = findViewById(R.id.name);
        editTextDescription = findViewById(R.id.description);
        editTextPrice = findViewById(R.id.price);
        editTextPicture = findViewById(R.id.picture);

        Button saveBicycle = findViewById(R.id.save_bicycle_button);
        saveBicycle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    private void save() {

        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        String price = editTextPrice.getText().toString();
        String picture = editTextPicture.getText().toString();

        dbHelper.create(new Bicycle(name, description, new BigDecimal(price), picture));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Zapisano!");
        builder.setMessage(R.string.success)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

