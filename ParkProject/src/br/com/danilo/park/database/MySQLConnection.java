package br.com.danilo.park.database;


import br.com.danilo.park.domain.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



// MySQL      -> gestão de conexao
// Repository -> operações no banco
// Service    -> meio de campo com a usa logica/validacao/integration.... Repository
// Interface/View  -> Abstracao do seu Service com o usuario


//Desafio
// Criar relatórios
// Bubg entrada e saída
// Aperfeiçoar swing
// acrescentar total

public class MySQLConnection {


    public static final Connection getConnection() throws SQLException {
        return DriverManager.getConnection("url", "root", "password");
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("Testing Connection");

        List<Controller> result = new ArrayList<>();

        String SQL_SELECT = "Select * from CONTROLLER";
        try (
                Connection conn = DriverManager.getConnection(
                        "url", "root", "password");
                PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT))
        {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long ID = resultSet.getLong("ID");
                String LICENSE = resultSet.getString("LICENSE");
                String MODEL = resultSet.getString("MODEL");
                Timestamp TIMEIN = resultSet.getTimestamp("TIMEIN");
                Timestamp TIMEOUT = resultSet.getTimestamp("TIMEOUT");

                Controller controller = new Controller();
                controller.setId(ID);
                controller.setLicense(LICENSE);
                controller.setModel(MODEL);
                controller.setDateIn(TIMEIN.toLocalDateTime());
                controller.setDateOut(TIMEOUT.toLocalDateTime());


                result.add(controller);


            }
            result.forEach(x -> System.out.println(x));
        } catch (SQLException e) {
            System.err.format("SQL Statte: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



