package com.example.taipeizoo.zooListAdapter;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taipeizoo.R;
import com.example.taipeizoo.Room.AppDataBase;
import com.example.taipeizoo.Room.User;
import com.example.taipeizoo.Util.UtilTools;
import com.example.taipeizoo.listdata.ListData;

import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class ListDataAdapter extends RecyclerView.Adapter<ListDataAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<ListData> mZooDataList = new ArrayList<>();
    private final String mTitleName;
    private final ListDataItf mListDataItf;
    private final ArrayList<Integer> mAlreadyRead = new ArrayList<>();
    private final String mSynchronizedUsed = "aa";
    private Boolean mPageState;
    private final Handler mHandler = new Handler();
    private final Runnable mRunnable = this::changeUI;
    private UtilTools mUtilTools = new UtilTools();

    public ListDataAdapter(ListDataItf pListDataItf, Context context, String pTitleName, Boolean pPageState) {
        this.context = context;
        mListDataItf = pListDataItf;
        mTitleName = pTitleName;
        mPageState = pPageState;
    }

    public void setPageState(boolean pPageState) {
        mPageState = pPageState;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<ListData> pAnimalDataList) {
        mZooDataList.addAll(pAnimalDataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (mPageState) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_horizontal_item, parent, false);
        }
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ListDataAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ListData iListData = mZooDataList.get(position);

        mUtilTools.controlPicture(context, iListData.keyUrl01(), holder.mPic01_URL);

        getRoom(position, iListData.getEnglishName(), iListData.getChineseName());
        setData(iListData.getChineseName(), holder.mName_Ch);

        setData(iListData.getEnglishName(), holder.mName_En);

        if (mAlreadyRead.contains(position)) {
            holder.itemView.setBackgroundResource(R.color.gold);
        } else {
            holder.itemView.setBackgroundResource(0);
        }


        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(mClick);
    }

    private final View.OnClickListener mClick = new View.OnClickListener() {
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v) {
            if (v.getTag() != null) {
                int iIndex;
                String iD = v.getTag().toString();
                if (Character.isDigit(iD.charAt(0))) {
                    iIndex = Integer.parseInt(iD);
                    ListDataAdapter.this.throwData(context, iIndex);
                    mListDataItf.getData(mZooDataList.get(iIndex));
                    setRoom(iIndex);
                    v.setBackgroundResource(R.color.gold);
                }
            }
        }
    };

    private void setData(String pShowContext, TextView pTextView) {
        if (pShowContext == null) {
            pTextView.setVisibility(View.GONE);
        } else {
            pTextView.setText(pShowContext);
        }
    }

    @Override
    public int getItemCount() {
        return this.mZooDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mName_Ch, mName_En;
        ImageView mPic01_URL;

        public MyViewHolder(View itemView) {
            super(itemView);
            mPic01_URL = itemView.findViewById(R.id.mPic01_URL);
            mName_Ch = itemView.findViewById(R.id.mName_Ch);
            mName_En = itemView.findViewById(R.id.mName_En);
        }
    }

    private void throwData(Context pContent, int pPosition) {
        ListData iData = mZooDataList.get(pPosition);
        Intent iIntent = new Intent();
        Bundle iBundle = new Bundle();
        switch (mTitleName) {
//            case "Department":
//                iIntent.setClass(pContent, PavilionDetailActivity.class);
//                break;
//            case "動物區":
//                iIntent.setClass(pContent, AnimalDetailActivity.class);
//                break;
//            case "植物區":
//                iIntent.setClass(pContent, PlantDetailActivity.class);
//                break;
        }
        iBundle.putString(mTitleName, mTitleName);
//        iBundle.putString(Parameter.KeyRawJson, iData.getRawData());
        iIntent.putExtras(iBundle);
        context.startActivity(iIntent);
    }

    public interface ListDataItf {
        void getData(ListData pListData);
    }


    private void getRoom(int pPosition, String pEnglishName, String pChineseName) {
        new Thread(() -> {
            synchronized (mSynchronizedUsed) {
                int iFindData = mAlreadyRead.indexOf(pPosition);
                if (iFindData == -1) {
                    User ix = AppDataBase.getInstance(context).userDao().findByName(mTitleName, pPosition, pEnglishName, pChineseName);
                    if (ix != null) {
                        mAlreadyRead.add(pPosition);
                        mHandler.removeCallbacks(mRunnable);
                        mHandler.postDelayed(mRunnable, 500);
                    }
                }
            }
        }).start();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void changeUI() {
        notifyDataSetChanged();
    }

    private void setRoom(int pPosition) {
        new Thread(() -> {
//                AppDataBase db = Room.databaseBuilder(context, AppDataBase.class, "clickStore").build();
            ListData iListData = mZooDataList.get(pPosition);
            //查詢
            AppDataBase mApDataBase = AppDataBase.getInstance(context);
//            mApDataBase.userDao().getUserList();

            //插入
            mApDataBase.userDao().insertUser(new User(mTitleName, pPosition, iListData.getEnglishName(), iListData.getChineseName()));
        }).start();
    }

}
