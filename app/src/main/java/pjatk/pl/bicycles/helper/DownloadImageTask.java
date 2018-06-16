package pjatk.pl.bicycles.helper;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by mateuszsielawa on 15.06.2018.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Drawable> {

    @Override
    protected Drawable doInBackground(String... urls) {
        try {
            InputStream is = (InputStream) new URL(urls[0]).getContent();
            return Drawable.createFromStream(is, "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
