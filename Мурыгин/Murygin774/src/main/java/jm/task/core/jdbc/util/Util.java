package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД

    private static SessionFactory sessionFactory;

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0402";

    private Util() {
    }

    public static Connection getConnection() { // для JBDC
        Connection conn = null;
        try {
            System.out.println("Соединение с БД");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static SessionFactory getSessionFactory() { // для HIBERNATE
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", USER)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // собираем реестр метаданных для создания sessionFactory
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            // Используем настройки из configuration, чтобы сконфигурировать и построить sessionFactory.
            // Принимает в качестве аргумента serviceRegistry,
            // который представляет сервисный реестр, содержащий необходимые службы и настройки для работы Hibernate.
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
