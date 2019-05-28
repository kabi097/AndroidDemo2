package com.example.recyclerview2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class PreviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        Event event = (Event) getIntent().getSerializableExtra("event");
        PreviewFragment previewFragment = (PreviewFragment) getSupportFragmentManager().findFragmentById(R.id.preview_fragment2);
        previewFragment.setEvent(event);
    }
}
