package com.itwill.location;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


public class MainActivity extends Activity {
	LocationManager locationManager;
	LocationListener locationListener;
	TextView locationTV;
	
	//Map
	GoogleMap googleMap;
	
	PolylineOptions lineOptions=new PolylineOptions();
	CircleOptions circleOptions=new CircleOptions();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationTV=(TextView)findViewById(R.id.locationTV);
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment=
        		(MapFragment)fragmentManager.findFragmentById(R.id.mapFragment);
        googleMap=mapFragment.getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        /*
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL|GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        */
        UiSettings uiSetting=googleMap.getUiSettings();
        uiSetting.setCompassEnabled(true);
        uiSetting.setZoomControlsEnabled(true);
        //uiSetting.setMyLocationButtonEnabled(true);
        
        
    }
    @Override
    protected void onResume() {
    	locationManager=
    			(LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	Location currentlocation=locationManager.getLastKnownLocation(
    			LocationManager.GPS_PROVIDER);
    	
    	if(currentlocation==null){
    		currentlocation=locationManager.getLastKnownLocation(
    			LocationManager.NETWORK_PROVIDER);
    	}
    	locationListener=new MyLocationListener();
    	
    	if(currentlocation!=null){
    		locationListener.onLocationChanged(currentlocation);
    	}
    	
    	locationManager.requestLocationUpdates(
    			"gps",
    			0,
    			0,
    			locationListener);
    	locationManager.requestLocationUpdates(
    			"network",
    			0,
    			0,
    			locationListener);
    	
    	super.onResume();
    }
    Location oldLocation;
    private void displayLocationInfo(Location currentLocation){
    	StringBuffer sb=new StringBuffer();
    	double lat=currentLocation.getLatitude();
    	double lng=currentLocation.getLongitude();
    	//sb.append("위도:"+lat+"\n");
    	//sb.append("경도:"+lng+"\n");
    	if(oldLocation!=null){
    		float distance=oldLocation.distanceTo(currentLocation);
    		//sb.append("이동거리:"+distance+"\n");
    		float direction=oldLocation.bearingTo(currentLocation);
    		//sb.append("이동방향:"+direction+"\n");
    	}
    	oldLocation=currentLocation;
    	//reverse geocoding(location-->address)
    	Geocoder geocoder=new Geocoder(getApplicationContext(),
    			Locale.KOREA);
    	try{
    		List<Address> addressList=geocoder.getFromLocation(lat, lng, 1);
    		if(addressList.size()!=0){
    			Address address=addressList.get(0);
    			int addressLineIndex=
    					address.getMaxAddressLineIndex();
    			String addressStr=address.getAddressLine(0);
    			sb.append(addressStr);
    			
    		}else{
    			//sb.append("주소:없다~~~\n");
    		}
    	}catch(Exception e){
    		//sb.append("주소:애로다~~~\n");
    	}
    	
    	locationTV.setText(sb.toString());
    }
    /**************LocationListener impl***************/
    public class MyLocationListener implements LocationListener{
		public void onLocationChanged(Location location) {
			//위치정보보여주기
			displayLocationInfo(location);
			//지도이동
			LatLng latLng=new LatLng(location.getLatitude(), location.getLongitude());
			CameraUpdate cameraUpdate=
					CameraUpdateFactory.newLatLngZoom(latLng, 17);
			googleMap.animateCamera(cameraUpdate);
			
			
			//Marker
			googleMap.clear();//Removes all markers, polylines, polygons, overlays, etc from the map. 
			MarkerOptions markerOptions=new MarkerOptions();
			markerOptions.title("현재위치");
			markerOptions.snippet("여삼빌딩");
			//markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
		    markerOptions.icon(BitmapDescriptorFactory.
		    					fromResource(R.drawable.ic_launcher));
			markerOptions.position(latLng);
			
			googleMap.addMarker(markerOptions);
			
			//그리기
			circleOptions.radius(100);
			circleOptions.center(latLng);
			circleOptions.fillColor(Color.argb(50, 0, 0, 255));
			circleOptions.strokeColor(Color.TRANSPARENT);
			
			Circle circle=googleMap.addCircle(circleOptions);
			
			
			//event
			googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
				@Override
				public void onMapClick(LatLng point) {
					googleMap.animateCamera(CameraUpdateFactory.newLatLng(point));
				}
			});
			googleMap.setOnMapLongClickListener(null);
			
			googleMap.setOnMarkerDragListener(null);
			googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
				@Override
				public boolean onMarkerClick(Marker marker) {
					
					
					if(marker.isInfoWindowShown()){
						marker.hideInfoWindow();
						Log.e("mc info show", marker.toString());
					}else{
						marker.showInfoWindow();
						
						Log.e("mc info hide", marker.toString());
					}
					return false;
				}
			});
			
		}
		public void onStatusChanged(String provider, int status, Bundle extras) {}
		public void onProviderEnabled(String provider) {}
		public void onProviderDisabled(String provider) {}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
