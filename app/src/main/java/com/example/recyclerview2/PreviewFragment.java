package com.example.recyclerview2;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewFragment extends Fragment {

    View view;

    public PreviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_preview, container, false);
        return view;
    }

    public void setEvent(Event event) {
        TextView name = (TextView) view.findViewById(R.id.preview_name);
        TextView description = (TextView) view.findViewById(R.id.preview_description);
        TextView date = (TextView) view.findViewById(R.id.preview_date);
        ImageView image = (ImageView) view.findViewById(R.id.preview_image);
        if (event.isHas_photo()) {
            image.setImageURI(Uri.parse(event.getPhoto_uri()));
        } else {
            image.setImageResource(event.getImage_resource());
//            image.setBackgroundResource(event.getImage_resource());
//            Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), event.getImage_resource());
//            image.setImageBitmap(bImage);
        }
        name.setText(event.getName());
        description.setText(event.getDescription());
        date.setText(event.getDate());

    }
}
