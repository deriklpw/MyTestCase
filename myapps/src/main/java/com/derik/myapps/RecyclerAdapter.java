package com.derik.myapps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by derik on 17-3-13.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private LayoutInflater layoutInflater;
    private List<?> list;
    private RecyclerListener.OnItemClickListener mOnClickListener;
    private RecyclerListener.OnItemLongClickListener mOnLongClickListener;

    public RecyclerAdapter(Context mContext, List<?> list){
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.listview_line, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder)holder).textView.setText((String)list.get(position));
        if (mOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onItemClick(holder.itemView, position);
                }
            });
        }

        if (mOnLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnLongClickListener.onItemLongClick(holder.itemView, position);

                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.list_line_text);
        }
    }

    public void setOnClickListener(RecyclerListener.OnItemClickListener listener){
        mOnClickListener = listener;
    }

    public void setOnLongClickListener(RecyclerListener.OnItemLongClickListener listener){
        mOnLongClickListener = listener;

    }
}
