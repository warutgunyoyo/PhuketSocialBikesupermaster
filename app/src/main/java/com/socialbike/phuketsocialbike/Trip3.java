package com.socialbike.phuketsocialbike;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import app.akexorcist.gdaplibrary.GoogleDirection;
import app.akexorcist.gdaplibrary.GoogleDirection.OnDirectionResponseListener;

import java.util.ArrayList;
import java.util.List;

public class Trip3 extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    static final LatLng phuc = new LatLng(7.966598, 98.359929);
    LatLng start = new LatLng(7.979215231780963, 98.331759609282);
    LatLng end = new LatLng(7.9815865842173865, 98.36380094289);
    private List<LatLng> list;
    GoogleDirection gd;
    TextView textProgress;
    Document mDoc;
    Button buttonAnimate, buttonRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip3);

        emergency_BTN();
        mMap = ((SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(start, 15));

        gd = new GoogleDirection(this);
        gd.setOnDirectionResponseListener(new OnDirectionResponseListener() {
            public void onResponse(String status, Document doc, GoogleDirection gd) {
                mDoc = doc;
                mMap.addPolyline(gd.getPolyline(doc, 3, Color.RED));
                mMap.addMarker(new MarkerOptions().position(start)
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN)));

                mMap.addMarker(new MarkerOptions().position(end)
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN)));

                buttonAnimate.setVisibility(View.VISIBLE);
            }
        });

        gd.setOnAnimateListener(new GoogleDirection.OnAnimateListener() {
            public void onStart() {
                textProgress.setVisibility(View.VISIBLE);
            }

            public void onProgress(int progress, int total) {
                textProgress.setText(progress + " / " + total);
            }

            public void onFinish() {
                buttonAnimate.setVisibility(View.VISIBLE);
                textProgress.setVisibility(View.GONE);
            }
        });

        textProgress = (TextView)findViewById(R.id.textProgress);
        textProgress.setVisibility(View.GONE);

        buttonRequest = (Button)findViewById(R.id.buttonRequest);
        buttonRequest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                gd.setLogging(true);
                gd.request(start, end, GoogleDirection.MODE_DRIVING);
            }
        });

        buttonAnimate = (Button)findViewById(R.id.buttonAnimate);
        buttonAnimate.setVisibility(View.GONE);
        buttonAnimate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                gd.animateDirection(mMap, gd.getDirection(mDoc), GoogleDirection.SPEED_FAST
                        , true, false, true, false, null, false, true, null);
            }
        });


    }
    //BTN Emergency
    private void emergency_BTN() {
        Button emergency = (Button) findViewById(R.id.emergency_btn);
        emergency.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(Trip3.this,MapsActivity.class);
                startActivity(contractBtn);
            }
        });
    }

    public void onPause() {
        super.onPause();
        gd.cancelAnimated();
    }
}


