package com.socialbike.phuketsocialbike;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import adapter.NavDrawerListAdapter;
import bean.NavDrawerItem;

public class Biker_BoardActivity extends ActionBarActivity {

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ImageView img;
    private ImageView img2;
    private int PICK_IMAGE =1;
    private int TAKE_PICTURE =1;
    private File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biker_board);

        pickImage();
        takeImage();
        emergency_BTN();
        trip_Introduce_BTN();
        slide_manu();
        post_BTN();

    }

    private void post_BTN() {
        Button post_btn = (Button) findViewById(R.id.post_btn);
        final EditText eventNews_edittext   = (EditText)findViewById(R.id.board_editText);
        final TextView content_show = (TextView) findViewById(R.id.content_show);
        final ImageView set = (ImageView) findViewById(R.id.showeventImage);
        final ImageView get = (ImageView) findViewById(R.id.imgBoard_imgView);

        post_btn.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String content = eventNews_edittext.getText().toString();
                content_show.setText(content);
                Bitmap bitmap = ((BitmapDrawable)get.getDrawable()).getBitmap();
                set.setImageBitmap(bitmap);
            }
        });
    }

    private void slide_manu() {
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Profile
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Hospital
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Officer
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // US
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Setting
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // Logout
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));

        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
    }

    //BTN Trip Introduce
    private void trip_Introduce_BTN() {
        Button trip_introduce = (Button) findViewById(R.id.trip_introduce_btn);
        trip_introduce.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(Biker_BoardActivity.this,Trip_IntroduceActivity.class);
                startActivity(contractBtn);
            }
        });
    }
    //BTN Emergency
    private void emergency_BTN() {
        Button emergency = (Button) findViewById(R.id.emergency_btn);
        emergency.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(Biker_BoardActivity.this,MapsActivity.class);
                startActivity(contractBtn);
            }
        });
    }

    //****Take picture
    private void takeImage() {
        Button picture = (Button)findViewById(R.id.picture_btn);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageFile = new File(Environment.getExternalStorageDirectory(), "my_image.jpg");
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                startActivityForResult(Intent.createChooser(takePicture,
                        "Take Picture"), TAKE_PICTURE);
            }
        });
/*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageFile = new File(Environment.getExternalStorageDirectory(), "my_image.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                startActivityForResult(intent, TAKE_PICTURE_SAVE);
            }
        });*/
    }

    //****IMAGE Gallery
    private void pickImage() {
        Button pickImg = (Button)findViewById(R.id.pickImage_btn);
        pickImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickPhoto.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(pickPhoto,
                        "Select Picture"), PICK_IMAGE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

            // return from file upload
            ImageView imageView = (ImageView) findViewById(R.id.imgBoard_imgView);

            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            }else  if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK && null != data)
            {
                if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
                    Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(capturedImage);
                }

            }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_biker_board, menu);
        return false;
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

    /**Slide manu **/
    private class SlideMenuClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // display view for selected nav drawer item
            displayView(position);
        }

        private void displayView(int position) {
            // update the main content by replacing fragments
            Fragment fragment = null;


        }
    }
}