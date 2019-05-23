package com.example.recyclerview2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerview2.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class EventFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    private EventAdapter eventAdapter;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            List<Event> sample = new ArrayList<>();
//            sample.add(new Event("Koncert zespołu Enej", R.drawable.ic_event_note_black_24dp, new Date(), "Koncert z okazji 25-lecia gminy"));
//            sample.add(new Event("Premiera filmu Jacht w reż. Andrzeja Kakaowego", R.drawable.ic_event_note_black_24dp, new Date(), "Wyjątkowe wydarzenie w naszej okolicy"));
            eventAdapter = new EventAdapter(sample, mListener);
            recyclerView.setAdapter(eventAdapter);
            eventAdapter.readFromJson("[{'date':'May 18, 2019 6:58:42 PM','description':'Koncert z okazji 25-lecia gminy','image_resource':" + R.drawable.event1 + ",'name':'Koncert zespołu Enej'},{'date':'May 18, 2019 6:58:42 PM','description':'Wyjątkowe wydarzenie w naszej okolicy','image_resource':" + R.drawable.event2 + ",'name':'Premiera filmu Jacht w reż. Andrzeja Kakaowego'}]");
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Event item, boolean delete);
    }
}
