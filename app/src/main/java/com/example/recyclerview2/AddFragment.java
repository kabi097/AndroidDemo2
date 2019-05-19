package com.example.recyclerview2;

import android.content.Context;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class AddFragment extends Fragment {
    private static final String ARG_TITLE = "param1";
    private static final String ARG_DESCRIPTION = "param2";
    private static final String ARG_DATE = "param2";

    private String title;
    private String description;
    private String date;

    OnDataPass dataPasser;

    public AddFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2, String param3) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, param1);
        args.putString(ARG_DESCRIPTION, param2);
        args.putString(ARG_DATE, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            description = getArguments().getString(ARG_DESCRIPTION);
            date = getArguments().getString(ARG_DATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        try {
            dataPasser = (AddActivity) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnDataPass");
        }
        Button confirm = (Button) view.findViewById(R.id.confirm_button);
        final EditText name = (EditText) view.findViewById(R.id.title);
        final EditText description = (EditText) view.findViewById(R.id.description);
        final EditText date = (EditText) view.findViewById(R.id.date);

        confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                dataPasser.onDataPass(name.getText().toString(), description.getText().toString().toString(), date.getText().toString());
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public interface OnDataPass {
        public void onDataPass(String name, String description, String date);
    }
}
