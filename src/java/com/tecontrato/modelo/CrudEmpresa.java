
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
public class CrudEmpresa extends Conexion {
    
    public int insertarEmpresa(Empresa emp) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        int resultado = 0;
        try {
            conexion = db.getConnection();
            String sql="insert into empresa(idempresa,actividad,descripcion,email,telefono,nombreempresa,foto) values(?,?,?,?,?,?,'userdefault.png')";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            
            pre.setInt(1, emp.getIdEmpresa());
            pre.setString(2, emp.getActividad());
            pre.setString(3, emp.getDescripcion());
            pre.setString(4, emp.getEmail());
            pre.setString(5, emp.getTelefono());
            pre.setString(6, emp.getNombreEmpresa());
            
           resultado = pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }finally{
            conexion.close();
        }
        return  resultado;
    }
    
    public int modificarEmpresa(Empresa emp) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
          int resultado = 0;
        try {
            conexion = db.getConnection();
            String sql="update empresa set actividad=?,descripcion=?,email=?,telefono=?,nombreempresa=? where idempresa=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            
            pre.setString(1, emp.getActividad());
            pre.setString(2, emp.getDescripcion());
            pre.setString(3, emp.getEmail());
            pre.setString(4, emp.getTelefono());
            pre.setString(5, emp.getNombreEmpresa());
            pre.setInt(6, emp.getIdEmpresa());
            
            resultado = pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }finally{
            conexion.close();
        }
        return resultado;
    }
    
 
    public int eliminarEmpresa(Empresa emp) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        int resultado = 0;
        try {
            conexion = db.getConnection();
            String sql="delete from empresa where idempresa=?;delete from usuario where idusuario=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            
            pre.setInt(1, emp.getIdEmpresa());
            pre.setInt(2, emp.getIdEmpresa());
            
            resultado = pre.executeUpdate(); 
            
        } catch (Exception e) {
            
            throw e;
        }finally{
            conexion.close();
        }
        return resultado;
    }
    
     public int modificarFoto(Empresa emp) throws Exception
    {
        int resultado = 0;
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update empresa set foto=? where idempresa=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            
            pre.setString(1, emp.getFoto());
            pre.setInt(2, emp.getIdEmpresa());

            resultado = pre.executeUpdate();  
            
        } catch (Exception e) {
            
            throw e;
        }finally{
            conexion.close();
        }
        return resultado;
    }
    
    public List<Empresa>mostrarEmpresa() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Empresa>lst= new ArrayList();
        try {
            conexion = db.getConnection();
            String sql="select * from empresa order by idempresa";
            PreparedStatement pre = conexion.prepareCall(sql);
            res = pre.executeQuery();
            while(res.next())
            {
                Empresa emp= new Empresa();
                emp.setIdEmpresa(res.getInt("idempresa"));
                emp.setActividad(res.getString("actividad"));
                emp.setDescripcion(res.getString("descripcion"));
                emp.setEmail(res.getString("email"));
                emp.setTelefono(res.getString("telefono"));
                emp.setNombreEmpresa(res.getString("nombreempresa"));
                emp.setFoto(res.getString("foto"));
 
                lst.add(emp);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }  
    
    public int ultimoId() throws Exception{
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        try{
            conexion = db.getConnection();
            String sql="SELECT MAX(idUsuario) AS id FROM usuario";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next()){
                int id = res.getInt("id");
                return id;
            }
        }catch(Exception e){
            
        }
        return 0;
    }
    
    public List<Empresa>empresas() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Empresa>lst= new ArrayList();
        try {
            conexion = db.getConnection();
            String sql="select * from empresa order by random() limit 4";
            PreparedStatement pre = conexion.prepareCall(sql);
            res = pre.executeQuery();
            while(res.next())
            {
                Empresa emp= new Empresa();
                emp.setIdEmpresa(res.getInt("idempresa"));
                emp.setActividad(res.getString("actividad"));
                emp.setDescripcion(res.getString("descripcion"));
                emp.setEmail(res.getString("email"));
                emp.setTelefono(res.getString("telefono"));
                emp.setNombreEmpresa(res.getString("nombreempresa"));
                emp.setFoto(res.getString("foto"));
                lst.add(emp);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    public List<Empresa>obtenerEmpresas(int id) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Empresa>lst= new ArrayList();
        try {
            conexion = db.getConnection();
            String sql="select * from empresa where idempresa=?";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, id);
            res = pre.executeQuery();
            while(res.next())
            {
                Empresa emp= new Empresa();
                emp.setIdEmpresa(res.getInt("idempresa"));
                emp.setActividad(res.getString("actividad"));
                emp.setDescripcion(res.getString("descripcion"));
                emp.setEmail(res.getString("email"));
                emp.setTelefono(res.getString("telefono"));
                emp.setNombreEmpresa(res.getString("nombreempresa"));
                emp.setFoto(res.getString("foto"));
                lst.add(emp);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
}
