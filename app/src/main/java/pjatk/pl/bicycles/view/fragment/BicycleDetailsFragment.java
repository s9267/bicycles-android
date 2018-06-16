package pjatk.pl.bicycles.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import pjatk.pl.bicycles.R;
import pjatk.pl.bicycles.helper.BicycleDbHelper;
import pjatk.pl.bicycles.helper.DownloadImageTask;
import pjatk.pl.bicycles.model.Bicycle;

/**
 * Created by mateuszsielawa on 15.06.2018.
 */

public class BicycleDetailsFragment extends Fragment {

    public static final String ARG_ID = "bicycle_id";
    private BicycleDbHelper bicycleDbHelper = BicycleDbHelper.getInstance(getContext());

    private TextView id;
    private TextView name;
    private TextView description;
    private TextView price;
    private ImageView picture;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bicycle_details, container,
                false);

        id = view.findViewById(R.id.bicycle_id);
        name = view.findViewById(R.id.bicycle_name);
        description = view.findViewById(R.id.bicycle_description);
        price = view.findViewById(R.id.bicycle_price);
        picture = view.findViewById(R.id.bicycle_picture);

        if (getArguments() != null) {
            int id = getArguments().getInt(ARG_ID);
            loadBicycle(id);
        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getArguments() != null) {
            int id = getArguments().getInt(ARG_ID);
            loadBicycle(id);
        }
    }

    public void loadBicycle(Integer id) {
        Optional<Bicycle> optional = bicycleDbHelper.get(id);

        if (optional.isPresent()) {
            Bicycle bicycle = optional.get();
            try {
                this.id.setText(bicycle.getId().toString());
                name.setText(bicycle.getName());
                description.setText(bicycle.getDescription());
                price.setText(bicycle.getPrice().toPlainString());
                picture.setImageDrawable(new DownloadImageTask().execute(bicycle.getPicture()).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
