package lucagrasso.clientcolonnine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.location.LocationManager;
import android.location.LocationListener;
import android.view.View.OnClickListener;
import android.content.Context;

import android.util.Log;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

import android.location.Location;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  ActivityCompat.OnRequestPermissionsResultCallback{

    private String TAG = "MyActivity";
    private EditText latInput;
    private EditText lonInput;
    private EditText minpowerkwInput;

    private boolean isValidLat = false, isValidLon = false, isValidMin = false;

    private double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // creo il layout della activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creo delle variabili che si riferiscono agli elementi della activity: bottone submit, caselle di testo
        final Button btn = (Button) findViewById(R.id.cerca);
        latInput = (EditText) findViewById(R.id.Latitudine);
        lonInput = (EditText) findViewById(R.id.Longitudine);
        minpowerkwInput = (EditText) findViewById(R.id.minPowerKW);
        final Button btn1 = (Button) findViewById(R.id.preleva_coordinate);

        btn.setEnabled(false);

        boolean permissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if(permissionGranted) {
            Log.i(TAG, "granted");
            LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            LocationListener locListener = new MyLocationListener();
            try {
                locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
                List<String> providers = locManager.getProviders(true);
                Location loc = null;
                for (String provider : providers) {
                    loc = locManager.getLastKnownLocation(provider);
                    if (loc == null) {
                        continue;
                    }
                }

                lat = loc.getLatitude();
                lon = loc.getLongitude();
            } catch (SecurityException ex) {
                Log.i(TAG, ex.getMessage() + ex.toString());
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }


    // applico un onclick listener al bottone: quando l'utente clicca sul bottone,
        // si apre la activity ListActivity e si passa a questa activity i dati
        // inseriti dall'utente (latitudine, longitudine, minpowerkw)
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), ListActivity.class);
                intent.putExtra("longitudine", lonInput.getText().toString());
                intent.putExtra("latitudine", latInput.getText().toString());
                intent.putExtra("minpowerkw", minpowerkwInput.getText().toString());
                intent.putExtra("mylongitudine", lon);
                intent.putExtra("mylatitudine", lat);

                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                latInput.setText(lat+"");
                lonInput.setText(lon+"");
            }
        });


        // ogni casella di testo ha un listener che controlla se i dati inseriti sono numeri
        // validi, se così non è non viene attivato il bottone ricerca
        latInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Double.parseDouble(s.toString());
                    isValidLat = true;
                    if(isValidLon && isValidMin)
                        btn.setEnabled(true);
                } catch (NumberFormatException ex){
                    isValidLat = false;
                    btn.setEnabled(false);
                }
            }
        });

        lonInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Double.parseDouble(s.toString());
                    isValidLon = true;
                    if(isValidLat && isValidMin)
                        btn.setEnabled(true);
                } catch (NumberFormatException ex){
                    isValidLon = false;
                    btn.setEnabled(false);
                }
            }
        });

        minpowerkwInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Integer.parseInt(s.toString());
                    isValidMin = true;
                    if(isValidLon && isValidLat)
                        btn.setEnabled(true);
                } catch (NumberFormatException ex){
                    isValidMin = false;
                    btn.setEnabled(false);
                }
            }
        });
    }

    public class MyLocationListener implements LocationListener
    {

        @Override
        public void onLocationChanged(Location loc){
            lat = loc.getLatitude();
            lon = loc.getLongitude();
        }

        @Override
        public void onProviderDisabled(String provider){

        }

        @Override
        public void onProviderEnabled(String provider){

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras){

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 200: {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // {Some Code}
                }
            }
        }
    }




}
