package com.socialbike.phuketsocialbike;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import  com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import  com.google.android.gms.maps.CameraUpdateFactory;
import  com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import  com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import  com.google.android.gms.maps.model.Polyline;
import  com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap map;//ight be null if Google Play services APK is not available.
    static final  LatLng phu = new LatLng(7.966598, 98.359929);
    static  final LatLng h1e = new LatLng(7.8989985,98.3656401);
    static  final LatLng h2e = new LatLng(7.896834,98.384245);
    static  final LatLng h3e = new LatLng(7.9066047,98.3909879);
    static  final LatLng h4e = new LatLng(7.896479,98.301867);
    static  final LatLng h5e = new LatLng(7.896479,98.301867);
    static  final LatLng h6e = new LatLng(7.896479,98.301867);
    static  final LatLng h7e = new LatLng(8.023733,98.3389957);
    static  final LatLng h8e = new LatLng(7.876578,98.389437);
    static final LatLng cop1e = new LatLng(7.9665319,98.3599288);
    static final LatLng cop2e = new LatLng(7.9208378,98.3125841);
    static final LatLng cop3e = new LatLng(8.088982,98.341246);
    static final LatLng cop4e = new LatLng(7.9665319,98.3599288);
    static final LatLng cop5e = new LatLng(8.0705849,98.3713235);
    static final LatLng cop6e = new LatLng(7.9892,98.307259);
    static final LatLng cop7e = new LatLng(7.870558,98.394171);
    static final LatLng cop8e = new LatLng(8.19715,98.295684);





    private Polyline polyline;
    private List<LatLng> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
        if (status == ConnectionResult.SUCCESS){
            SupportMapFragment fm =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            map = fm.getMap();
            map.setMyLocationEnabled(true);
            list = new ArrayList<LatLng>();
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng arg0) {
                    MarkerOptions m = new MarkerOptions();
                    m.position(arg0);
                    m.draggable(true);
                    m.snippet("กดที่นี่เพื่อลบ");
                    m.title(arg0.toString());
                    map.addMarker(m);

                    list.add(arg0);
                   /* drawRoute(); */
                }
            });
            map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker arg0) {
                    arg0.remove();
                }
            });
            map.setOnMarkerDragListener(new OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker arg0) {
                    LatLng ll = arg0.getPosition();
                    arg0.setSnippet(ll.latitude+"," + ll.longitude);
                    arg0.showInfoWindow();

                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {

                }
            });


        }
        map =((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();
        if (map!=null){
            // Try to obtain the map from the SupportMapFragment.
            Marker hs1 = map.addMarker(new MarkerOptions().position(phu).title("ภูเก็ต").snippet("จังหวัด"));

                //hospiTAL!!!!!!!!!!
            Marker hos1 = map.addMarker(new MarkerOptions()
                    .position(h1e)
                    .title("ศิริโรจน์")
                    .snippet("โรงพยาบาล")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hosmarker)));
            Marker hos2 = map.addMarker(new MarkerOptions()
                    .position(h2e)
                    .title("โรงพยาบาลวชิระภูเก็ต์")
                    .snippet("โรงพยาบาล")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hosmarker)));
            Marker hos3 = map.addMarker(new MarkerOptions()
                    .position(h3e)
                    .title("โรงพยาบาลมิสชั่นภูเก็ต ์")
                    .snippet("โรงพยาบาล")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hosmarker)));
            Marker hos4 = map.addMarker(new MarkerOptions()
                    .position(h4e)
                    .title("  โรงพยาบาลป่าตอง  ")
                    .snippet("โรงพยาบาล")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hosmarker)));
            Marker hos5 = map.addMarker(new MarkerOptions()
                    .position(h5e)
                    .title("โรงพยาบาลกรุงเทพภูเก็ต ์")
                    .snippet("โรงพยาบาล")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hosmarker)));
            Marker hos6 = map.addMarker(new MarkerOptions()
                    .position(h6e)
                    .title("โรงพยาบาลดีบุก")
                    .snippet("โรงพยาบาล")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hosmarker)));


            Marker hos7 = map.addMarker(new MarkerOptions()
                    .position(h7e)
                    .title("โรงพยาบาลถลาง ")
                    .snippet("โรงพยาบาล")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hosmarker)));
            Marker hos8 = map.addMarker(new MarkerOptions()
                    .position(h8e)
                    .title("โรงพยาบาลแพทย์สมพจน์")
                    .snippet("โรงพยาบาล")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hosmarker)));



            //POlice!!!!!!!!
            Marker policee1 = map.addMarker(new MarkerOptions()
                    .position(cop1e)
                    .title("ป้อมตำรวจทางหลวงภูเก็ต์")
                    .snippet("ตำรวจ")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_markerpolice)));
            Marker policee2 = map.addMarker(new MarkerOptions()
                    .position(cop2e)
                    .title("สถานีตำรวจภูธรอำเภอกระทู้")
                    .snippet("ตำรวจ")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_markerpolice)));
            Marker policee3 = map.addMarker(new MarkerOptions()
                    .position(cop3e)
                    .title("สถานีตำรวจทางหลวงภูเก็ต")
                    .snippet("ตำรวจ")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_markerpolice)));
            Marker policee4 = map.addMarker(new MarkerOptions()
                    .position(cop4e)
                    .title("สถานีตำรวจชุมชน+ตำบลป่าคลอก")
                    .snippet("ตำรวจ")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_markerpolice)));
            Marker policee5 = map.addMarker(new MarkerOptions()
                    .position(cop5e)
                    .title("สถานีตำรวจภูธรอำเภอถลาง")
                    .snippet("ตำรวจ")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_markerpolice)));
            Marker policee6 = map.addMarker(new MarkerOptions()
                    .position(cop6e)
                    .title("สถานีตำรวจภูธรตำบลเชิงทะเล")
                    .snippet("ตำรวจ")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_markerpolice)));
            Marker policee7 = map.addMarker(new MarkerOptions()
                    .position(cop7e)
                    .title("กองกำกับการ5กองตำรวจน้ำ")
                    .snippet("ตำรวจ")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_markerpolice)));
            Marker policee8 = map.addMarker(new MarkerOptions()
                    .position(cop8e)
                    .title("สถานีตำรวจภูธรตำบลท่าฉัตรไชย")
                    .snippet("ตำรวจ")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_markerpolice)));

            CameraPosition cameraPosition = new CameraPosition.Builder().target(phu).zoom(12).build();

            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            // Check if we were successful in obtaining the map.
           /*f (mMap != null) {
                setUpMap();
            }
        }*/ }


        //BTN Call
        Button call_btn = (Button) findViewById(R.id.call_btn);
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(MapsActivity.this,CallActivity.class);
                startActivity(contractBtn);
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

    }


   /*rivate void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }  */


   /* public void  drawRoute(){
        PolylineOptions po;
        if (polyline == null){
            po = new PolylineOptions();

            for (int i = 0, tam = list.size(); i < tam; i++){

                po.add(list.get(i));
            }
            po.color(Color.RED);
            polyline = map.addPolyline(po);

        }

        else {
            polyline.setPoints(list);
        }

    }
*/ }

