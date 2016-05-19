package koemdzhiev.com.activityrecognitionfromtutorial;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    public GoogleApiClient mApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Intent intent = new Intent(this,ActivityRecognizedService.class);
            PendingIntent pendingIntent = PendingIntent.getService(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(mApiClient,
                3000,pendingIntent);
        Log.d(TAG,"onConnected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG,"onConnectionSuspended");
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG,"onConnectionFailed");
    }
}
