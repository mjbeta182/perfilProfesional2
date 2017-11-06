
package com.tecontrato.modelo;

import com.tecontrato.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geofredo
 */
public class CrudArea extends Conexion {
    
    public void insertarArea(Area ar) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="insert into area(nombrearea) values(?)";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setString(1, ar.getNombreArea());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    public void modificarArea(Area ar) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update area set nombrearea=? where idarea=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            pre.setString(1, ar.getNombreArea());
            pre.setInt(2, ar.getIdArea());            
            
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
 
    public void eliminarArea(Area ar) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="delete from area where idarea=?";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, ar.getIdArea());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    
    public List<Area>mostrarArea() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Area>lst= new ArrayList();
        try {
            conexion = db.getConnection();
            String sql="select * from area";
            PreparedStatement pre = conexion.prepareCall(sql);
            res = pre.executeQuery();
            while(res.next())
            {
                Area ar= new Area();
                ar.setIdArea(res.getInt("idarea"));
                ar.setNombreArea(res.getString("nombrearea"));
                lst.add(ar);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }  
    
    
    
    
    
    
}
