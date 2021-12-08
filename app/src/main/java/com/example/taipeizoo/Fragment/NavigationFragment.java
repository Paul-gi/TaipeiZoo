package com.example.taipeizoo.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.taipeizoo.R;
import com.example.taipeizoo.ViewModel.CallViewModel;
import com.example.taipeizoo.databinding.FragmentNavigationBinding;
import com.example.taipeizoo.listdata.ListData;
import com.example.taipeizoo.zooListAdapter.ListDataAdapter;


public class NavigationFragment extends BaseFragment<FragmentNavigationBinding> {

    public boolean mPageState = true;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    public ListDataAdapter mListDataAdapter;
    private Boolean mFinish = false;
    public CallViewModel mCallViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBing = DataBindingUtil.inflate(inflater, R.layout.fragment_navigation, container, false);
        initView();
        return mDataBing.getRoot();
    }

    @Override
    protected void initView() {
        super.initView();
        mProgress = new ProgressDialog(getContext());
        Loading(mProgress);
        mLinearLayoutManager = new LinearLayoutManager(this.getActivity());
        mDataBing.mRecycleView.setLayoutManager(mLinearLayoutManager);
        mDataBing.mToolbarLayout.mToolbar.setTitle(mTitleStr);
        mDataBing.mToolbarLayout.mBackBtn.setOnClickListener(view -> fragmentBackPressed());

        mCallViewModel = new ViewModelProvider(this).get(CallViewModel.class);

        mListDataAdapter = new ListDataAdapter(pListData -> {
        }, getContext(), mTitleStr, mPageState);

        mDataBing.mRecycleView.setAdapter(mListDataAdapter);

        mDataBing.mToolbarLayout.mChange.setOnClickListener(v -> {
            if (!mPageState) {
                mGridLayoutManager = new GridLayoutManager(getActivity(), 1);
                mDataBing.mRecycleView.setLayoutManager(mLinearLayoutManager);
                mPageState = true;
            } else {
                mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
                mDataBing.mRecycleView.setLayoutManager(mGridLayoutManager);
                mPageState = false;
            }
            mListDataAdapter.setPageState(mPageState);
            mDataBing.mRecycleView.setAdapter(mListDataAdapter);
        });
        //＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝


        //================================RecycleView 到底刷新的部分＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
        mDataBing.mRecycleView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (!mDataBing.mRecycleView.canScrollVertically(1)) {
                if (!mFinish) {
                    Loading(mProgress);
                    callApiThread();
                } else {
                    Toast.makeText(getActivity(), "到底了", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCallViewModel.getDataFinishState().observe(getViewLifecycleOwner(), aBoolean -> mFinish = aBoolean);

        mCallViewModel.getDataListObserver().observe(getViewLifecycleOwner(), pCallData -> {
            if (pCallData != null) {
                mListDataAdapter.setData(pCallData);
                mProgress.dismiss();
            }
        });
        callApiThread();
    }

    private void callApiThread() {
        new Thread(() -> mCallViewModel.mCallApi(mTitleStr)).start();
    }
}
