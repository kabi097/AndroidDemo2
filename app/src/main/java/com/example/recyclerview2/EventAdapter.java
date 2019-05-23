package com.example.recyclerview2;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recyclerview2.EventFragment.OnListFragmentInteractionListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Event} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private final List<Event> mValues;
    private final OnListFragmentInteractionListener mListener;

    public EventAdapter(List<Event> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.label.setText(mValues.get(position).getName());
        if (mValues.get(position).isHas_photo()) {
            holder.image.setImageURI(Uri.parse(mValues.get(position).getPhoto_uri()));
        } else {
            holder.image.setImageResource(mValues.get(position).getImage_resource());
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 300);
        holder.image.setLayoutParams(layoutParams);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValues.remove(position);
                notifyItemRemoved(position);
                mListener.onListFragmentInteraction(holder.mItem, true);
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView image;
        public final TextView label;
        public final ImageView delete;
        public Event mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            label = (TextView) view.findViewById(R.id.label);
            image = (ImageView) view.findViewById(R.id.image);
            delete = (ImageView) view.findViewById(R.id.delete_icon);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + label.getText() + "'";
        }
    }

    public String saveToJson() {
        return new Gson().toJson(mValues);
    }

    public void readFromJson(String json) {
//        mValues.add(new Gson().fromJson(json, new TypeToken<ArrayList<Event>>(){}.getType()));
        mValues.clear();
        for (Event event : new ArrayList<Event>(Arrays.asList(new Gson().fromJson(json, Event[].class)))) {
            mValues.add(event);
        }
        notifyItemChanged(mValues.size()-1);
        //notifyDataSetChanged();
    }
}
