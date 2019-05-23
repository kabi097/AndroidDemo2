package com.example.recyclerview2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recyclerview2.dummy.DummyContent;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements EventFragment.OnListFragmentInteractionListener {

    static final int REQUEST_ADD = 1;
    static final int REQUEST_ADD_WITH_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String description = data.getStringExtra("description");
            String date = data.getStringExtra("date");

        } else if (requestCode == REQUEST_ADD_WITH_PHOTO && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String description = data.getStringExtra("description");
            String date = data.getStringExtra("date");
            String photo = data.getStringExtra("photo");
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

    }
}
