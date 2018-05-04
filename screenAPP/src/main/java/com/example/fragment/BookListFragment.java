package com.example.fragment;

import model.BookContent;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BookListFragment extends ListFragment {
	private Callbacks callbacks;
	
	public interface Callbacks{
		public void onItemSelected(Integer id);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<BookContent.Book>(getActivity(), android.R.layout.simple_list_item_activated_1, BookContent.ITEMS));
	}
    //当Fragment被添加、显示到activity中时，回调该方法
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException("BookListFragment所在的Activity必须实现Callbacks接口");
		}
		callbacks = (Callbacks)activity;
	}
	
	@Override
	public void onDetach(){
		super.onDetach();
		callbacks = null;
	}
	
	@Override
	public void onListItemClick(ListView listView,View view, int position, long id){
		super.onListItemClick(listView, view, position, id);
		callbacks.onItemSelected(BookContent.ITEMS.get(position).idInteger);
		Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
	}

	public void setActivateOnItemClick(boolean activateOnItemClick){
		getListView().setChoiceMode(activateOnItemClick?ListView.CHOICE_MODE_SINGLE:ListView.CHOICE_MODE_NONE);
	}
}
