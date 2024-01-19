package com.example.lab_1;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys =
        {
                @ForeignKey(entity = Recipe.class,
                        parentColumns = "uid",
                        childColumns = "recipeId",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                ),
                @ForeignKey(entity = User.class,
                        parentColumns = "uid",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                ),
        })
public class FavouriteRecipe {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    public int recipeId;
    public int userId;
}
