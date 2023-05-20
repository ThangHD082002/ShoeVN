/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HandleWithSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Thắng đẹp trai
 */
public class ConectMySQL {

    protected Connection connection;

    public ConectMySQL() {
        try{
            String url = "jdbc:mysql://localhost:3306/ttcs";
            String username = "root";
            String password = "27082002";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException | ClassNotFoundException e){    
            System.err.println(e);
        }

    }
}
