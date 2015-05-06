package com.socialbike.phuketsocialbike;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class CallActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Button c1 = (Button) findViewById(R.id.call1);
        c1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                makeCall1();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------------------
        Button c2 = (Button) findViewById(R.id.call2);
        c2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                makeCall2();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------------------
        Button c3 = (Button) findViewById(R.id.call3);
        c3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                makeCall3();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------------------
        Button c4 = (Button) findViewById(R.id.call4);
        c4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                makeCall4();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------------------
        Button c5 = (Button) findViewById(R.id.call5);
        c5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                makeCall5();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------------------
        Button c6 = (Button) findViewById(R.id.call6);
        c6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                makeCall6();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------------------
        Button c7 = (Button) findViewById(R.id.call7);
        c7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                makeCall7();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------------------
        Button c8 = (Button) findViewById(R.id.call8);
        c8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                makeCall8();
            }
        });
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    protected void makeCall1() {
        Log.i("Make call", "");

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:191*"));

        try {
            startActivity(phoneIntent);
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CallActivity.this,
                    "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    protected void makeCall2() {
        Log.i("Make call", "");

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:199*"));

        try {
            startActivity(phoneIntent);
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CallActivity.this,
                    "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    protected void makeCall3() {
        Log.i("Make call", "");

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:1691*"));

        try {
            startActivity(phoneIntent);
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CallActivity.this,
                    "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    protected void makeCall4() {
        Log.i("Make call", "");

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:1669*"));

        try {
            startActivity(phoneIntent);
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CallActivity.this,
                    "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    protected void makeCall5() {
        Log.i("Make call", "");

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:1554*"));

        try {
            startActivity(phoneIntent);
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CallActivity.this,
                    "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    protected void makeCall6() {
        Log.i("Make call", "");

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:0-2226-4444-8"));

        try {
            startActivity(phoneIntent);
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CallActivity.this,
                    "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    protected void makeCall7() {
        Log.i("Make call", "");

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:0-2751-0951-3"));

        try {
            startActivity(phoneIntent);
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CallActivity.this,
                    "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    protected void makeCall8() {
        Log.i("Make call", "");

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("1193*"));

        try {
            startActivity(phoneIntent);
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CallActivity.this,
                    "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
//----------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_call, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}