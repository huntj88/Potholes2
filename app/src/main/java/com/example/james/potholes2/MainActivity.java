package com.example.james.potholes2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.james.potholes2.adapters.PotholeAdapter;
import com.example.james.potholes2.retrofit.model.pothole.Pothole;
import com.example.james.potholes2.retrofit.remote.ApiUtils;
import com.example.james.potholes2.util.AuthUtils;
import com.example.james.potholes2.util.BaseActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements PotholePresenter.PotholeView, AuthUtils.AuthListener,OnMapReadyCallback{


    PotholePresenter presenter;
    private GoogleMap mMap;
    @BindView(R.id.pothole_recycler) RecyclerView potholeRecyclerView;
    @BindView(R.id.button2) Button button;
    PotholeAdapter potholeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getAndSaveAllPotholesFromServer();
            }
        });

        setUpMapIfNeeded();

        AuthUtils.getToken(ApiUtils.getNoAuthAPIService(), this);

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        potholeRecyclerView.setAdapter(null);
        if(presenter!=null)
        {
            presenter.closeRealm();
        }
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }

    private void setUpRecyclerView()
    {

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        potholeRecyclerView.setLayoutManager(llm);

        potholeAdapter = new PotholeAdapter(presenter.getRealm().where(Pothole.class).findAll(),true);
        potholeRecyclerView.setAdapter(potholeAdapter);
    }

    @Override
    public void gotAuthModel(AuthModel authModel) {
        Log.d(TAG,authModel.getAccessToken());
        presenter = new PotholePresenter(this, authModel);
        setUpRecyclerView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        mMap.setMinZoomPreference(12.5f);
    }
}
