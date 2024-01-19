package com.example.lab_1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface FavouriteRecipeDAO {
    @Query("SELECT * FROM FavouriteRecipe")
    List<FavouriteRecipe> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(FavouriteRecipe... favouriteRecipe);

    @Delete
    void delete(FavouriteRecipe favouriteRecipe);
}