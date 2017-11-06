
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
public class CrudCvCargo extends Conexion {
    
    public void insertarCvCargo(CvCargo cvc) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="insert into cvcargo(idcargo) values(?)";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, cvc.getCargo().getIdCargo());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    public void modificarCvCargo(CvCargo cvc) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update cvcargo set idcargo=? where idcurriculum=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            
            pre.setInt(1, cvc.getCargo().getIdCargo());
            pre.setInt(2, cvc.getCurriculum().getIdCurriculum());
      
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
 
    public void eliminarCvCargo(CvCargo cvc) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="delete from cvcargo where idcurriculum=?";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, cvc.getCurriculum().getIdCurriculum());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    
     public List<CvCargo>mostrarCvCargo() throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<CvCargo>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select curriculum.idcurriculum, cargo.idcargo, cargo.nombrecargo from cvcargo"
                      +" inner join curriculum on cvcargo.idcurriculum=curriculum.idcurriculum"
                      +" inner join cargo on cvcargo.idcargo=cargo.idcargo";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Curriculum cv = new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                Cargo car = new Cargo();
                car.setIdCargo(res.getInt("idcargo"));
                car.setNombreCargo(res.getString("nombrecargo"));  
                
                
                CvCargo cvc = new CvCargo(cv,car);
                lst.add(cvc);
                
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
    
}
