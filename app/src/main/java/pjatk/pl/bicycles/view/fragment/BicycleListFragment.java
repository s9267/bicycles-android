package pjatk.pl.bicycles.view.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import pjatk.pl.bicycles.R;
import pjatk.pl.bicycles.helper.BicycleDbHelper;
import pjatk.pl.bicycles.model.Bicycle;
import pjatk.pl.bicycles.view.activity.CreateBicycleActivity;

/**
 * Created by mateuszsielawa on 15.06.2018.
 */
public class BicycleListFragment extends ListFragment {

    private BicycleDbHelper bicycleDbHelper;

    OnBicycleSelectedListener mCallback;

    public interface OnBicycleSelectedListener {
        void onBicycleSelected(int id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bicycleDbHelper = BicycleDbHelper.getInstance(getContext());

        List<Bicycle> bicycles = bicycleDbHelper.findAll();

        ArrayAdapter<Bicycle> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, bicycles);
        setListAdapter(adapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bicycles_list, container,
                false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getContext(), CreateBicycleActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Bicycle bicycle = (Bicycle) l.getAdapter().getItem(position);
        mCallback.onBicycleSelected(bicycle.getId());
        getListView().setItemChecked(position, true);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getFragmentManager().findFragmentById(R.id.bicycles_list) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Bicycle> bicycles = bicycleDbHelper.findAll();

        ArrayAdapter<Bicycle> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_selectable_list_item, bicycles);
        setListAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {

            mCallback = (OnBicycleSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnBicycleSelectedListener");
        }
    }
}
