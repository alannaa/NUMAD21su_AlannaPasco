package neu.edu.madcourse.numad21su_alannapasco.Locator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import neu.edu.madcourse.numad21su_alannapasco.R;

public class Locator extends AppCompatActivity implements LocationListener {

    TextView longitudeText;
    TextView latitudeText;
    LocationManager locManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);

        longitudeText = findViewById(R.id.long_value_id);
        latitudeText = findViewById(R.id.lat_value_id);

        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //PERMISSIONS
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

        //REQUEST LOCATION UPDATES
        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, this);
        Location location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        //GET THE LONG/LAT AND DISPLAY
        onLocationChanged(location);

        //STOP TRACKING, RELEASE RESOURCES
        if(locManager!=null){
            locManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        longitudeText.setText(longitude.toString());
        latitudeText.setText(latitude.toString());
    }
}