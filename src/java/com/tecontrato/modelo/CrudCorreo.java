
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
public class CrudCorreo extends Conexion {
    
    public void insertarCorreo(Correo co) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="insert into correo(correo,idcandidato) values(?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setString(1, co.getCorreo());
            pre.setInt(2, co.getCandidato().getIdCandidato());
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    public void modificarCorreo(Correo co) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update correo set correo=? where idcandidato=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            
            pre.setString(1, co.getCorreo());
            pre.setInt(2, co.getCandidato().getIdCandidato());
      
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
 
    public void eliminarCorreo(Correo co) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="delete from correo where idcandidato=? and correo=?";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, co.getCandidato().getIdCandidato());
            pre.setString(2, co.getCorreo());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    
     public List<Correo>mostrarCorreo() throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<Correo>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select candidato.idcandidato, candidato.nombre, correo from correo " 
                    +"inner join candidato on correo.idcandidato=candidato.idcandidato";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Candidato can=new Candidato();
                can.setIdCandidato(res.getInt("idcandidato"));
                can.setNombre(res.getString("nombre"));
                
                Correo co=new Correo(can,res.getString("correo"));
                lst.add(co);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
    public List<Correo>correos(int id) throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<Correo>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select candidato.idcandidato, candidato.nombre, correo from correo " 
                    +"inner join candidato on correo.idcandidato=candidato.idcandidato where candidato.idcandidato=?";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, id);
            res=pre.executeQuery();
            while(res.next())
            {
                Candidato can=new Candidato();
                can.setIdCandidato(res.getInt("idcandidato"));
                can.setNombre(res.getString("nombre"));
                
                Correo co=new Correo(can,res.getString("correo"));
                lst.add(co);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
}
