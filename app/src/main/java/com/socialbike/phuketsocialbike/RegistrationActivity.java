package com.socialbike.phuketsocialbike;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;


public class RegistrationActivity extends ActionBarActivity {

    ProgressDialog progress;
    AQuery aq;

    String s_txtusername, s_txtuserpaaword, s_txtfirstname, s_txtlastname, s_txtgender, s_txtage, s_link;
    EditText txtusername, txtuserpaaword, txtfirstname, txtlastname, txtgender, txtage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);
        if (android.os.Build.VERSION.SDK_INT > 12) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }


        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        txtusername = (EditText) findViewById(R.id.edusername);
        txtuserpaaword = (EditText) findViewById(R.id.edpassword);
        txtfirstname = (EditText) findViewById(R.id.edfirstname);
        txtlastname = (EditText) findViewById(R.id.edlastname);
        txtgender = (EditText) findViewById(R.id.edgender);
        txtage = (EditText) findViewById(R.id.edage);


        final Button btnSave = (Button) findViewById(R.id.buttonsubmit);


        aq = new AQuery(this);


        progress = new ProgressDialog(this);
        progress.setMessage("Wait ... ");
        progress.setCanceledOnTouchOutside(false);




      /* s_txtusername = txtusername.getText().toString();
        s_txtuserpaaword = txtuserpaaword.getText().toString();
        s_txtfirstname = txtfirstname.getText().toString();
        s_txtlastname = txtlastname.getText().toString();
        s_txtgender = txtgender.getText().toString();
        s_txtage = txtage.getText().toString();*/




        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                s_link = "http://www.phuketsocailbike.esy.es/regist.php?username=" +txtusername.getText().toString() + "&&userpaaword=" + txtuserpaaword.getText().toString() + "&&firstname=" + txtfirstname.getText().toString() + "&&lastname=" + txtlastname.getText().toString() + "&&gender=" + txtgender.getText().toString() + "&&age=" + txtage.getText().toString();

                aq.progress(progress).ajax(s_link, String.class, new AjaxCallback<String>() {

                });


                //  Toast.makeText(getApplication(), s_link, Toast.LENGTH_LONG).show();

            }
        });



    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

}