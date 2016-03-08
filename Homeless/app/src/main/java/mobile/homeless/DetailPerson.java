package mobile.homeless;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DetailPerson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String titre = "";
        String description = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            titre = extras.getString("Titre");
            description = extras.getString("Description");
        }

        TextView titreTextView = (TextView) findViewById(R.id.Titre);
        TextView descriptionTextView = (TextView) findViewById(R.id.Description);
        titreTextView.append(titre);
        descriptionTextView.append(description);
    }

}
