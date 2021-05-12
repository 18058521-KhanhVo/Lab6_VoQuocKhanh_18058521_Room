package com.example.lab6_roomsql_voquockhanh_18058521;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Location> locations;
    private LocationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "location_db").allowMainThreadQueries().build();
        LocationDAO dao = db.locationDAO();

        locations = (ArrayList<Location>) dao.getAll();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LocationAdapter(locations, this, dao);
        recyclerView.setAdapter(adapter);

        TextView plaintext = findViewById(R.id.plaintext);
        Button btnsave = findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = plaintext.getText().toString();
                if (s.equals(""))
                    Toast.makeText(MainActivity.this, "Vui long nhap ten dia diem truoc khi luu", Toast.LENGTH_SHORT).show();
                else {
                    Location location = new Location(s);
                    dao.insert(location);
                    plaintext.setText("");
                    locations.clear();
                    locations = (ArrayList<Location>) dao.getAll();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}