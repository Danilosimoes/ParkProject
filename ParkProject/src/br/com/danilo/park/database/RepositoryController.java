package br.com.danilo.park.database;

import br.com.danilo.park.domain.Controller;
import com.mysql.jdbc.UpdatableResultSet;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositoryController {

    public List<Controller> getAll() throws SQLException {
        List<Controller> list = new ArrayList<>();
        Connection con = MySQLConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM CONTROLLER");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Controller(
                    rs.getLong("ID"),
                    rs.getString("LICENSE"),
                    rs.getString("MODEL"),
                    rs.getTimestamp("DATE_IN").toLocalDateTime(),
                    rs.getTimestamp("DATE_OUT") != null ? rs.getTimestamp("DATE_OUT").toLocalDateTime() : null,
                    rs.getDouble("TOTAL")
            ));
        }
        con.close();
        ps.close();
        return list;
    }

    public void save(Controller controller) throws SQLException {
        Connection con = MySQLConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO CONTROLLER (LICENSE, MODEL, DATE_IN, DATE_OUT, TOTAL) VALUES (?,?,?,?,?)");
        ps.setString(1, controller.getLicense());
        ps.setString(2, controller.getModel());
        ps.setTimestamp(3, Timestamp.valueOf(controller.getDateIn()));
        ps.setTimestamp(4,  controller.getDateOut() != null ? Timestamp.valueOf(controller.getDateOut()) : null);
        ps.setDouble(5, controller.getTotal());
        ps.executeUpdate();
        con.close();
        ps.close();
    }

    public void update(String license, Timestamp dateOut, double total) throws SQLException {
        Connection con = MySQLConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE CONTROLLER SET DATE_OUT = ?, TOTAL = ? WHERE LICENSE = ? AND DATE_OUT IS NULL");
        ps.setTimestamp(1, dateOut);
        ps.setDouble(2, total);
        ps.setString(3, license);
        ps.executeUpdate();
        con.close();
        ps.close();
    }

    public Controller find(String license) throws SQLException {
        Connection con = MySQLConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM CONTROLLER WHERE LICENSE = ? AND DATE_OUT IS NULL LIMIT 1");
        ps.setString(1, license);
        ResultSet rs = ps.executeQuery();
        Controller controller = null;
        if(rs.next()){
            controller = new Controller(
                    rs.getLong("ID"),
                    rs.getString("LICENSE"),
                    rs.getString("MODEL"),
                    rs.getTimestamp("DATE_IN").toLocalDateTime(),
                    rs.getTimestamp("DATE_OUT") != null ? rs.getTimestamp("DATE_OUT").toLocalDateTime() : null,
                    rs.getDouble("TOTAL"));
        }

        con.close();
        ps.close();
        return controller;
    }
}
