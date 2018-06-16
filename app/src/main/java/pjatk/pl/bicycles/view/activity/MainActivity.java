package pjatk.pl.bicycles.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import pjatk.pl.bicycles.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_bicycles:
                Intent bicycleIntent = new Intent(this, BicycleListActivity.class);
                startActivityForResult(bicycleIntent, 0);
                return true;
            case R.id.action_show_clients:
                Intent clientIntent = new Intent(this, ClientListActivity.class);
                startActivityForResult(clientIntent, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
