package com.example.mmamin.blanklogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InformationActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    RelativeLayout mRelativeLayout;
    GoogleApiClient mGoogleApiClient;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> mCollection;
    ExpandableListView mListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        mListview = (ExpandableListView) findViewById(R.id.listview);

        groupList = new ArrayList<String>();
        groupList.add("Gold");
        groupList.add("Silver");
        groupList.add("USDebit");
        groupList.add("UKDebit");
        groupList.add("Carriers");

        childList = new ArrayList<String>();
        childList.add("Cuba");
        childList.add("USA");
        childList.add("France");

        mCollection = new LinkedHashMap<String, List<String>>();
        for (String carrier : groupList) {
            mCollection.put(carrier, childList);
        }

        mListview.setAdapter(new CustomExpandableListAdapter(this, groupList, mCollection));

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(InformationActivity.this, InformationActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_red:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                mRelativeLayout.setBackgroundColor(android.graphics.Color.RED);
                return true;
            case R.id.menu_green:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                mRelativeLayout.setBackgroundColor(android.graphics.Color.GREEN);
                return true;
            case R.id.logout:
                signOut();
                Intent returnIntent = new Intent(InformationActivity.this, MainActivity.class);
                startActivity(returnIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Log.d("Status", "onResult: " + status);
                    }
                });
    }
}
