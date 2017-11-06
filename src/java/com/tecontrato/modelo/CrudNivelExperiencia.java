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
public class CrudNivelExperiencia extends Conexion{
    
        public void insertarNivelExperiencia(NivelExperiencia ne) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="insert into nivelexperiencia(nombrenivelexperiencia) values(?)";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setString(1, ne.getNombreNivelExperiencia());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void modificarNivelExperiencia(Curriculum cv) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="update  curriculum set idnivelexperiencia=? where idcurriculum=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, cv.getNivelExperiencia().getIdNivelExperiencia());
            pre.setInt(2, cv.getIdCurriculum());
            
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void eliminarNivelExperiencia(NivelExperiencia ne) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="delete from nivelexperiencia where idnivelexperiencia=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, ne.getIdNivelExperiencia());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public List<NivelExperiencia>mostrarNivelExperiencia() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<NivelExperiencia>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select * from nivelexperiencia order by idnivelexperiencia";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                NivelExperiencia ne=new NivelExperiencia();
                ne.setIdNivelExperiencia(res.getInt("idnivelexperiencia"));
                ne.setNombreNivelExperiencia(res.getString("nombrenivelexperiencia"));
                lst.add(ne);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
}
