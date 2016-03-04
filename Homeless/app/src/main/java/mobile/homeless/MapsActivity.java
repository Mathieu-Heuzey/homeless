package mobile.homeless;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.Console;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        System.out.println(longitude);
        System.out.println(latitude);
        LatLng mLatLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(mLatLng).title("My Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLng));

        String strJson=
                "[{\"PersonneId\":1,\"Latitude\":48.861832,\"Longitude\":2.338057,\"Titre\":\"Test\",\"Description\":\"Coucou\"}" +
                ",{\"PersonneId\":2,\"Latitude\":48.86642,\"Longitude\":2.337307,\"Titre\":\"Personne\",\"Description\":\"Bonsoir\"}" +
                ",{\"PersonneId\":3,\"Latitude\":48.86173,\"Longitude\":2.344565,\"Titre\":\"Nicolas\",\"Description\":\"Il as faim\"}]";
        try {
            JSONArray  jsonArray = new JSONArray(strJson);

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("PersonneId");
                String lat = jsonObject.getString("Latitude");
                String lon = jsonObject.getString("Longitude");
                String titre = jsonObject.getString("Titre");
                String desc = jsonObject.getString("Description");
                Double lati = Double.parseDouble(lat);
                Double longi = Double.parseDouble(lon);

                LatLng mLatLngSdf = new LatLng(lati, longi);
                mMap.addMarker(new MarkerOptions().position(mLatLngSdf).title(titre));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLng));

//                System.out.println("TOTO");
//                System.out.println(id);
//                System.out.println(lat);
            }
        } catch (JSONException e) {e.printStackTrace();}
    }
}
