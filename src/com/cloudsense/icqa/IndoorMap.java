package com.cloudsense.icqa;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;

public class IndoorMap extends android.support.v4.app.FragmentActivity {

	private GoogleMap mMap;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.indoor_map);
		setUpMapIfNeeded();
	}

	public void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	public void setUpMapIfNeeded() {
		if (mMap == null)
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
		if (mMap != null)
			setUpMap();
	}

	public void setUpMap() {
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		LatLng point = new LatLng(60.18716, 24.81899);
		MarkerOptions marker = new MarkerOptions().position(point)
				.title("TUAS").snippet("Here is where we work");
		mMap.addMarker(marker);
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 17.0f));

	}

}
