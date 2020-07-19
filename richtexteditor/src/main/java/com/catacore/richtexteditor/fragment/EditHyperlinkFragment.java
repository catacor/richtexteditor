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


public class EditHyperlinkFragment extends Fragment {

    EditText etAddress;
    EditText etDisplayText;

    private OnHyperlinkListener mOnHyperlinkListener;
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit_hyperlink, null);

        initViewItems();
        return rootView;
    }

    private void initViewItems() {
        etAddress = rootView.findViewById(R.id.et_address);
        etDisplayText = rootView.findViewById(R.id.et_display_text);

        ImageView iv_back = rootView.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBack();
            }
        });
        Button btn_ok = rootView.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOK();
            }
        });
    }

    void onClickBack() {
        getFragmentManager().beginTransaction().remove(this).commit();
    }

    void onClickOK() {
        if (mOnHyperlinkListener != null) {
            mOnHyperlinkListener.onHyperlinkOK(etAddress.getText().toString(),
                    etDisplayText.getText().toString());
            onClickBack();
        }
    }

    public void setOnHyperlinkListener(OnHyperlinkListener mOnHyperlinkListener) {
        this.mOnHyperlinkListener = mOnHyperlinkListener;
    }

    public interface OnHyperlinkListener {
        void onHyperlinkOK(String address, String text);
    }
}
