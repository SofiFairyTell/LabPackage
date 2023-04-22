package LabFunctions.lab5;
import java.sql.*;

public class ParseSQL {
    private String connectionUrl;
    private String userName;
    private String password;
    private Statement statement;

    public ParseSQL() {

        try {
            ParseProperties propertiesParse = new ParseProperties();
            var settings = propertiesParse.bdSettings();
            this.connectionUrl = settings[0];
            this.userName = settings[1];
            this.password = settings[2];
            Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
            System.out.println("Подключение прошло успешно!");
            this.statement = (Statement) connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAll() throws SQLException {
        return this.statement.executeQuery("select * from cityList.City");
    }

    public ResultSet searchRecord(int id) throws SQLException {
        return statement.executeQuery("select * from cityList.City where id in(" + id + ");");
    }

    public void addNewRecord(String[] strings) throws SQLException {
        this.statement.executeUpdate("INSERT INTO cityList.City (country, name, mayor, population, area , qualityOfLife)" +
                " VALUES ('" + strings[0] + "','" + strings[1] + "', '" + strings[2] + "', '" + strings[3] + "', " +
                "'" + strings[4] + "','" + strings[5] + "')");
    }

    public void addNewRecord(City city) throws SQLException {
        statement.executeUpdate("INSERT INTO cityList.City (code, country, name,mayor ,population , area , qualityOfLife)" +
                " VALUES ('" + city.getCode() + "','" + city.getCountry() + "','" + city.getName() + "','" + city.getMayor() + "', '"
                + city.getPopulation() + "', '" + city.getArea() + "', " + "'" + city.getQualityOfLife() + "')");
    }

    public void updateRecord(int code) throws SQLException {
        SetData setData = new SetData();
        var strings = setData.setData();
        statement.executeUpdate("update cityList.City set country = '" + strings[0] + "', name = '" + strings[1]
                + "', maoyr = '" + strings[2] + "', population = '" + strings[3] + "', area = '" + strings[4]
                + "', qualityOfLife = '" + strings[5] + "' where code = " + code + ";");
    }

    public void updateRecord(int code, City city) throws SQLException {
        statement.executeUpdate("UPDATE cityList.City set country = '" + city.getCountry() + "', name = '" + city.getName()
                + "', mayor = '" + city.getMayor() + "', population = '" + city.getPopulation() + "', area = '" + city.getArea() +
                "', qualityOfLife = '" + city.getQualityOfLife() + "' where code = " + code + ";");
    }

    public void deleteRecord(int id) throws SQLException {
        statement.executeUpdate("delete from cityList.City where id in(" + id + ");");
    }

    public ResultSet workDataBase(int action) {
        try {
            //регистрация драйвера
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.postgresql.Driver");
            try {
                switch (action) {
                    case 1 -> {
                        return getAll();
                    }
                    case 2 -> {
                        System.out.println("Введите Id записи");
                        var id = Common.getID();
                        return searchRecord(id);
                    }
                    case 3 -> {
                        SetData setData = new SetData();
                        var strings = setData.setData();
                        addNewRecord(strings);
                    }
                    case 4 -> {
                        System.out.println("Введите Id записи");
                    }
                    case 5 -> {
                        System.out.println("Введите Id записи");
                        var id = Common.getID();
                        deleteRecord(id);
                    }
                }
            } catch (Exception e) {
                var prop = new ParseProperties();
                System.out.println(prop.error());
            }
        } catch (Exception e) {
            var prop = new ParseProperties();
            System.out.println(prop.errorDriver());
        }
        return null;
    }
}