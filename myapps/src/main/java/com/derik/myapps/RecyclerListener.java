package com.derik.myapps;

import android.view.View;

/**
 * Created by derik on 17-3-13.
 */

public abstract class RecyclerListener {
    interface OnItemClickListener {
        void onItemClick(View v, int position);

    }

    interface OnItemLongClickListener {
        void onItemLongClick(View v, int position);
    }
}
