package com.derik.myapps.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.derik.myapps.R;

/**
 * Created by derik on 17-3-14.
 */

public class ItemDetailFragment extends Fragment {

    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_item_detail, container, false);
        textView = (TextView) layout.findViewById(R.id.fragment_detail_text);
        if (getArguments().containsKey("0")) {
            textView.setText(getArguments().getString("0"));
        }

        if (getArguments().containsKey("1")) {
            textView.setText(getArguments().getString("1"));
        }

        if (getArguments().containsKey("2")) {
            textView.setText(getArguments().getString("2"));
        }
        if (getArguments().containsKey("3")) {
            textView.setText(getArguments().getString("3"));
        }

        if (getArguments().containsKey("4")) {
            textView.setText(getArguments().getString("4"));
        }

        if (getArguments().containsKey("5")) {
            textView.setText(getArguments().getString("5"));
        }

        if (getArguments().containsKey("6")) {
            textView.setText(getArguments().getString("6"));
        }

        if (getArguments().containsKey("7")) {
            textView.setText(getArguments().getString("7"));
        }

        return layout;
    }
}
