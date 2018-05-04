package com.derik.myapps.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.derik.myapps.R;

/**
 * Created by derik on 17-3-14.
 */

public class ItemListFragment extends ListFragment {


    private CallBack callBack;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        String[] list = getActivity().getResources().getStringArray(R.array.items);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list);
        setListAdapter(arrayAdapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callBack = (CallBack) activity;
    }

    public void onDetach() {
        super.onDetach();
        callBack = null;

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        callBack.onItemSelected(position);
        Log.e("TAG",""+position);
    }

}
