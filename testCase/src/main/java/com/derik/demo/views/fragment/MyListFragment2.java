package com.derik.demo.views.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.derik.demo.R;

/**
 * Created by derik on 16-9-1.
 */
public class MyListFragment2 extends ListFragment {
    private String arr[];

    private CallBacks callBacks;
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        arr = new String[]{"0","1","2","3","4","5","6","7","8","9"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, arr);
        setListAdapter(arrayAdapter);
        fragmentManager = getFragmentManager();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
//        if (!(activity instanceof CallBacks)){
//            throw new IllegalStateException("Activity 必须实现Callbacks接口");
//        }
//        callBacks = (CallBacks) activity;

        callBacks = new CallBacks() {
            @Override
            public void onItemSelected(int id) {
                Bundle data = new Bundle();
                data.putInt("key", id);
                MyDetailFragment myDetailFragment = new MyDetailFragment();

                // Activity中通过在其中的fragment的setArguments()方法传递数据给fragment
                // setArguments()和getArguments均是Fragment对象的方法
                myDetailFragment.setArguments(data);

                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.fragment3, myDetailFragment);
                transaction.addToBackStack("Test");
                transaction.commit();
            }
        };
    }

    @Override
    public void onDetach(){
        super.onDetach();
        if (callBacks != null){
            callBacks = null;
        }
    }


    @Override
    public void onListItemClick(ListView listView, View view, int position, long id){
        switch(position){
            case 0:
                Log.i("Fragment2, ","clicked item " + position);
                Toast.makeText(getActivity(), "Fragment2,  " + position, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Log.i("Fragment2, ","clicked item " + position);
                Toast.makeText(getActivity(), "Fragment2,  " + position, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Log.i("Fragment2, ","clicked item " + position);
                Toast.makeText(getActivity(), "Fragment2,  " + position, Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Log.i("Fragment2, ","clicked item " + position);
                Toast.makeText(getActivity(), "Fragment2,  " + position, Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Log.i("Fragment2, ","clicked item " + position);
                Toast.makeText(getActivity(), "Fragment2,  " + position, Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Log.i("Fragment2, ","clicked item " + position);
                Toast.makeText(getActivity(), "Fragment2,  " + position, Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Log.i("Fragment2, ","clicked item " + position);
                Toast.makeText(getActivity(), "Fragment2,  " + position, Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Log.i("Fragment2, ","clicked item " + position);
                Toast.makeText(getActivity(), "Fragment2,  " + position, Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Log.i("Fragment2, ","clicked item " + position);
                Toast.makeText(getActivity(), "Fragment2,  " + position, Toast.LENGTH_SHORT).show();
                break;
            case 9:
                Log.i("Fragment2, ","clicked item " + position);
                Toast.makeText(getActivity(), "Fragment2,  " + position, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        // 单击
        callBacks.onItemSelected(position);

    }

    public void setActivateOnItemClick(boolean activateOnItemClick){
        getListView().setChoiceMode(activateOnItemClick?ListView.CHOICE_MODE_SINGLE:ListView.CHOICE_MODE_NONE);
    }

}
