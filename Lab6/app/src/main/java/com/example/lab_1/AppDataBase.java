package com.example.lab_1;

import androidx.room.*;

@Database(entities = {Recipe.class, User.class, FavouriteRecipe.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {
    public abstract RecipeDAO recipeDAO();
    public abstract UserDAO userDAO();

    public abstract FavouriteRecipeDAO favouriteRecipeDAO();
}