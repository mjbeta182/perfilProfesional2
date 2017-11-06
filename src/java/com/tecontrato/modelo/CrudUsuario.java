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
public class CrudUsuario extends Conexion{
    
    public void insertarUsuario(Usuario us) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="insert into usuario(idusuario, idrol,usuario,clave) values((select max(idusuario)+1 from usuario us),?,?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, us.getRol().getIdRol());
            pre.setString(2, us.getUsuario());
            pre.setString(3, us.getClave());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void modificarUsuario(Usuario us) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
         conexion = db.getConnection();
         String sql="update usuario set idrol=?,usuario=?,clave=? where idusuario=?";
         PreparedStatement pre = conexion.prepareStatement(sql);
         pre.setInt(1, us.getRol().getIdRol());
         pre.setString(2, us.getUsuario());
         pre.setString(3, us.getClave());
         pre.setInt(4, us.getIdUsuario());
         pre.executeUpdate();   
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void eliminarUsuario(Usuario us) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="delete from usuario where idusuario=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, us.getIdUsuario());
            pre.executeUpdate(); 
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public List<Usuario>mostrarUsuario() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Usuario>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select idusuario, rol.idrol, rol.nombrerol, usuario, clave from usuario "
                    + "inner join rol on usuario.idrol=rol.idrol";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Rol rol=new Rol();
                rol.setIdRol(res.getInt("idrol"));
                rol.setNombreRol(res.getString("nombre rol"));
                
                Usuario us=new Usuario(res.getInt("idusuario"),rol,res.getString("usuario"),
                        res.getString("clave"));
                lst.add(us);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
    public List<Rol>listaRol() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Rol>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select * from rol";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Rol rol=new Rol();
                rol.setIdRol(res.getInt("idrol"));
                rol.setNombreRol(res.getString("nombre rol"));
                lst.add(rol);
            }
        } 
        catch (Exception e) 
        {
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
    
     public int autenticarUsuario(String usuario, String contrasena) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet rs;
        int resp = 0;
         try 
         {  
            conexion = db.getConnection();
            String sql="select idrol from usuario  where usuario=? and clave=?";
            PreparedStatement pst = conexion.prepareCall(sql);
            pst.setString(1, usuario);
            pst.setString(2, contrasena);
            rs = pst.executeQuery();
            while(rs.next())
            {
              resp = rs.getInt("idrol");     
            } 
        } catch (Exception e) {
             throw e;
        }
        finally
         {
            
         }
        return resp;
    }
     
    public List<Candidato>obtenerDatosC(String usuario) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet rs;
        List<Candidato>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select u.idusuario, c.idcandidato,c.nombre,c.profesion,c.iddepto,c.idgenero,c.nacionalidad,c.fechanacimiento,c.direccion,c.foto,d.iddepto,d.nombredepto,g.idgenero,g.genero from candidato c inner join departamento d on c.iddepto=d.iddepto inner join genero g on c.idgenero=g.idgenero inner join usuario u on c.idcandidato =u.idusuario  where usuario=?";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setString(1, usuario);
            rs=pre.executeQuery();
            while(rs.next())
            {
                Departamento d =  new Departamento();
                d.setIdDepto(rs.getInt("iddepto"));
                d.setNombreDepto(rs.getString("nombredepto"));
                
                Genero g = new Genero();
                g.setIdGenero(rs.getInt("idgenero"));
                g.setGenero(rs.getString("genero"));
                
                Candidato c =  new Candidato();
                c.setIdCandidato(rs.getInt("idcandidato"));
                c.setNombre(rs.getString("nombre"));
                c.setDireccion(rs.getString("direccion"));
                c.setFechaNacimiento(rs.getString("fechanacimiento"));
                c.setFoto(rs.getString("foto"));
                c.setProfesion(rs.getString("profesion"));
                c.setDepto(d);
                c.setGenero(g);
                c.setNacionalidad(rs.getString("nacionalidad"));
                lst.add(c);

            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
}
