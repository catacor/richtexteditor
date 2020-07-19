package com.catacore.richtexteditor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.catacore.richtexteditor.R;

public class EditTableFragment extends Fragment {

    EditText etRows;
    EditText etCols;
    Button etOkButton;
    ImageView etBackButton;

    private OnTableListener mOnTableListener;
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit_table, null);
        initViewItems();
        return rootView;
    }

    private void initViewItems() {
        etRows = rootView.findViewById(R.id.et_rows);
        etCols = rootView.findViewById(R.id.et_cols);

        etOkButton = rootView.findViewById(R.id.btn_ok);
        etBackButton = rootView.findViewById(R.id.iv_back);

        etOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOK();
            }
        });
        etBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBack();
            }
        });

    }

    void onClickBack() {
        getFragmentManager().beginTransaction().remove(this).commit();
    }

    void onClickOK() {
        if (mOnTableListener != null) {
            mOnTableListener.onTableOK(Integer.valueOf(etRows.getText().toString()),
                    Integer.valueOf(etCols.getText().toString()));
            onClickBack();
        }
    }

    public void setOnTableListener(OnTableListener mOnTableListener) {
        this.mOnTableListener = mOnTableListener;
    }

    public interface OnTableListener {
        void onTableOK(int rows, int cols);
    }
}
