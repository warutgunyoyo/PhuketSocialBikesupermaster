package com.socialbike.phuketsocialbike;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.facebook.widget.LoginButton;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;

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
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends Activity  {
    ProgressDialog progress;
    AQuery aq;
    String s_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        aq = new AQuery(this);


        progress = new ProgressDialog(this);
        progress.setMessage("Wait ... ");
        progress.setCanceledOnTouchOutside(false);
        if (android.os.Build.VERSION.SDK_INT > 12) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        // txtUsername & txtPassword
        final EditText txtUser = (EditText)findViewById(R.id.edlogin);
        final EditText txtPass = (EditText)findViewById(R.id.edloginpassword);
        // Add code to print out the key hash
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.facebook.samples.hellofacebook",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


        //BTN Login to BikerBoard
        Button login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://www.phuketsocailbike.esy.es/check_login.php";
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("strUser",txtUser.getText().toString()));
                params.add(new BasicNameValuePair("strPass",txtPass.getText().toString()));

                /** Get result from Server (Return the JSON Code)
                 * StatusID = ? [0=Failed,1=Complete]
                 * MemberID = ? [Eg : 1]
                 * Error	= ?	[On case error return custom error message]
                 *
                 * Eg Login Failed = {"StatusID":"0","MemberID":"0","Error":"Incorrect Username and Password"}
                 * Eg Login Complete = {"StatusID":"1","MemberID":"2","Error":""}
                 */

                String resultServer  = getHttpPost(url,params);

                /*** Default Value ***/
                String strStatusID = "0";
                String strMemberID = "0";
                String strError = "Unknow Status!";

                JSONObject c;
                try {
                    c = new JSONObject(resultServer);
                    strStatusID = c.getString("StatusID");
                    strMemberID = c.getString("i");
                    strError = c.getString("Error");

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



                // Prepare Login
                if(strStatusID.equals("0"))
                {
                    // Dialog
                    ad.setTitle("Error! fuck");

                    ad.setPositiveButton("Close", null);
                    ad.setMessage(strError);
                    ad.show();
                    txtUser.setText("");
                    txtPass.setText("");
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(LoginActivity.this,Biker_BoardActivity.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

            }
        });



        //BTN Login to BikerBoard
        Button emergency_btn = (Button) findViewById(R.id.emergency_btn);
        emergency_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(LoginActivity.this,MapsActivity.class);
                startActivity(contractBtn);
            }
        });
        //TEXT Regis
        TextView text = (TextView) findViewById(R.id.text5);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(contractBtn);
            }
        });





    }


    public String getHttpPost(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

}




