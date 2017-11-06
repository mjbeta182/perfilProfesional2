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
    
    public List<Oferta>ofertaCriterio(String criterio) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Oferta>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select idoferta, nombre, vacantes, oferta.descripcion,  edadmin, edadmax, " +
"                    area.idarea, area.nombrearea, " +
"                    cargo.idcargo, cargo.nombrecargo, " +
"                    nivelexperiencia.idnivelexperiencia, nivelexperiencia.nombrenivelexperiencia, " +
"                    tipocontratacion.idtipocontratacion, tipocontratacion.nombrecontratacion, " +
"                   departamento.iddepto, departamento.nombredepto, " +
"                    empresa.idempresa, empresa.actividad, empresa.descripcion, empresa.email, empresa.telefono, empresa.nombreempresa " +
"                    from oferta " +
"                    inner join area on oferta.idarea=area.idarea inner join cargo on oferta.idcargo=cargo.idcargo " +
"                    inner join nivelexperiencia on oferta.idnivelexperiencia=nivelexperiencia.idnivelexperiencia " +
"                    inner join departamento on oferta.iddepto=departamento.iddepto " +
"                    inner join empresa on oferta.idempresa=empresa.idempresa " +
"                    inner join tipocontratacion on oferta.idtipocontratacion = tipocontratacion.idtipocontratacion " +
"                    where upper (nombre) like upper('%"+criterio+"%') or upper(oferta.descripcion) like upper('%"+criterio+"%') or upper(area.nombrearea) like upper('%"+criterio+"%') or " +
"                    upper(cargo.nombrecargo) like upper('%"+criterio+"%') or upper(nivelexperiencia.nombrenivelexperiencia) like upper('%"+criterio+"%') or upper(tipocontratacion.nombrecontratacion) like upper('%"+criterio+"%') or "+
"                    upper(departamento.nombredepto) like upper('%"+criterio+"%') or upper(empresa.actividad) like upper('%"+criterio+"%') or upper(empresa.nombreempresa) like upper('%"+criterio+"%') " +
"                    order by idoferta ";
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
    
    public List<Candidato> seguirCriterio(String criterio) throws Exception
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
                       +"inner join genero on candidato.idgenero=genero.idgenero where upper (candidato.nombre) like upper('%"+criterio+"%') or upper(candidato.profesion) like upper('%"+criterio+"%') order by candidato.idcandidato  ";
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
    
    
    public List<Oferta>ofertaEmpresa(int codigo) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Oferta>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select idoferta, nombre, vacantes, oferta.descripcion,  edadmin, edadmax, " +
"                    area.idarea, area.nombrearea, " +
"                    cargo.idcargo, cargo.nombrecargo, " +
"                    nivelexperiencia.idnivelexperiencia, nivelexperiencia.nombrenivelexperiencia, " +
"                    tipocontratacion.idtipocontratacion, tipocontratacion.nombrecontratacion, " +
"                   departamento.iddepto, departamento.nombredepto, " +
"                    empresa.idempresa, empresa.actividad, empresa.descripcion, empresa.email, empresa.telefono, empresa.nombreempresa " +
"                    from oferta " +
"                    inner join area on oferta.idarea=area.idarea inner join cargo on oferta.idcargo=cargo.idcargo " +
"                    inner join nivelexperiencia on oferta.idnivelexperiencia=nivelexperiencia.idnivelexperiencia " +
"                    inner join departamento on oferta.iddepto=departamento.iddepto " +
"                    inner join empresa on oferta.idempresa=empresa.idempresa " +
"                    inner join tipocontratacion on oferta.idtipocontratacion = tipocontratacion.idtipocontratacion " +
"                    where empresa.idempresa = "+codigo+" " + 
"                    order by idoferta ";
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
    
    public List<Oferta>aplicantes(int codigo) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Oferta>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select idoferta, nombre, vacantes, oferta.descripcion,  edadmin, edadmax, " +
"                    area.idarea, area.nombrearea, " +
"                    cargo.idcargo, cargo.nombrecargo, " +
"                    nivelexperiencia.idnivelexperiencia, nivelexperiencia.nombrenivelexperiencia, " +
"                    tipocontratacion.idtipocontratacion, tipocontratacion.nombrecontratacion, " +
"                   departamento.iddepto, departamento.nombredepto, " +
"                    empresa.idempresa, empresa.actividad, empresa.descripcion, empresa.email, empresa.telefono, empresa.nombreempresa " +
"                    from oferta " +
"                    inner join area on oferta.idarea=area.idarea inner join cargo on oferta.idcargo=cargo.idcargo " +
"                    inner join nivelexperiencia on oferta.idnivelexperiencia=nivelexperiencia.idnivelexperiencia " +
"                    inner join departamento on oferta.iddepto=departamento.iddepto " +
"                    inner join empresa on oferta.idempresa=empresa.idempresa " +
"                    inner join tipocontratacion on oferta.idtipocontratacion = tipocontratacion.idtipocontratacion " +
"                    where empresa.idempresa = "+codigo+" " + 
"                    order by idoferta ";
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
    
    public List<Oferta>ofertadetalle(String codigo) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Oferta>lst= new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select idoferta, nombre, vacantes, oferta.descripcion,  edadmin, edadmax, " +
"                    area.idarea, area.nombrearea, " +
"                    cargo.idcargo, cargo.nombrecargo, " +
"                    nivelexperiencia.idnivelexperiencia, nivelexperiencia.nombrenivelexperiencia, " +
"                    tipocontratacion.idtipocontratacion, tipocontratacion.nombrecontratacion, " +
"                   departamento.iddepto, departamento.nombredepto, " +
"                    empresa.idempresa, empresa.actividad, empresa.descripcion, empresa.email, empresa.telefono, empresa.nombreempresa " +
"                    from oferta " +
"                    inner join area on oferta.idarea=area.idarea inner join cargo on oferta.idcargo=cargo.idcargo " +
"                    inner join nivelexperiencia on oferta.idnivelexperiencia=nivelexperiencia.idnivelexperiencia " +
"                    inner join departamento on oferta.iddepto=departamento.iddepto " +
"                    inner join empresa on oferta.idempresa=empresa.idempresa " +
"                    inner join tipocontratacion on oferta.idtipocontratacion = tipocontratacion.idtipocontratacion " +
"                    where oferta.idoferta = "+codigo+" " + 
"                    order by idoferta ";
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
   
    public List<Candidato> seguirdetalle(String codigo) throws Exception
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
                       +"inner join genero on candidato.idgenero=genero.idgenero where candidato.idcandidato="+codigo;
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
    
    
    
    
    
    
    public int obtenerrol(int codigo) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet rs;
        int resp = 0;
         try 
         {  
            conexion = db.getConnection();
            String sql="select idrol from usuario where idusuario="+codigo+" ";
            PreparedStatement pst = conexion.prepareCall(sql);
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
    
    public int obtenerid(String usuario) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet rs;
        int resp = 0;
         try 
         {  
            conexion = db.getConnection();
            String sql="select idusuario from usuario where usuario= ?";
            PreparedStatement pst = conexion.prepareCall(sql);
            pst.setString(1, usuario);
            rs = pst.executeQuery();
            while(rs.next())
            {
              resp = rs.getInt("idusuario");     
            } 
        } catch (Exception e) {
             throw e;
        }
        finally
         {
            
         }
        return resp;
    }
    
    public int verRelacion(int idusuario, String idoferta) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet rs;
        int resp = 0;
         try 
         {  
            conexion = db.getConnection();
            String sql="select count(idcandidato) as resp from aplicantesoferta where idcandidato="+idusuario+" and idoferta="+idoferta;
            PreparedStatement pst = conexion.prepareCall(sql);
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
            
         }
        return resp;
    }
    
    public void insertarInscripcion(String idoferta, String idusuario) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="INSERT INTO public.aplicantesoferta( " +
                       "idoferta, idcandidato) " +
                       "VALUES ("+idoferta+","+idusuario+");";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void eliminarInscripcion(String idoferta, String idusuario) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="DELETE FROM public.aplicantesoferta " +
                       "WHERE idcandidato="+idusuario+" and idoferta="+idoferta;
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
        public void insertarSeguir(String idseguir, String idusuario) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="INSERT INTO seguircandidato( " +
                       "idcandidatofollower, idcandidatoseguir) " +
                       "VALUES ("+idusuario+","+idseguir+");";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void eliminarSeguir(String idseguir, String idusuario) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="DELETE FROM seguircandidato " +
                       "WHERE idcandidatofollower="+idusuario+" and idcandidatoseguir="+idseguir;
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    public int verRelacionSeguir(int idusuario, String idseguir) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet rs;
        int resp = 0;
         try 
         {  
            conexion = db.getConnection();
            String sql="select count(idcandidatofollower) as resp from seguircandidato where idcandidatofollower="+idusuario+" and idcandidatoseguir="+idseguir;
            PreparedStatement pst = conexion.prepareCall(sql);
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
            
         }
        return resp;
    }
    
    
    
}