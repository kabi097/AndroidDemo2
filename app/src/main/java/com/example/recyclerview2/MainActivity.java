package com.example.recyclerview2;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements EventFragment.OnListFragmentInteractionListener {

    static final int REQUEST_ADD = 1;
    static final int REQUEST_ADD_WITH_PHOTO = 2;
    private File json_file;
    EventFragment eventFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        json_file = new File(getFilesDir(), "json.txt");

        FloatingActionButton fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(addIntent, REQUEST_ADD);
            }
        });

        FloatingActionButton fab_photo = findViewById(R.id.fab_photo);
        fab_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(getApplicationContext(), AddActivity.class);
                addIntent.putExtra("photo", true);
                startActivityForResult(addIntent, REQUEST_ADD_WITH_PHOTO);
            }
        });

        eventFragment = (EventFragment) getSupportFragmentManager().findFragmentById(R.id.event_fragment);
        try {
            eventFragment.loadJson(readJsonFromFile(json_file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (getResources().getConfiguration().orientation == 2) {
            PreviewFragment previewFragment = (PreviewFragment) getSupportFragmentManager().findFragmentById(R.id.preview_fragment);
            previewFragment.getView().setVisibility(View.GONE);
        }

    }

    public void saveJsonToFile(String json) {
        try {
            if (!json_file.exists()) {
                json_file.createNewFile();
            }
            FileWriter writer = new FileWriter(json_file);
            writer.append(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readJsonFromFile(File file) throws FileNotFoundException {
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        FileInputStream in = new FileInputStream(file);
        try {
            in.read(bytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String description = data.getStringExtra("description");
            String date = data.getStringExtra("date");

            TypedArray images = getResources().obtainTypedArray(R.array.loading_images);
            int image_resource = images.getResourceId((int) (Math.random() * images.length()), R.drawable.event1);

            try {
                Gson gson = new Gson();
                ArrayList<Event> all_events = new ArrayList<Event>(Arrays.asList(new Gson().fromJson(readJsonFromFile(json_file), Event[].class)));
                Event event = new Event(name,date, description);
                event.setImage_resource(image_resource);
                all_events.add(event);
                String json = gson.toJson(all_events);
                saveJsonToFile(json);
                eventFragment.loadJson(json);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else if (requestCode == REQUEST_ADD_WITH_PHOTO && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String description = data.getStringExtra("description");
            String date = data.getStringExtra("date");
            String photo = data.getStringExtra("photo");

            try {
                Gson gson = new Gson();
                ArrayList<Event> all_events = new ArrayList<Event>(Arrays.asList(new Gson().fromJson(readJsonFromFile(json_file), Event[].class)));
                Event event = new Event(name,date,description);
                event.setHas_photo(true);
                event.setPhoto_uri(photo);
                all_events.add(event);
                String json = gson.toJson(all_events);
                saveJsonToFile(json);
                eventFragment.loadJson(json);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Event item, boolean delete) {

        if (delete) {
            if (getResources().getConfiguration().orientation == 2) {
                PreviewFragment previewFragment = (PreviewFragment) getSupportFragmentManager().findFragmentById(R.id.preview_fragment);
                previewFragment.getView().setVisibility(View.GONE);
            }
            saveJsonToFile(eventFragment.getEventAdapter().saveToJson());

        } else {
            if (getResources().getConfiguration().orientation == 1) {
                Intent previewIntent = new Intent(this, PreviewActivity.class);
                previewIntent.putExtra("event", item);
                startActivity(previewIntent);
            } else {
                PreviewFragment previewFragment = (PreviewFragment) getSupportFragmentManager().findFragmentById(R.id.preview_fragment);
                previewFragment.getView().setVisibility(View.VISIBLE);
                previewFragment.setEvent(item);
            }
        }
    }
}
