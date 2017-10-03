package com.iconasystems.android.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iconasystems.android.authensandroid.R;
import com.iconasystems.android.models.Lock;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.view.IconicsImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 2/16/2016.
 */
public class KeyAdapter extends RecyclerView.Adapter<KeyAdapter.ViewHolder> {

    private List<Lock> lockList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private SparseBooleanArray selectedItems;

    public KeyAdapter(Context context, List<Lock> lockList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.lockList = lockList;
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Lock lock);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.key_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(lockList.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {

        return lockList.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mDoorName;
        public IconicsImageView mLockStatusLocked;
        public IconicsImageView mLockStatusUnLocked;
        public View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            mDoorName = (TextView) itemView.findViewById(R.id.lock_name);
            mLockStatusLocked = (IconicsImageView) itemView.findViewById(R.id.lock_status_locked);
            mLockStatusUnLocked = (IconicsImageView) itemView.findViewById(R.id.lock_status_unlocked);
        }

        public void bind(final Lock lock, final OnItemClickListener onItemClickListener) {
            String door_name = lock.getDoorName();
            String lock_status = lock.getState();
            if (lock_status.equals("CLOSED")) {
                mLockStatusLocked.setVisibility(View.VISIBLE);
            } else if (lock_status.equals("OPEN")) {
                mLockStatusUnLocked.setVisibility(View.VISIBLE);
            }

            mDoorName.setText(door_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                public SparseBooleanArray selectedItems = new SparseBooleanArray();

                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(lock);
                    itemView.setSelected(true);
                    if (selectedItems.get(getAdapterPosition(), false)) {
                        selectedItems.delete(getAdapterPosition());
                        itemView.setSelected(false);
                    } else {
                        selectedItems.put(getAdapterPosition(), true);
                        itemView.setSelected(true);
                    }
                }

            });
        }
    }


}
