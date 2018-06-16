package pjatk.pl.bicycles.view.activity;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pjatk.pl.bicycles.R;
import pjatk.pl.bicycles.model.Client;
import pjatk.pl.bicycles.model.ClientAdapter;

public class ClientListActivity extends ListActivity {

    private static String URL = "https://jsonplaceholder.typicode.com/users";

    ClientAdapter clientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        List<Client> clients = getClients();

        clientAdapter = new ClientAdapter(this,
                R.layout.activity_client, clients);
        setListAdapter(clientAdapter);

    }

    private List<Client> getClients() {

        final List<Client> listValues = new ArrayList<Client>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject employee =response.getJSONObject(i);
                        String firstname = employee.getString("name");
                        String mail = employee.getString("email");

                        Client client = new Client();
                        client.setName(firstname);
                        client.setEmail(mail);
                        listValues.add(client);
                        clientAdapter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest response", error.toString());
            }
        });
        requestQueue.add(objectRequest);
        return listValues;
    }

}
