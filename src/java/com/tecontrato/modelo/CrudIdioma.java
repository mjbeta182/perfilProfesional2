package com.tecontrato.modelo;

import com.tecontrato.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin López
 */
public class CrudIdioma extends Conexion{
    
    public void insertarIdioma(Idioma idi) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="insert into idioma(ididioma,nombreidioma) values((select max(ididioma)+1 from idioma idi),?)";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            
            pre.setString(1, idi.getNombreIdioma());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    public void modificarIdioma(Idioma idi) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="update idioma set nombreidioma=? where ididioma=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setString(1, idi.getNombreIdioma());
            pre.setInt(2, idi.getIdIdioma());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void eliminarIdioma(Idioma idi) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="delete from idioma where ididioma=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, idi.getIdIdioma());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public List<Idioma>mostrarIdioma() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Idioma>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select * from idioma order by ididioma";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Idioma idi=new Idioma();
                idi.setIdIdioma(res.getInt("ididioma"));
                idi.setNombreIdioma(res.getString("nombreidioma"));
                lst.add(idi);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
}
