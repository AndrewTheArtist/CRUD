package controller.Recipe;

import controller.Cake.DaoCake;
import controller.RecipeDao;

import models.Cake;
import models.Recipe;
import resources.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoRecipe implements RecipeDao {
    private DaoRecipe() {
    }

    private static class SingletonHelper{
        private static final DaoRecipe INSTANCE = new DaoRecipe();
    }

    public static DaoRecipe getInstance() {
        return DaoRecipe.SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Recipe> find(String id) throws SQLException, Exception {

        String sql = "SELECT recipe_id, recipe_description, ingredients FROM Recipe WHERE recipe_id = ?";

        int recipe_id = 0;
        String recipe_description = "";
        String ingredients = "";

        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            recipe_id = resultSet.getInt("recipe_id");
            recipe_description = resultSet.getString("recipe_description");
            ingredients = resultSet.getString("ingredients");
        }
        return Optional.of(new Recipe(recipe_id, recipe_description, ingredients));
    }

    @Override
    public List<Recipe> findAll() throws SQLException {
        List<Recipe> listRecipe = new ArrayList<>();
        String sql = "SELECT recipe_id, recipe_description, ingredients FROM Recipe";

        Connection conn = DataSourceFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            int recipe_id = resultSet.getInt("recipe_id");
            String recipe_description = resultSet.getString("recipe_description");
            String ingredients = resultSet.getString("ingredients");

            Recipe recipe = new Recipe(recipe_id, recipe_description, ingredients);
            listRecipe.add(recipe);
        }
        return listRecipe;
    }

    @Override
    public boolean save(Recipe recipe) throws SQLException {
        String sql = "INSERT into Recipe (recipe_id, recipe_description, ingredients ) VALUES(?, ?, ?)";

        boolean rowInserted = false;

        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, recipe.getId());
        statement.setString(2, recipe.getDescription());
        statement.setString(3, recipe.getIngredients());
        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Recipe recipe) throws SQLException {
        String sql = "UPDATE Recipe SET recipe_id = ?, recipe_description = ?, ingredients = ?";
        sql += "WHERE recipe_id = ?";
        boolean rowUpdated = false;

        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, recipe.getId());
        statement.setString(2, recipe.getDescription());
        statement.setString(3, recipe.getIngredients());
        rowUpdated = statement.executeUpdate() > 0;

        return rowUpdated;
    }

    @Override
    public boolean delete(Recipe recipe) throws SQLException {
        String sql = "DELETE FROM Recipe where recipe_id = ?";
        boolean rowDeleted = false;

        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, recipe.getId());
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
