package mobile.homeless;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class AjoutSdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_sdf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void saveDescription(View view)
    {
        EditText et = (EditText)(findViewById(R.id.editText));
        String titre = et.getText().toString();

        EditText desc = (EditText)(findViewById(R.id.TFdescription));
        String description = desc.getText().toString();
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        Log.d("ADebugTag", "Value: " + titre);
        Log.d("ADebugTag", "Value: " + description);
        Log.d("ADebugTag", "Value: " + longitude);
        Log.d("ADebugTag", "Value: " + latitude);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Titre", titre);
        params.put("Description", description);
        params.put("Longitude", -95);
        params.put("Latitude", 44);
        client.post("http://163.5.84.232/WebService/api/Personnes", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("ShowPerson", "ERROR");
            }
        });
        finish();
    }

}
