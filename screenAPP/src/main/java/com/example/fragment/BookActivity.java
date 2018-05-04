package com.example.fragment;

import com.example.screenapp.R;

import android.app.Activity;
import android.os.Bundle;

public class BookActivity extends Activity implements BookListFragment.Callbacks{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_twopane);
		
	}

	@Override
	public void onItemSelected(Integer id){
		Bundle arguments = new Bundle();
		arguments.putInt(BookDetailFragment.ITEM_ID, id);
		BookDetailFragment fragment = new BookDetailFragment();
		fragment.setArguments(arguments);
		getFragmentManager().beginTransaction().replace(R.id.fragment2, fragment).commit();
	}
}
