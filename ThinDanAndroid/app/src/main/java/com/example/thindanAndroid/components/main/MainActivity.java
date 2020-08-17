package com.example.thindanAndroid.components.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.thindanAndroid.R;
import com.example.thindanAndroid.components.profile.ProfileActivity;
import com.facebook.AccessToken;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static
    boolean fbUser;
    String userID;
    String userAvatar;
    String fullname;
    private static final int PROFILE_ACTIVITY_ID = 0;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boolean logged_in_at_startup = getIntent().getBooleanExtra("logged_in_at_startup", false);
        Log.e("Logged In at Startup?", String.valueOf(logged_in_at_startup));


        if (!logged_in_at_startup) {
            fbUser = getIntent().getBooleanExtra("fbUser", false);
            userID = getIntent().getStringExtra("userID");
            userAvatar = getIntent().getStringExtra("userAvatar");
            fullname = getIntent().getStringExtra("fullname");
        } else {
            fbUser = true;
            userAvatar = "https://graph.facebook.com/" + userID + "/picture?return_ssl_resources=1";
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Setting up Nav Drawer Header Programmatically
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivityForResult(i, PROFILE_ACTIVITY_ID);
            }
        });

        ImageView avatar = (ImageView) headerView.findViewById(R.id.imageView);
        final TextView name = (TextView) headerView.findViewById(R.id.navtitlename);
        final TextView subname = (TextView) headerView.findViewById(R.id.textView);

        if (logged_in_at_startup) {
            AccessToken accessToken = AccessToken.getCurrentAccessToken();

            fbUser = true;
            userID = accessToken.getUserId();
            userAvatar = "https://graph.facebook.com/" + userID + "/picture?return_ssl_resources=1";

            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            // Application code
                            try {
                                String fbname = object.getString("name"); // User's full name is acquired here.
                                name.setText(fbname);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link");
            request.setParameters(parameters);
            request.executeAsync();
        }

        Picasso.get().load(userAvatar).into(avatar);
        name.setText(fullname);
        subname.setText(userID);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_search,
                R.id.nav_categories,
                R.id.nav_chat,
                R.id.nav_request,
                R.id.nav_saved,
                R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
