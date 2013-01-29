package com.cloudsense.icqa;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class IndoorMap extends android.support.v4.app.FragmentActivity {

	private GoogleMap mMap;
	//private GroundOverlay mGroundOverlay;
	//private BitmapDescriptor image;
	

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
		
		mMap.addGroundOverlay(new GroundOverlayOptions()
			//.bearing(12.70f)
			.image(BitmapDescriptorFactory.fromResource(R.drawable.tuas_warped))
			.positionFromBounds(setupBounds())
			.transparency(0.5f));
		

	}
	
	public LatLngBounds setupBounds(){
		LatLng  northEast = new LatLng(60.18771980524313, 24.820497035972608);
		LatLng  southWest = new LatLng(60.18677168239048, 24.817380309097302);
		return new LatLngBounds(southWest, northEast);
	}

}
