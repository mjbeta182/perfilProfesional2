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
public class CrudOferta extends Conexion{
    
    public void insertarOferta(Oferta ofe) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="INSERT INTO public.oferta( "
                    +  "idoferta, idarea, idcargo, idnivelexperiencia, idtipocontratacion, iddepto, idempresa, nombre, vacantes, descripcion, edadmin, edadmax) " 
                    +  "VALUES ((select max(idoferta)+1 from oferta ofe), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, ofe.getArea().getIdArea());
            pre.setInt(2, ofe.getCargo().getIdCargo());
            pre.setInt(3, ofe.getNivelExperiencia().getIdNivelExperiencia());
            pre.setInt(4, ofe.getTipoContratacion().getIdTipoContratacion());
            pre.setInt(5, ofe.getDepartamento().getIdDepto());
            pre.setInt(6, ofe.getEmpresa().getIdEmpresa());
            pre.setString(7, ofe.getNombre());
            pre.setInt(8, ofe.getVacantes());
            pre.setString(9, ofe.getDescripcion());
            pre.setInt(10, ofe.getEdadMin());
            pre.setInt(11, ofe.getEdadMax());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void modificarOferta(Oferta ofe) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="UPDATE public.oferta " 
                    +  "SET idarea=?, idcargo=?, idnivelexperiencia=?, idtipocontratacion=?, iddepto=?, idempresa=?, nombre=?, vacantes=?, descripcion=?, edadmin=?, edadmax=? " 
                    +  "WHERE idoferta=?;";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, ofe.getArea().getIdArea());
            pre.setInt(2, ofe.getCargo().getIdCargo());
            pre.setInt(3, ofe.getNivelExperiencia().getIdNivelExperiencia());
            pre.setInt(4, ofe.getTipoContratacion().getIdTipoContratacion());
            pre.setInt(5, ofe.getDepartamento().getIdDepto());
            pre.setInt(6, ofe.getEmpresa().getIdEmpresa());
            pre.setString(7, ofe.getNombre());
            pre.setInt(8, ofe.getVacantes());
            pre.setString(9, ofe.getDescripcion());
            pre.setInt(10, ofe.getEdadMin());
            pre.setInt(11, ofe.getEdadMax());
            pre.setInt(12, ofe.getIdOferta());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void eliminarOferta(Oferta ofe) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="delete from oferta where idoferta=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, ofe.getIdOferta());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public List<Oferta>mostrarOferta() throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Oferta>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select idoferta, nombre, vacantes, oferta.descripcion,  edadmin, edadmax, " 
                    +"area.idarea, area.nombrearea, " 
                    +"cargo.idcargo, cargo.nombrecargo, " 
                    +"nivelexperiencia.idnivelexperiencia, nivelexperiencia.nombrenivelexperiencia, " 
                    +"tipocontratacion.idtipocontratacion, tipocontratacion.nombrecontratacion, " 
                    +"departamento.iddepto, departamento.nombredepto, " 
                    +"empresa.idempresa, empresa.actividad, empresa.descripcion, empresa.email, empresa.telefono, empresa.nombreempresa " 
                    +"from oferta " 
                    +"inner join area on oferta.idarea=area.idarea inner join cargo on oferta.idcargo=cargo.idcargo " 
                    +"inner join nivelexperiencia on oferta.idnivelexperiencia=nivelexperiencia.idnivelexperiencia " 
                    +"inner join departamento on oferta.iddepto=departamento.iddepto " 
                    +"inner join empresa on oferta.idempresa=empresa.idempresa " 
                    +"inner join tipocontratacion on oferta.idtipocontratacion = tipocontratacion.idtipocontratacion "
                    + "order by idoferta asc";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Area ar=new Area();
                ar.setIdArea(res.getInt("idarea"));
                ar.setNombreArea(res.getString("nombrearea"));
                
                Cargo ca=new Cargo();
                ca.setIdCargo(res.getInt("idcargo"));
                ca.setNombreCargo(res.getString("nombrecargo"));
                
                NivelExperiencia ne=new NivelExperiencia();
                ne.setIdNivelExperiencia(res.getInt("idnivelexperiencia"));
                ne.setNombreNivelExperiencia(res.getString("nombrenivelexperiencia"));
                
                TipoContratacion tc=new TipoContratacion();
                tc.setIdTipoContratacion(res.getInt("idtipocontratacion"));
                tc.setNombreContratacion(res.getString("nombrecontratacion"));
                
                Departamento depto=new Departamento();
                depto.setIdDepto(res.getInt("iddepto"));
                depto.setNombreDepto(res.getString("nombredepto"));
                
                Empresa emp=new Empresa();
                emp.setIdEmpresa(res.getInt("idempresa"));
                emp.setActividad(res.getString("actividad"));
                emp.setDescripcion(res.getString("descripcion"));
                emp.setEmail(res.getString("email"));
                emp.setTelefono(res.getString("telefono"));
                emp.setNombreEmpresa(res.getString("nombreempresa"));
                
                Oferta ofe=new Oferta(res.getInt("idoferta"),ar,ca,ne,tc,depto,emp,res.getString("nombre"),res.getInt("vacantes"),
                                    res.getString("descripcion"),res.getInt("edadmin"),res.getInt("edadmax"));
                lst.add(ofe);
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
}