package com.tecontrato.modelo;

import com.tecontrato.conexion.Conexion;
import com.tecontrato.utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin López
 */
public class CrudExperienciaLaboral extends Conexion{
    
   public void insertarExperienciaLaboral(ExperienciaLaboral exl) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="insert into experiencialaboral(nombreempresa,puesto,fechainicio,fechafin,idcurriculum) values(?,?,?,?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setString(1, exl.getNombreEmpresa());
            pre.setString(2, exl.getPuesto());
            pre.setDate(3,Utilidades.convertFromStrToSqlDate(exl.getFechaInicio(), "yyyy-MM-dd"));
            pre.setDate(4,Utilidades.convertFromStrToSqlDate(exl.getFechaFin(), "yyyy-MM-dd"));
            pre.setInt(5, exl.getCurriculum().getIdCurriculum());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {

            throw e;
        }
    }
    
    public void modificarExperienciaLaboral(ExperienciaLaboral exl) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="update experiencialaboral set nombreempresa=?,puesto=?,fechainicio=?,fechafin=? where idcurriculum=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setString(1, exl.getNombreEmpresa());
            pre.setString(2, exl.getPuesto());
            pre.setString(3, exl.getFechaInicio());
            pre.setString(4, exl.getFechaFin());
            pre.setInt(5, exl.getCurriculum().getIdCurriculum());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {

            throw e;
        }
    }
    
    public void eliminarExperienciaLaboral(ExperienciaLaboral exl) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="delete from experiencialaboral where idcurriculum=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, exl.getCurriculum().getIdCurriculum());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {

            throw e;
        }
    }
    
    public List<ExperienciaLaboral>mostrarExperienciaLaboral() throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<ExperienciaLaboral>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select curriculum.idcurriculum, nombreempresa, puesto, fechainicio, fechafin from experiencialaboral "
                    + "inner join curriculum on experiencialaboral.idcurriculum=curriculum.idcurriculum";
            PreparedStatement pre = conexion.prepareStatement(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Curriculum cv=new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                ExperienciaLaboral exl=new ExperienciaLaboral(cv,res.getString("nombreempresa"),res.getString("puesto"),
                                res.getString("fechainicio"),res.getString("fechafin"));
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    public List<ExperienciaLaboral>mostrarExperienciaLaboral(int IdCurriculum) throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<ExperienciaLaboral>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql=" select curriculum.idcurriculum, nombreempresa, puesto, fechainicio, fechafin from experiencialaboral inner join curriculum on experiencialaboral.idcurriculum=curriculum.idcurriculum where curriculum.idcurriculum=? ";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, IdCurriculum);
            res=pre.executeQuery();
            while(res.next())
            {
                Curriculum cv=new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                ExperienciaLaboral exl=new ExperienciaLaboral(cv,res.getString("nombreempresa"),res.getString("puesto"),res.getString("fechainicio"),res.getString("fechafin"));
                lst.add(exl);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
}
