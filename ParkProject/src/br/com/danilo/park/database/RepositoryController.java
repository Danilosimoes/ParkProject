package br.com.danilo.park.database;

import br.com.danilo.park.domain.Controller;
import com.mysql.jdbc.UpdatableResultSet;

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
                    rs.getTimestamp("DATE_OUT") != null ? rs.getTimestamp("DATE_OUT").toLocalDateTime() : null
            ));
        }
        con.close();
        ps.close();
        return list;
    }

    public void save(Controller controller) throws SQLException {
        Connection con = MySQLConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO CONTROLLER (LICENSE, MODEL, DATE_IN, DATE_OUT) VALUES (?,?,?,?)");
        ps.setString(1, controller.getLicense());
        ps.setString(2, controller.getModel());
        ps.setTimestamp(3, Timestamp.valueOf(controller.getDateIn()));
        ps.setTimestamp(4,  controller.getDateOut() != null ? Timestamp.valueOf(controller.getDateOut()) : null);
        ps.executeUpdate();
        con.close();
        ps.close();
    }

    public void update(String license, Timestamp dateOut) throws SQLException {
        Connection con = MySQLConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE CONTROLLER SET DATE_OUT = ? WHERE LICENSE = ? AND DATE_OUT IS NULL ");
        ps.setTimestamp(1, dateOut);
        ps.setString(2, license);
        ps.executeUpdate();
        con.close();
        ps.close();
    }

}
