
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
public class CrudCurriculum extends Conexion {
    
    public void insertarCurriculum() throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="insert into curriculum(idcandidato,idnivelexperiencia) values((select max(idcandidato) from candidato ),1)";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    public void modificarCurriculum(Curriculum cv) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update curriculum set idcandidato=?, idnivelexperiencia=? where idcurriculum=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            
            pre.setInt(1, cv.getCandidato().getIdCandidato());
            pre.setInt(2, cv.getNivelExperiencia().getIdNivelExperiencia());
            pre.setInt(3, cv.getIdCurriculum());
            
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
 
    public void eliminarCurriculum(Curriculum cv) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="delete from curriculum where idcurriculum=?";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, cv.getIdCurriculum());
            
            pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    
     public List<Curriculum>mostrarCurriculum() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Curriculum>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select idcurriculum, candidato.idcandidato, candidato.nombre, nivelexperiencia.idnivelexperiencia, nivelexperiencia.nombrenivelexperiencia from curriculum"
                       +" inner join candidato on curriculum.idcandidato=candidato.idcandidato"
                       +" inner join nivelexperiencia on curriculum.idnivelexperiencia=nivelexperiencia.idnivelexperiencia";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                
                Candidato can=new Candidato();
                can.setIdCandidato(res.getInt("idcandidato"));
                can.setNombre(res.getString("nombre"));
                
                NivelExperiencia ne=new NivelExperiencia();
                ne.setIdNivelExperiencia(res.getInt("idnivelexperiencia"));
                ne.setNombreNivelExperiencia(res.getString("nombrenivelexperiencia"));
                
                Curriculum cv = new Curriculum( res.getInt("idcurriculum"),can,ne);
                lst.add(cv);
                
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
         public List<Curriculum>mostrarCurriculum(int idCurriculum) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Curriculum>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select idcurriculum, candidato.idcandidato, candidato.nombre, nivelexperiencia.idnivelexperiencia, nivelexperiencia.nombrenivelexperiencia from curriculum"
                       +" inner join candidato on curriculum.idcandidato=candidato.idcandidato"
                       +" inner join nivelexperiencia on curriculum.idnivelexperiencia=nivelexperiencia.idnivelexperiencia where curriculum.idcurriculum=?";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, idCurriculum);
            res=pre.executeQuery();
            while(res.next())
            {
                
                Candidato can=new Candidato();
                can.setIdCandidato(res.getInt("idcandidato"));
                can.setNombre(res.getString("nombre"));
                
                NivelExperiencia ne=new NivelExperiencia();
                ne.setIdNivelExperiencia(res.getInt("idnivelexperiencia"));
                ne.setNombreNivelExperiencia(res.getString("nombrenivelexperiencia"));
                
                Curriculum cv = new Curriculum( res.getInt("idcurriculum"),can,ne);
                lst.add(cv);
                
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    public int ultimoId(int idc) throws Exception{
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        int id=0;
        try{
            conexion = db.getConnection();
            String sql="select idcurriculum from curriculum where idcandidato=? ";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, idc);
            res = pre.executeQuery();
            while(res.next()){
             id = res.getInt("idcurriculum"); 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }
}
