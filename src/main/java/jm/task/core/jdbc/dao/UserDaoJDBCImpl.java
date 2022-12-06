package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        String sql = "create table if not exists users(id int not null auto_increment primary key, name varchar(45), lastName varchar(45), age int)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        String sql = "drop table if exists users;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = Util.getConnection();
        String sql = "INSERT INTO users (name, lastname, age) VALUES('" + name + "', '" + lastName +"', "+ age + ")";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        System.out.println("User with name " + name+ " has been added to the database");
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = Util.getConnection();
        String sql = "delete from users where id="+id;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<User>();
        Connection connection = Util.getConnection();
        String sql = "select * from users";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastName"));
            user.setAge(resultSet.getByte("age"));
            System.out.println(user);
            userList.add(user);
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        String sql = "delete from users";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }
}
