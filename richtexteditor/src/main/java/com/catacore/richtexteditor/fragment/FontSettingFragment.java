package com.catacore.richtexteditor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.catacore.richtexteditor.R;
import com.catacore.richtexteditor.adapter.FontSettingAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.Arrays;
import java.util.List;

public class FontSettingFragment extends Fragment {
    public static final String TYPE = "type";

    public static final int TYPE_SIZE = 0;
    public static final int TYPE_LINE_HGEIGHT = 1;
    public static final int TYPE_FONT_FAMILY = 2;

    private List<String> fontFamilyList =
            Arrays.asList("Arial", "Arial Black", "Comic Sans MS", "Courier New", "Helvetica Neue",
                    "Helvetica", "Impact", "Lucida Grande", "Tahoma", "Times New Roman", "Verdana");

    private List<String> fontSizeList =
            Arrays.asList("12", "14", "16", "18", "20", "22", "24", "26", "28", "36");

    private List<String> fontLineHeightList =
            Arrays.asList("1.0", "1.2", "1.4", "1.6", "1.8", "2.0", "3.0");

    RecyclerView rvContainer;
    private FontSettingAdapter mAdapter;
    private OnResultListener mOnResultListener;
    private List<String> dataSourceList = fontSizeList;
    private int type = 0;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_font_setting, null);
        rvContainer = rootView.findViewById(R.id.rv_container);

        type = getArguments().getInt(TYPE);
        if (type == TYPE_SIZE) {
            dataSourceList = fontSizeList;
        } else if (type == TYPE_LINE_HGEIGHT) {
            dataSourceList = fontLineHeightList;
        } else if (type == TYPE_FONT_FAMILY) {
            dataSourceList = fontFamilyList;
        }

        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        rvContainer.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new FontSettingAdapter(dataSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mOnResultListener != null) {
                    mOnResultListener.onResult(dataSourceList.get(position));
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                            .remove(FontSettingFragment.this)
                            .show(fm.findFragmentByTag(EditorMenuFragment.class.getName()))
                            .commit();
                }
            }
        });
        rvContainer.setAdapter(mAdapter);
    }

    interface OnResultListener {

        void onResult(String result);
    }

    public void setOnResultListener(OnResultListener mOnResultListener) {
        this.mOnResultListener = mOnResultListener;
    }
}

