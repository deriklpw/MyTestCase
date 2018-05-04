package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookContent {
	public static class Book{
		public Integer idInteger;
		public String titleString;
		public String descString;
		public Book(Integer id, String title, String desc){
			this.idInteger = id;
			this.titleString =title;
			this.descString = desc;
			
		}
		@Override
		public String toString(){
			return titleString;
		}
	}
	
	public static List<Book> ITEMS=new ArrayList<Book>();
	public static Map<Integer,Book> ITEM_MAP = new HashMap<Integer, Book>();
	static {
		addItem(new Book(1, "test1", "test1 detials"));
		addItem(new Book(2, "test2", "test2 detials"));
		addItem(new Book(3, "test3", "test3 detials"));
	}
	private static void addItem(Book book){
		ITEMS.add(book);
		ITEM_MAP.put(book.idInteger, book);
	}
	
	
	
}
