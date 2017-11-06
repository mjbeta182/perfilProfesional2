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
public class CrudTelefono extends Conexion{
    
    public void insertarTelefono(Telefono tel) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="insert into telefono(idcandidato,telefono) values(?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, tel.getCandidato().getIdCandidato());
            pre.setString(2, tel.getTelefono());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void modificarTelefono(Telefono tel) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="update telefono set telefono=? where telefono=? and idcandidato=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setString(1, tel.getTelefono());
            pre.setInt(2, tel.getCandidato().getIdCandidato());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void eliminarTelefono(Telefono tel) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="delete from telefono where telefono=? and idcandidato=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setString(1, tel.getTelefono());
            pre.setInt(2, tel.getCandidato().getIdCandidato());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public List<Telefono>mostrarTelefono() throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<Telefono>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select telefono.idcandidato, candidato.idcandidato, candidato.nombre, telefono from telefono " 
                    +"inner join candidato on telefono.idcandidato=candidato.idcandidato";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Candidato can=new Candidato();
                can.setIdCandidato(res.getInt("idcandidato"));
                can.setNombre(res.getString("nombre"));
                
                Telefono tel=new Telefono(can,res.getString("telefono"));
                lst.add(tel);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
    public List<Telefono> telefonos(int id) throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<Telefono>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select telefono.idcandidato, candidato.idcandidato, candidato.nombre, telefono from telefono " 
                    +"inner join candidato on telefono.idcandidato=candidato.idcandidato where candidato.idcandidato=?";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, id);
            res=pre.executeQuery();
            while(res.next())
            {
                Candidato can=new Candidato();
                can.setIdCandidato(res.getInt("idcandidato"));
                can.setNombre(res.getString("nombre"));
                
                Telefono tel=new Telefono(can,res.getString("telefono"));
                lst.add(tel);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
    
    
    
}
