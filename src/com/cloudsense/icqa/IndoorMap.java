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
			//.bearing(-2.70f)
			.image(BitmapDescriptorFactory.fromResource(R.drawable.tuas))
			.positionFromBounds(setupBounds())
			.transparency(0.f));
		

	}
	
	public LatLngBounds setupBounds(){
		/*
		 * Ground control points for the ground overlay bitmap
		 * 
		 * GCP Projection =
		 *	GCP[  0]: Id=1, Info=
		 *	          (0,0) -> (24.8172971606177,60.1876397959336,0)
		 *	GCP[  1]: Id=2, Info=
		 *	          (2000,0) -> (24.8207008838576,60.187691802007,0)
		 *	GCP[  2]: Id=3, Info=
		 *	          (2000,1140) -> (24.8207411169928,60.1867276760235,0)
		 */
		
		//LatLng  northEast = new LatLng(60.187691802007, 24.8207008838576);
		//LatLng  southWest = new LatLng(60.1866756699501, 24.8172971606177);
		
		
		double deltaLat = 0.000015; // correction value
		
		LatLng  northEast = new LatLng(60.1876397959336 + (2.4 * deltaLat), 24.8207008838576);
		LatLng  southWest = new LatLng(60.1866756699501 + deltaLat, 24.8172971606177);
		
		
		// 60.1866756699501
		return new LatLngBounds(southWest, northEast);
	}

}
