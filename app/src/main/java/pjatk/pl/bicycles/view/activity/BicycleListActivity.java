package pjatk.pl.bicycles.view.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pjatk.pl.bicycles.R;
import pjatk.pl.bicycles.helper.BicycleDbHelper;
import pjatk.pl.bicycles.view.fragment.BicycleDetailsFragment;
import pjatk.pl.bicycles.view.fragment.BicycleListFragment;

/**
 * Created by mateuszsielawa on 15.06.2018.
 */
public class BicycleListActivity extends AppCompatActivity implements BicycleListFragment.OnBicycleSelectedListener {

    BicycleDbHelper bicycleDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bicycles_list_fragment);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            BicycleListFragment bicyclesFragment = new BicycleListFragment();
            bicyclesFragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, bicyclesFragment).commit();
        }

        bicycleDbHelper = BicycleDbHelper.getInstance(this);
    }

    @Override
    public void onBicycleSelected(int id) {
        BicycleDetailsFragment bicycleDetailsFragment = (BicycleDetailsFragment)
                getFragmentManager().findFragmentById(R.id.bicycleDetails);

        if (bicycleDetailsFragment != null) {
            bicycleDetailsFragment.loadBicycle(id);
        } else {
            bicycleDetailsFragment = new BicycleDetailsFragment();
            Bundle args = new Bundle();

            args.putInt(BicycleDetailsFragment.ARG_ID, id);
            bicycleDetailsFragment.setArguments(args);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, bicycleDetailsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
