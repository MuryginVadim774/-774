package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    private static UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public static void main(String[] args) {
//         реализуйте алгоритм здесь
        userDaoJDBC.dropUsersTable();

        userDaoJDBC.createUsersTable();


        userDaoJDBC.saveUser("Alexander", "Boiko", (byte) 35);
        userDaoJDBC.saveUser("Nicolai", "SLOMENKO", (byte) 64);
        userDaoJDBC.saveUser("Lev", "DURATSKII", (byte) 55);
        userDaoJDBC.saveUser("Marina", "SHABOLDAAAA", (byte) 58);

        userDaoJDBC.getAllUsers().forEach(System.out::println);

        userDaoJDBC.cleanUsersTable();

        userDaoJDBC.dropUsersTable();

    }
}
