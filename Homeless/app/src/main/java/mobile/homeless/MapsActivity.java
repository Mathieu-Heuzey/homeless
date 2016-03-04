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
import java.util.ArrayList;
import java.util.List;

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

    private List<Person> parseJsonList(String strJson) {

        List<Person> list = new ArrayList<Person>();
        try {
            JSONArray  jsonArray = new JSONArray(strJson);

            for(int i=0; i < jsonArray.length(); i++){
                Person       sdf = new Person();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                sdf.setDescription(jsonObject.getString("Description"));
                sdf.setLatitude(Double.parseDouble(jsonObject.getString("Latitude")));
                sdf.setLongitude(Double.parseDouble(jsonObject.getString("Longitude")));
                sdf.setPersonneId(jsonObject.getString("PersonneId"));
                sdf.setTitre(jsonObject.getString("Titre"));
                list.add(sdf);
            }
        } catch (JSONException e) {e.printStackTrace();}
        return list;
    }

    private void setMarker(List<Person> list, GoogleMap googleMap)
    {
        mMap = googleMap;
        Person currentSdf;
        for (int i=0; i < list.size(); i++)
        {
            currentSdf = list.get(i);
            LatLng mLatLngSdf = new LatLng(currentSdf.getLatitude(),currentSdf.getLongitude());
            mMap.addMarker(new MarkerOptions().position(mLatLngSdf).title(currentSdf.getTitre()));
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
                ",{\"PersonneId\":3,\"Latitude\":48.86173,\"Longitude\":2.344565,\"Titre\":\"Nicolas\",\"Description\":\"Il as faim\"}" +
                ",{\"PersonneId\":4,\"Latitude\":48.86259,\"Longitude\":2.334894,\"Titre\":\"Adrien\",\"Description\":\"Il a soif\"}" +
                ",{\"PersonneId\":5,\"Latitude\":48.86348,\"Longitude\":2.354214,\"Titre\":\"Quentin\",\"Description\":\"Il as besoin d'heroine\"}]";

        ;
        setMarker(parseJsonList(strJson), mMap);
    }
}
