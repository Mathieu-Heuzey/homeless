package mobile.homeless;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.content.Context;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.loopj.android.http.*;
import org.json.JSONArray;
import cz.msebera.android.httpclient.Header;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private void showPersonOnMap() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.get("http://163.5.84.232/WebService/api/Personnes", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                setMarkers(JSONToListPerson(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("ShowPerson", "ERROR");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private List<Person> JSONToListPerson(JSONArray jsonArray) {
        List<Person> listPerson = new ArrayList<Person>();
        try {
            for(int i=0; i < jsonArray.length(); i++){
                Person person = new Person();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                person.setPersonneId(jsonObject.getString("PersonneId"));
                person.setLatitude(Double.parseDouble(jsonObject.getString("Latitude")));
                person.setLongitude(Double.parseDouble(jsonObject.getString("Longitude")));
                person.setTitre(jsonObject.getString("Titre"));
                person.setDescription(jsonObject.getString("Description"));
                listPerson.add(person);
            }
        } catch (JSONException e) {e.printStackTrace();}
        return listPerson;
    }

    private void setMarkers(List<Person> listPerson) {
        for (Person person : listPerson)
        {
            LatLng mLatLngSdf = new LatLng(person.getLatitude(), person.getLongitude());
            mMap.addMarker(new MarkerOptions().position(mLatLngSdf).title(person.getTitre()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLngSdf));
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Ma position
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        LatLng mLatLng = new LatLng(latitude, longitude);
//        CameraUpdate center= CameraUpdateFactory.newLatLng(mLatLng);
//        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
//        mMap.moveCamera(center);
//        mMap.animateCamera(zoom);
        mMap.addMarker(new MarkerOptions().position(mLatLng).title("My Location").snippet("and snipet").
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 13));

        showPersonOnMap();
    }
}
