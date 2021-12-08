package com.example.taipeizoo.zooListAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taipeizoo.R;
import com.example.taipeizoo.listdata.LocationPositionData;

import java.util.ArrayList;

public class GoogleMapGeoAdapter extends RecyclerView.Adapter<com.example.taipeizoo.zooListAdapter.GoogleMapGeoAdapter.MyViewHolder> {
    private final ArrayList<LocationPositionData> mGeoDataList = new ArrayList<>();


    public GoogleMapGeoAdapter(ArrayList<LocationPositionData> pGeoList) {
        setData(pGeoList);
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<LocationPositionData> pLocationDataList) {
        mGeoDataList.clear();
        mGeoDataList.addAll(pLocationDataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.example.taipeizoo.zooListAdapter.GoogleMapGeoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.googlemap_geo_item, parent, false);
        return new com.example.taipeizoo.zooListAdapter.GoogleMapGeoAdapter.MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull com.example.taipeizoo.zooListAdapter.GoogleMapGeoAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mA_Location.setText(mGeoDataList.get(position).getKeyLocationLogo());
    }

    @Override
    public int getItemCount() {
        return this.mGeoDataList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mA_Location;

        public MyViewHolder(View itemView) {
            super(itemView);
            mA_Location = itemView.findViewById(R.id.mA_Location);
        }
    }
}

