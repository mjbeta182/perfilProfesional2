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
public class CrudTipoContratacion extends Conexion{
    
    public void insertarTipoContratacion(TipoContratacion tp) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="insert into tipocontratacion(nombrecontratacion) values(?)";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setString(1, tp.getNombreContratacion());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void modificarTipoContratacion(TipoContratacion tp) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="update tipocontratacion set nombrecontratacion=? where idtipocontratacion=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setString(1, tp.getNombreContratacion());
            pre.setInt(2, tp.getIdTipoContratacion());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void eliminarTipoContratacion(TipoContratacion tp) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="delete from tipocontratacion where idtipocontratacion=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, tp.getIdTipoContratacion());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public List<TipoContratacion>mostrarTipoContratacion() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<TipoContratacion>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select * from tipocontratacion order by idtipocontratacion";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                TipoContratacion tp=new TipoContratacion();
                tp.setIdTipoContratacion(res.getInt("idtipocontratacion"));
                tp.setNombreContratacion(res.getString("nombrecontratacion"));
                lst.add(tp);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
}
