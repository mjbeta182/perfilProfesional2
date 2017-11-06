
package com.tecontrato.modelo;

import com.tecontrato.conexion.Conexion;
import java.sql.*;
import java.util.*;

/**
 * Nombre      : CrudHabilidad
 * Versión     : 2.0
 * Fecha       : 21/09/2017
 * CopyRight   : Programmer Group Services S.A de C.V
 * @author     : Alexis, Kevin, Luis, María José, Geofredo
 */

public class CrudHabilidad extends Conexion {
    
     public void insertarHabilidad(Habilidad hb) throws Exception
    {   
        Conexion db = new Conexion();
        Connection conexion = null;
        try {           
            conexion = db.getConnection();
            String sql="insert into habilidad(nombrehabilidad) values(?)";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            pre.setString(1, hb.getNombreHabilidad());
            pre.executeUpdate();    
        } catch (Exception e) {
            
            throw e;
        }        
    }
    
    public void eliminarHabilidad(Habilidad hb) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try {           
            conexion = db.getConnection();
            String sql="delete from habilidad where idhabilidad=?";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            pre.setInt(1, hb.getIdHabilidad());
            pre.executeUpdate();    
        } catch (Exception e) {
            
            throw e;
        }      
    }
    
   public void modificarHabilidad(Habilidad hb) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update habilidad set nombrehabilidad=? where idhabilidad=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            pre.setString(1, hb.getNombreHabilidad());
            pre.setInt(2, hb.getIdHabilidad()); 
            pre.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Habilidad>listahabilidades() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Habilidad>lst= new ArrayList();
        try {
            conexion = db.getConnection();
            String sql="select * from habilidad order by idhabilidad";
            PreparedStatement pre = conexion.prepareCall(sql);
            res = pre.executeQuery();
            while(res.next())
            {
                Habilidad hb= new Habilidad();
                hb.setIdHabilidad(res.getInt("idhabilidad"));
                hb.setNombreHabilidad(res.getString("nombrehabilidad"));
                lst.add(hb);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }  
}
