package br.com.danilo.park.database;


import br.com.danilo.park.domain.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Realizando a cnexão com o banco de dados

public class MySQLConnection {


    public static final Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "url",
                "user",
                "pass"



        );
    }


}



