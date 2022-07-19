package com.example.mynotesapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mynotesapp.R;
import com.example.mynotesapp.model.Notes;
import com.google.android.material.navigation.NavigationView;

public class NotesActivity extends AppCompatActivity {
    Notes notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        if (!isLandscape()){
        initDrawer();}

        if (savedInstanceState == null) {
            NotesFragmentOne notesFragment = new NotesFragmentOne();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_one_container, notesFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_about:
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("")
                        .replace(R.id.fragment_one_container, new AboutFragment()).commit();
                return true;
            case R.id.action_create_new_note:
                Notes.notesArrayList.add(new Notes("Заметка"));
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("")
                        .replace(R.id.fragment_one_container, new NotesFragmentOne()).commit();
                return true;
            case R.id.action_find:
                Toast.makeText(this, "Здесь должен быть поиск по заметкам", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDrawer() {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_drawer_about:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack("")
                                .replace(R.id.fragment_one_container, new AboutFragment()).commit();
                        return true;
                    case R.id.action_drawer_settings:
                        return true;
                }
                return true;
            }
        });

    }
    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

}