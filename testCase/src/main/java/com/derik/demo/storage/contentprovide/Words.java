package com.derik.demo.storage.contentprovide;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Words {

	public static final String AUTHORITY = "org.foxconn.provider.first";
	public static final class Word implements BaseColumns {
		public final static String _ID = "_id";
		public final static String WORD = "word";
		public final static String DETAIL = "detail";

		// 以Uri来确定可以访问的资源
		public final static Uri DICT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/words");
		public final static Uri WORD_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/word");
	}
}
