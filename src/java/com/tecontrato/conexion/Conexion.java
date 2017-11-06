package com.tecontrato.conexion;

import java.sql.*;

/**
 * Nombre      : Conexión
 * Versión     : 2.0
 * Fecha       : 21/09/2017
 * CopyRight   : Programmer Group Services S.A de C.V
 * @author     : Alexis, Kevin, Luis, María José, Geofredo
 */

public class Conexion {    
    private static Connection con;
    public static Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdperfilprofesional?user=postgres&password=");
            return con;
        } catch (Exception e) {
            return null;
        }
    } 
}