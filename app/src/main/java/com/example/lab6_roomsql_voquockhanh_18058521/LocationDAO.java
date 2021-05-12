package com.example.lab6_roomsql_voquockhanh_18058521;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationDAO {

    @Query("SELECT * FROM location")
    public List<Location> getAll();

    @Query("SELECT * FROM location WHERE id=(:id)")
    public Location getLocation(int id);

    @Insert
    public void insert(Location location);

    @Update
    public void edit(Location location);

    @Delete
    public void delete(Location location);
}
