package pjatk.pl.bicycles.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pjatk.pl.bicycles.R;

/**
 * Created by mateuszsielawa on 16.06.2018.
 */
public class ClientAdapter extends ArrayAdapter<Client> {

    public ClientAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ClientAdapter(Context context, int resource, List<Client> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_client_layout, null);
        }

        Client p = getItem(position);

        if (p != null) {
            TextView tt1 = v.findViewById(R.id.username);
            TextView tt2 = v.findViewById(R.id.client_name);

            if (tt1 != null) {
                tt1.setText(p.getName());
            }

            if (tt2 != null) {
                tt2.setText(p.getEmail());
            }
        }

        return v;
    }

}
