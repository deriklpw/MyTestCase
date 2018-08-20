package com.derik.demo.views.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.derik.demo.R;
import com.derik.library.view.MsgToast;

/**
 * Created by derik on 16-9-1.
 */
public class MyListFragment extends Fragment {

    private ListView listView;
    private String arr[];
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        arr = new String[]{"0","1","2","3","4","5","6","7","8","9"};
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mContext = getActivity().getApplicationContext();
        View rootView = inflater.inflate(R.layout.fragment_list, container);
        listView = (ListView)rootView.findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, R.layout.line, R.id.my_title, arr);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {

                    case 0:
                        Log.i("Fragment1, ","clicked item "+i);
                        MsgToast.show(mContext, "Fragment1,  " + i);
                        break;
                    case 1:
                        Log.i("Fragment1, ","clicked item "+i);
                        MsgToast.show(mContext, "Fragment1,  " + i);
                        break;
                    case 2:
                        Log.i("Fragment1, ","clicked item "+i);
                        MsgToast.show(mContext, "Fragment1,  " + i);
                        break;
                    case 3:
                        Log.i("Fragment1, ","clicked item "+i);
                        MsgToast.show(mContext, "Fragment1,  " + i);
                        break;
                    case 4:
                        Log.i("Fragment1, ","clicked item "+i);
                        MsgToast.show(mContext, "Fragment1,  " + i);
                        break;
                    case 5:
                        Log.i("Fragment1, ","clicked item "+i);
                        MsgToast.show(mContext, "Fragment1,  " + i);
                        break;
                    case 6:
                        Log.i("Fragment1, ","clicked item "+i);
                        MsgToast.show(mContext, "Fragment1,  " + i);
                        break;
                    case 7:
                        Log.i("Fragment1, ","clicked item "+i);
                        MsgToast.show(mContext, "Fragment1,  " + i);
                        break;
                    case 8:
                        Log.i("Fragment1, ","clicked item "+i);
                        MsgToast.show(mContext, "Fragment1,  " + i);
                        break;
                    case 9:
                        Log.i("Fragment1, ","clicked item "+i);
                        MsgToast.show(mContext, "Fragment1,  " + i);
                        break;
                    default:
                        break;
                }

            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

    }

    @Override
    public void onDetach(){
        super.onDetach();
    }

}
