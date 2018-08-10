/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsis4.bd;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author DênisLeonardo
 */

public class ConexaoBD {
    
    private static ConexaoBD conexaoBD;
    
    private BasicDataSource dataSource;
    private String user = "hr";
    private String pass = "hr";
    private String driver = "oracle.jdbc.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:XE";
    
    private ConexaoBD() {
        dataSource = new BasicDataSource();
        dataSource.setUsername(user);
        dataSource.setPassword(pass);
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
    }
    
    public static ConexaoBD getInstance() {
        if(conexaoBD == null) {
            conexaoBD = new ConexaoBD();
        }
        return conexaoBD;
    }
    
    public Connection getConnection() throws SQLException {
        //System.out.println(dataSource.getInitialSize());
        return dataSource.getConnection();
    }
    
    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
