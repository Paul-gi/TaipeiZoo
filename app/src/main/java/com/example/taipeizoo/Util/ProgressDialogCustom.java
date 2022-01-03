package com.example.taipeizoo.Util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.taipeizoo.R;

import java.util.Objects;

public class ProgressDialogCustom extends DialogFragment {

    Dialog mDialog;
    Context mContext;

    public ProgressDialogCustom(Context pContext) {
        mContext = pContext;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progressbar, container, false);
        initView(mContext);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


    private void initView(Context pContext) {
        mDialog = new Dialog(pContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        WindowManager.LayoutParams lp = Objects.requireNonNull(getDialog()).getWindow().getAttributes();
//        Window window = mDialog.getWindow();
//        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        window.setLayout(lp.width,200);

    }

}

