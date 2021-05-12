package com.example.lab6_roomsql_voquockhanh_18058521;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Location.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LocationDAO locationDAO();
}
