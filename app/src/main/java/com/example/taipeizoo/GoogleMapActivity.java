package com.example.taipeizoo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.taipeizoo.databinding.FragmentGoogleMapBinding;
import com.example.taipeizoo.listdata.LocationPositionData;
import com.example.taipeizoo.zooListAdapter.GoogleMapItemAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMapItemAdapter.MapViewRecycleViewClickListener {
    FragmentGoogleMapBinding mFragmentGoogleMapBinding;
    GoogleMap mGoogleMap;
    LatLng mLatLng;
    ArrayList<LocationPositionData> mLocationPositionListData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentGoogleMapBinding = DataBindingUtil.setContentView(this, R.layout.fragment_google_map);
        getBundle();
        init();
    }


    private void init() {
        mFragmentGoogleMapBinding.mGoogleMapRecycleView.setLayoutManager(new LinearLayoutManager(GoogleMapActivity.this, LinearLayoutManager.HORIZONTAL, false));
        GoogleMapItemAdapter mGoogleMapItemAdapter = new GoogleMapItemAdapter(mLocationPositionListData, this);
        mFragmentGoogleMapBinding.mGoogleMapRecycleView.setAdapter(mGoogleMapItemAdapter);

        SupportMapFragment iSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mGoogleMap);
        assert iSupportMapFragment != null;
        iSupportMapFragment.getMapAsync(this);


    }

    private void getBundle() {
        Bundle iBundle = getIntent().getExtras();
        mLocationPositionListData = (ArrayList<LocationPositionData>) iBundle.getSerializable("mLocationPositionListData");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mLatLng = new LatLng(Double.parseDouble(mLocationPositionListData.get(0).getKeyY_position())
                , Double.parseDouble(mLocationPositionListData.get(0).getKeyX_position()));
        mGoogleMap.addMarker(new MarkerOptions().position(mLatLng));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng,17));



//        //放大地圖到15倍
//        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(17));

        //設定 右下角的放大縮小功能
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        //設定 左上角的指南針，要兩指旋轉才會出現
        mGoogleMap.getUiSettings().setCompassEnabled(true);
        //設定 右下角的導覽及開啟 Google Map功能
        mGoogleMap.getUiSettings().setMapToolbarEnabled(true);

    }

    @Override
    public void onMapViewClicked(int position) {

        mGoogleMap.clear();
        mLatLng = new LatLng(Double.parseDouble(mLocationPositionListData.get(position).getKeyY_position())
                , Double.parseDouble(mLocationPositionListData.get(position).getKeyX_position()));
        mGoogleMap.addMarker(new MarkerOptions().position(mLatLng));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLng));

    }
}
