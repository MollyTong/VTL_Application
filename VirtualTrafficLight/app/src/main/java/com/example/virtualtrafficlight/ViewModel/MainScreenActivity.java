package com.example.virtualtrafficlight.ViewModel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.virtualtrafficlight.Model.Point;
import com.example.virtualtrafficlight.R;
import com.example.virtualtrafficlight.Repository.Repository;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainScreenActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    Intent intent;
    SupportMapFragment mapFragment;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private final long min_time = 1000;
    private final long min_dis = 10;
    private LatLng latLng;
    private TextView X;
    private TextView Y;
    private double lont, lat, direction;
    private ImageView light, left, right, ahead, back;
    private Repository repository = new Repository();
    Marker mPositionMarker;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void onRequestPermissionResult(int requestCode,String[] permissions, int[] grantResults){
        switch(requestCode){
            case 10:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    configure();
        }
    }

    private void configure(){
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, min_time, min_dis, locationListener);
        }catch(SecurityException e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        intent = getIntent();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                try {
                    lat = location.getLatitude();
                    lont = location.getLongitude();
                    direction = location.getBearing();
                    latLng = new LatLng(lat, lont);
                    X = (TextView) findViewById(R.id.textX);
                    Y = (TextView) findViewById(R.id.textY);
                    light = findViewById(R.id.light);
                    left = findViewById(R.id.left);
                    right = findViewById(R.id.right);
                    ahead = findViewById(R.id.ahead);
                    back = findViewById(R.id.back);
                    X.setText(""+lat);
                    Y.setText(""+lont);

                    mPositionMarker = map.addMarker(new MarkerOptions().position(latLng).title("Your location"));
//                    mPositionMarker = map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
//                                    .fromResource(R.drawable.car)).anchor(0.5f, 0.5f).position(latLng));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    if (repository.onMessage(lat,lont,direction)){
                        light.setImageResource(R.drawable.green);
                        ahead.setImageResource(R.drawable.aheadgreen);
                    }else{
                        light.setImageResource(R.drawable.red);
                        left.setImageResource(R.drawable.leftgrey);
                        right.setImageResource(R.drawable.rightgrey);
                        ahead.setImageResource(R.drawable.aheadgrey);
                        back.setImageResource(R.drawable.backgrey);
                    }
                }catch(SecurityException e){
                    e.printStackTrace();
                }
            }

            public void onProviderDisabled(String s){
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);
            return;
        } else {
            configure();
        }

    }
}
