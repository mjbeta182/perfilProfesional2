
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
public class CrudCargo extends Conexion{
    
    
    public void insertarCargo(Cargo car) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="insert into cargo(nombrecargo) values(?)";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setString(1, car.getNombreCargo());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    public void modificarCargo(Cargo car) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update cargo set nombrecargo=? where idcargo=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            pre.setString(1, car.getNombreCargo());
            pre.setInt(2, car.getIdCargo());            
            
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
 
    public void eliminarCargo(Cargo car) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="delete from cargo where idcargo=?";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, car.getIdCargo());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    
    public List<Cargo>mostrarCargo() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Cargo>lst= new ArrayList();
        try {
            conexion = db.getConnection();
            String sql="select * from cargo order by idcargo";
            PreparedStatement pre = conexion.prepareCall(sql);
            res = pre.executeQuery();
            while(res.next())
            {
                Cargo car= new Cargo();
                car.setIdCargo(res.getInt("idcargo"));
                car.setNombreCargo(res.getString("nombrecargo"));
                lst.add(car);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }  
    
       
}
