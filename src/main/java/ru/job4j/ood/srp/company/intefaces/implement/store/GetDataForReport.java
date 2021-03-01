package ru.job4j.ood.srp.company.intefaces.implement.store;

import ru.job4j.ood.srp.company.intefaces.Store;
import ru.job4j.ood.srp.company.model.Employer;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class GetDataForReport implements Store {

    private Connection connection;
    private Properties properties = new Properties();

    public GetDataForReport(String fileProperties) {
        getConfig(fileProperties);
        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getConfig(String file) {
        ClassLoader loader = GetDataForReport.class.getClassLoader();
        try {
            properties.load(loader.getResourceAsStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employer> findByDepartment(String department) {
        List<Employer> employees = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM company_employees WHERE department = ?"
        )) {
            statement.setString(1, department);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Employer employer = new Employer();
                    employer.setId(resultSet.getInt("id"));
                    employer.setName(resultSet.getString("name"));
                    employer.setHired(timestampToCalendar(resultSet.getTimestamp("hired")));
                    employer.setFired(timestampToCalendar(resultSet.getTimestamp("fired")));
                    employer.setSalary(resultSet.getDouble("salary"));
                    employer.setDepartment(resultSet.getString("department"));
                    employees.add(employer);
                }
            }
        } catch (SQLException troubles) {
            troubles.printStackTrace();
        }
        return employees;
    }

    private Calendar timestampToCalendar(Timestamp timestamp) {
        Calendar calendar = new GregorianCalendar();
        if (timestamp != null) {
            Date date = new Date(timestamp.getTime());
            calendar.setTime(date);
        } else {
            calendar = null;
        }
        return calendar;
    }
}
