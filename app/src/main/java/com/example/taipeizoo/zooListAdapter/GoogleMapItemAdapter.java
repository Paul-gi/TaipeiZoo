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

public class GoogleMapItemAdapter extends RecyclerView.Adapter<GoogleMapItemAdapter.MyViewHolder> {
    private final ArrayList<LocationPositionData> mLocationDataList = new ArrayList<>();
    private final MapViewRecycleViewClickListener mMapViewRecycleViewClickListener;

    @SuppressLint("NotifyDataSetChanged")
    public GoogleMapItemAdapter(ArrayList<LocationPositionData> pLocationList, MapViewRecycleViewClickListener pMapViewRecycleViewClickListener) {
        mMapViewRecycleViewClickListener = pMapViewRecycleViewClickListener;
        mLocationDataList.clear();
        mLocationDataList.addAll(pLocationList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.googlemap_recycleview_utem, parent, false);
        return new MyViewHolder(view, mMapViewRecycleViewClickListener);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (mLocationDataList.size() == 1) {
            holder.mItem.setVisibility(View.GONE);
        }
        holder.mItem.setText("地點" + (position + 1));
    }

    @Override
    public int getItemCount() {
        return this.mLocationDataList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mItem;
        MapViewRecycleViewClickListener MapViewRecycleViewClickListener;

        public MyViewHolder(View itemView, MapViewRecycleViewClickListener pMapViewRecycleViewClickListener) {
            super(itemView);
            mItem = itemView.findViewById(R.id.mLocationItem);
            MapViewRecycleViewClickListener = pMapViewRecycleViewClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            MapViewRecycleViewClickListener.onMapViewClicked(getAdapterPosition());
        }
    }

    public interface MapViewRecycleViewClickListener {
        void onMapViewClicked(int position);
    }
}
