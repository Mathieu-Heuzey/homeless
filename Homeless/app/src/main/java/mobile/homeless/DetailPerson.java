package mobile.homeless;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class DetailPerson extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        String titre = "";
        String description = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            titre = extras.getString("Titre");
            description = extras.getString("Description");
        }
        final String personneId = (extras != null ? extras.getString("PersonneId") : "");

        TextView titreTextView = (TextView) findViewById(R.id.Titre);
        TextView descriptionTextView = (TextView) findViewById(R.id.Description);
        titreTextView.append(titre);
        descriptionTextView.append(description);
        final Button button = (Button) findViewById(R.id.supprimer);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();
                client.delete("http://163.5.84.232/WebService/api/Personnes/" + personneId, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                        finish();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                    }
                });
            }
        });
        Handler handler = new Handler();
        int millisDelay = 2000;
        handler.postDelayed(task, millisDelay);
    }
    private Runnable task = new Runnable() {
        public void run() {
            // Execute your delayed code
            finish();
        }
    };


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AjoutSdf Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://mobile.homeless/http/host/path")
        );
        AppIndex.AppIndexApi.start(client2, viewAction);
    }
    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AjoutSdf Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://mobile.homeless/http/host/path")
        );
        AppIndex.AppIndexApi.end(client2, viewAction);
        client2.disconnect();
    }
}
