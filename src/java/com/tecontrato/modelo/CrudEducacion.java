
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
public class CrudEducacion extends Conexion{
    
    public void insertarEducacion(Educacion edu) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="insert into educacion(idcurriculum,institucion,especialidad,aniofinalizacion) values(?,?,?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, edu.getCurriculum().getIdCurriculum());
            pre.setString(2, edu.getInstitucion());
            pre.setString(3, edu.getEspecialidad());
            pre.setInt(4, edu.getAnioFinalizacion());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    public void modificarEducacion(Educacion edu) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update educacion set institucion=?,especialidad=?,aniofinalizacion=? where idcurriculum=? and especialidad=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            
            
            pre.setString(1, edu.getInstitucion());
            pre.setString(2, edu.getEspecialidad());
            pre.setInt(3, edu.getAnioFinalizacion());  
            pre.setInt(4, edu.getCurriculum().getIdCurriculum());
            
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
 
    public void eliminarEducacion(Educacion edu) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="delete from educacion where idcurriculum=? and especialidad=?";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, edu.getCurriculum().getIdCurriculum());
            pre.setString(2, edu.getEspecialidad());
                 
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    
    public List<Educacion>mostrarEducacion() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Educacion>lst= new ArrayList();
        try {
            conexion = db.getConnection();
            String sql="select * from educacion order by idcurriculum";
            PreparedStatement pre = conexion.prepareCall(sql);
            res = pre.executeQuery();
            while(res.next())
            {     
                Curriculum cv = new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                Educacion edu = new Educacion( cv, res.getString("institucion"),res.getString("especialidad"),res.getInt("aniofinalizacion"));
                lst.add(edu);
                
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }  
    
    public List<Educacion>mostrarEducacion(int idCurriculum) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Educacion>lst= new ArrayList();
        try {
            conexion = db.getConnection();
            String sql="select * from educacion where idcurriculum=?";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, idCurriculum);
            res = pre.executeQuery();
            while(res.next())
            {

                Curriculum cv = new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                Educacion edu = new Educacion( cv, res.getString("institucion"),res.getString("especialidad"),res.getInt("aniofinalizacion"));
                lst.add(edu);
                
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }  
  
}
