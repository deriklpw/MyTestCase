package com.derik.myapps.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.derik.myapps.R;


public class FragmentActivity extends AppCompatActivity implements CallBack {

    private String[] listDetails;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        listDetails = getResources().getStringArray(R.array.details);
        fragmentManager = getFragmentManager();

    }

    @Override
    public void onItemSelected(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(""+position, listDetails[position]);

        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_detail_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
