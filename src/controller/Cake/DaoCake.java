package controller.Cake;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.CakeDao;
import models.Cake;
import resources.DataSourceFactory;

public class DaoCake  implements CakeDao {
    private DaoCake() {
    }

    private static class SingletonHelper{
        private static final DaoCake INSTANCE = new DaoCake();
    }

    public static DaoCake getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Cake> find(String id) throws SQLException {
        String sql = "SELECT cake_id, cake_name, recipe_id FROM Cake WHERE cake_id = ?";

        int cake_id = 0, recipe_id = 0;
        String cake_name = "";

        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            cake_id = resultSet.getInt("cake_id");
            cake_name = resultSet.getString("cake_name");
            recipe_id = resultSet.getInt("recipe_id");
        }
        return Optional.of(new Cake(cake_id, cake_name, recipe_id));
    }

    @Override
    public List<Cake> findAll() throws SQLException {
        List<Cake> listCake = new ArrayList<>();
        String sql = "SELECT cake_id, cake_name, recipe_id FROM Cake";

        Connection conn = DataSourceFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            int cake_id = resultSet.getInt("cake_id");
            String cake_name = resultSet.getString("cake_name");
            int recipe_id = resultSet.getInt("recipe_id");

            Cake cake = new Cake(cake_id, cake_name, recipe_id);
            listCake.add(cake);
        }
        return listCake;
    }

    @Override
    public boolean save(Cake cake) throws SQLException {
        String sql = "INSERT into Cake (cake_id, cake_name, recipe_id) VALUES(?, ?, ?)";

        boolean rowInserted = false;
        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, cake.getId());
        statement.setString(2, cake.getName());
        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;

    }

    @Override
    public boolean update(Cake cake) throws SQLException {
        String sql = "UPDATE Cake SET cake_id= ?, cake_name= ?";
        sql += "WHERE cake_id = ?";
        boolean rowUpdated = false;

        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, cake.getId());
        statement.setString(2, cake.getName());
        rowUpdated = statement.executeUpdate() > 0;

        return rowUpdated;
    }

    public boolean delete(Cake cake) throws SQLException{

        String sql = "DELETE FROM Cake where cake_id = ?";
        boolean rowDeleted = false;

        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, cake.getId());
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }


}
