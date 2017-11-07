
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
 * @author Geofredo
 */
public class CrudCandidato extends Conexion {
    
    public int insertarCandidato(Candidato cd) throws Exception
    {
        
        Conexion db = new Conexion();
        Connection conexion = null;
        int resultado = 0;
        try {
            conexion = db.getConnection();
            String sql="insert into candidato(iddepto, idgenero, nombre, nacionalidad, fechanacimiento, direccion, foto, idcandidato,profesion) values(?,?,?,?,?,?,'userdefault.png',?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);           
            pre.setInt(1, cd.getDepto().getIdDepto());
            pre.setInt(2, cd.getGenero().getIdGenero());
            pre.setString(3, cd.getNombre());
            pre.setString(4, cd.getNacionalidad());
            pre.setDate(5, Utilidades.convertFromStrToSqlDate(cd.getFechaNacimiento(), "yyyy-MM-dd"));
            pre.setString(6, cd.getDireccion());    
            pre.setInt(7, cd.getIdCandidato());
            pre.setString(8, cd.getProfesion());
            resultado = pre.executeUpdate(); 
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally{
            conexion.close();
        }
        return resultado;
    }
    
    public int modificarCandidato(Candidato cd) throws Exception
    {
        int resultado = 0;
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update candidato set iddepto=?, idgenero=?, nombre=?, nacionalidad=?, fechanacimiento=?, direccion=?, profesion=?  where idcandidato=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            
            pre.setInt(1, cd.getDepto().getIdDepto());
            pre.setInt(2, cd.getGenero().getIdGenero());
            pre.setString(3, cd.getNombre());
            pre.setString(4, cd.getNacionalidad());
            pre.setDate(5, Utilidades.convertFromStrToSqlDate(cd.getFechaNacimiento(), "yyyy-MM-dd"));
            pre.setString(6, cd.getDireccion());
            pre.setString(7, cd.getProfesion());
            pre.setInt(8, cd.getIdCandidato());

            resultado = pre.executeUpdate();  
            
        } catch (Exception e) {
            
            throw e;
        }finally{
            conexion.close();
        }
        return resultado;
    }
    
    public int modificarFoto(Candidato cd) throws Exception
    {
        int resultado = 0;
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update candidato set foto=? where idcandidato=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            
            pre.setString(1, cd.getFoto());
            pre.setInt(2, cd.getIdCandidato());

            resultado = pre.executeUpdate();  
            
        } catch (Exception e) {
            
            throw e;
        }finally{
            conexion.close();
        }
        return resultado;
    }
    
    public int eliminarCandidato(Candidato cd) throws Exception
    {
        int resultado = 0;
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="delete from aplicantesoferta where idcandidato=?;delete from curriculum where idcandidato=?;delete from candidato where idcandidato=?;delete from usuario where idusuario=?;";
            PreparedStatement pre = conexion.prepareStatement(sql);  
            pre.setInt(1, cd.getIdCandidato());  
            pre.setInt(2, cd.getIdCandidato());
            pre.setInt(3, cd.getIdCandidato());
            pre.setInt(4, cd.getIdCandidato());
            
            resultado = pre.executeUpdate();  
            
        } catch (Exception e) {
            
            throw e;
        }finally{
            conexion.close();
        }
        return resultado;
    }
    
    
    public List<Candidato>mostrarCandidato() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Candidato>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select candidato.idcandidato,candidato.profesion, departamento.iddepto, departamento.nombredepto, genero.idgenero, genero.genero, nombre, nacionalidad, fechanacimiento,direccion, foto from candidato  "
                       +"inner join departamento on candidato.iddepto=departamento.iddepto "
                       +"inner join genero on candidato.idgenero=genero.idgenero order by candidato.idcandidato";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                
                Departamento depto=new Departamento();
                depto.setIdDepto(res.getInt("iddepto"));
                depto.setNombreDepto(res.getString("nombredepto"));
                
                Genero gen = new Genero();
                gen.setIdGenero(res.getInt("idgenero"));
                gen.setGenero(res.getString("genero"));
                
                Candidato can = new Candidato( res.getInt("idcandidato"),depto,gen, res.getString("nombre"),res.getString("nacionalidad"),Utilidades.invertirFechas(res.getString("fechanacimiento"),"yyyy-mm-dd","dd-mm-yyyy"),res.getString("direccion"), res.getString("foto"),res.getString("profesion"));
                lst.add(can);   
                
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
    
    public List<Departamento>listaDepto() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Departamento>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select * from departamento";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {               
                Departamento depto=new Departamento();
                depto.setIdDepto(res.getInt("iddepto"));
                depto.setNombreDepto(res.getString("nombredepto"));

                lst.add(depto);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
    public List<Genero>listaGenero() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Genero>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select * from genero";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {               
                Genero ge=new Genero();
                ge.setIdGenero(res.getInt("idgenero"));
                ge.setGenero(res.getString("genero"));
                lst.add(ge);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
    public List<Candidato> candidatos(int id) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Candidato>lst= new ArrayList();
        try {
            conexion = db.getConnection();
            String sql="select * from candidato where idcandidato!=?  order by random() limit 4";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, id);
            res = pre.executeQuery();
            while(res.next())
            {
                Candidato can = new Candidato();
                can.setNombre(res.getString("nombre"));
                can.setFoto(res.getString("foto"));
                can.setProfesion(res.getString("profesion"));
                lst.add(can);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    public int edad(int id) throws Exception
    {
        ResultSet res;
        int edad = 0;
        Conexion db = new Conexion();
        Connection conexion = null; 
        try {
            conexion = db.getConnection();
            String sql="select date_part('year',fechanacimiento) as edad from candidato where idcandidato=?";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, id);
            res = pre.executeQuery();
            while(res.next())
            {
                edad = 2017-(res.getInt("edad"));    
            }
        } catch (Exception e) {
            throw e;
        }
        return edad;
    }
  public int contarfollows(int id) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet rs;
        int resp = 0;
         try 
         {  
            conexion = db.getConnection();
            String sql="select count(idcandidatofollower) as resp from seguircandidato  where idcandidatofollower=? ";
            PreparedStatement pst = conexion.prepareCall(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next())
            {
              resp = rs.getInt("resp");
            } 
        } catch (Exception e) {
             throw e;
        }
        finally
         {
            conexion.close();
         }
        return resp;
    }
    
  public int contarAplicaciones(int id) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet rs;
        int resp = 0;
         try 
         {  
            conexion = db.getConnection();
            String sql="select count(idoferta) as resp from aplicantesoferta  where idcandidato=? ";
            PreparedStatement pst = conexion.prepareCall(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next())
            {
              resp = rs.getInt("resp");
            } 
        } catch (Exception e) {
             throw e;
        }
        finally
         {
            conexion.close();
         }
        return resp;
    }
    
    
}
