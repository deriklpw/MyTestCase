package com.example.fragment;

import com.example.screenapp.R;

import model.BookContent;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BookDetailFragment extends Fragment {
	public static final String ITEM_ID = "Item_id";
	BookContent.Book book;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments().containsKey(ITEM_ID)) {
			book = BookContent.ITEM_MAP.get(getArguments().getInt(ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_book_detail,
				container, false);
		if (book != null) {
			((TextView) rootView.findViewById(R.id.textview1)).setText(book.titleString);
			((TextView) rootView.findViewById(R.id.textview2)).setText(book.descString);			
		}
		return rootView;
	}
}
