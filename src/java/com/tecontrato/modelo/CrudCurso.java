
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
public class CrudCurso extends Conexion {
    
   public void insertarCurso(Curso cur) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="insert into curso(titulo,institucion,aniofinalizacion,idcurriculum) values(?,?,?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setString(1, cur.getTitulo());
            pre.setString(2, cur.getInstitucion());
            pre.setInt(3, cur.getAnioFinalizacion());
            pre.setInt(4, cur.getCurriculum().getIdCurriculum());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    public void modificarCurso(Curso cur) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update curso set titulo=?,institucion=?,aniofinalizacion=? where idcurriculum=? and titulo=? ";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            
            pre.setString(1, cur.getTitulo());
            pre.setString(2, cur.getInstitucion());
            pre.setInt(3, cur.getAnioFinalizacion());
            pre.setInt(4, cur.getCurriculum().getIdCurriculum());
            
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
 
    public void eliminarCurso(Curso cur) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="delete from curso where idcurriculum=?";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, cur.getCurriculum().getIdCurriculum());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    
     public List<Curso>mostrarCurso() throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<Curso>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select titulo, institucion,aniofinalizacion, curriculum.idcurriculum from curso"
                       +"inner join curriculum on curso.idcurriculum=curriculum.idcurriculum";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {

                Curriculum cv = new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                Curso cur = new Curso(res.getString("titulo"),res.getString("institucion"),res.getInt("aniofinalizacion"),cv);
                lst.add(cur);
                
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    public List<Curso>mostrarCurso(int idCurriculum) throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<Curso>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select titulo, institucion,aniofinalizacion, curriculum.idcurriculum from curso"
                       +" inner join curriculum on curso.idcurriculum=curriculum.idcurriculum "
                       + " where curriculum.idcurriculum=?";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, idCurriculum);
            res=pre.executeQuery();
            while(res.next())
            {
                Curriculum cv = new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                Curso cur = new Curso(res.getString("titulo"),res.getString("institucion"),res.getInt("aniofinalizacion"),cv);
                lst.add(cur);   
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
}
