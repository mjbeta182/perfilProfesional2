
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
public class CrudBolsaTrabajo extends Conexion {
    
    public void insertarBolsaTrabajo(BolsaTrabajo bt) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="insert into bolsatrabajo(idcurriculum) values(?)";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, bt.getCurriculum().getIdCurriculum());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    public void modificarBolsaTrabajo(BolsaTrabajo bt) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update bolsatrabajo set idcurriculum=? where idempresa=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            
            pre.setInt(1, bt.getCurriculum().getIdCurriculum());
            pre.setInt(2, bt.getEmpresa().getIdEmpresa());            
            
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
 
    public void eliminarBolsaTrabajo(BolsaTrabajo bt) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="delete from bolsatrabajo where idempresa=?";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, bt.getEmpresa().getIdEmpresa());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    
     public List<BolsaTrabajo>mostrarBolsaTrabajo() throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<BolsaTrabajo>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select empresa.idempresa, empresa.nombreempresa, curriculum.idcurriculum from bolsatrabajo"
                       +"inner join empresa on bolsatrabajo.idempresa=empresa.idempresa"
                       +"inner join curriculum on bolsatrabajo.idcurriculum=curriculum.idcurriculum";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                
                
                Empresa emp=new Empresa();
                emp.setIdEmpresa(res.getInt("idempresa"));             
                emp.setNombreEmpresa(res.getString("nombreempresa"));
                
                Curriculum cv = new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                BolsaTrabajo bt = new BolsaTrabajo( emp,cv );
                lst.add(bt);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
    
}
