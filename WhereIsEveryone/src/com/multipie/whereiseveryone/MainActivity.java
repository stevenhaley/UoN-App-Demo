package com.multipie.whereiseveryone;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements OnConnectionFailedListener, ConnectionCallbacks, LocationListener {
	private GoogleApiClient googleClient;
	private Marker userPosition;
	private GoogleMap map;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MapFragment mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
		map = mapFrag.getMap();
		map.getUiSettings().setZoomControlsEnabled(true);
		map.setMyLocationEnabled(true);

		MarkerOptions userPosOptions = new MarkerOptions().position(new LatLng(0, 0)).title("This is you!");
		userPosition = map.addMarker(userPosOptions);

		googleClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
		googleClient.connect();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		LocationServices.FusedLocationApi.removeLocationUpdates(googleClient, this);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menu_new_person) {
			NameDialog dialog = new NameDialog();
			dialog.show(getFragmentManager(), dialog.getClass().getName());
			return true;

		} else if (id == R.id.menu_send_position_to_server) {
			sendLocationToServer();
			return true;

		} else if (id == R.id.menu_get_positions_from_server) {
			refreshServerLocations();
			return true;

		} else {
			return super.onOptionsItemSelected(item);
		}
	}


	public void onPersonCreated(String name) {
		Log.d("WhereIsEveryone", "Person created: " + name);
	}


	private void sendLocationToServer() {

	}


	private void refreshServerLocations() {

	}


	@Override
	public void onConnected(Bundle connectionHint) {
		LocationRequest request = new LocationRequest();
		request.setInterval(10000);
		request.setFastestInterval(5000);
		request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

		LocationServices.FusedLocationApi.requestLocationUpdates(googleClient, request, this);
	}


	@Override
	public void onConnectionSuspended(int cause) {}


	@Override
	public void onConnectionFailed(ConnectionResult result) {}


	@Override
	public void onLocationChanged(Location location) {
		userPosition.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
	}
}