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
public class CrudCvHabilidad extends Conexion{
    
    public void insertarCvHabilidad(CvHabilidad cvh) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="insert into cvhabilidad(idcurriculum,idhabilidad,idnivel,iddetallehabilidad) values(?,?,?,(select max(iddetallehabilidad)+1 from cvhabilidad ))";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, cvh.getCurriculum().getIdCurriculum());
            pre.setInt(2, cvh.getHabilidad().getIdHabilidad());
            pre.setInt(3, cvh.getNivel().getIdNivel());

            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void modificarCvHabilidad(CvHabilidad cvh) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="update cvhabilidad set idnivel=? where idcurriculum=? and idhabilidad=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, cvh.getNivel().getIdNivel());
            pre.setInt(2, cvh.getCurriculum().getIdCurriculum());
            pre.setInt(3, cvh.getHabilidad().getIdHabilidad());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void eliminarCvHabilidad(CvHabilidad cvh) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="delete from cvhabilidad where idcurriculum=? and iddetallehabilidad=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, cvh.getCurriculum().getIdCurriculum());
            pre.setInt(2, cvh.getValoracion() );
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public List<CvHabilidad>mostrarCvHabilidad() throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<CvHabilidad>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select curriculum.idcurriculum, habilidad.idhabilidad, habilidad.nombrehabilidad, "
                    + "iddetallehabilidad, nivel.idnivel, nivel.nivel from cvhabilidad "
                    + "inner join curriculum on cvhabilidad.idcurriculum=curriculum.idcurriculum "
                    + "inner join habilidad on cvhabilidad.idhabilidad=habilidad.idhabilidad "
                    + "inner join nivel on cvhabilidad.idnivel=nivel.idnivel";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Curriculum cv=new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                Habilidad ha=new Habilidad();
                ha.setIdHabilidad(res.getInt("idhabilidad"));
                ha.setNombreHabilidad(res.getString("nombrehabilidad"));
                
                Nivel ni=new Nivel();
                ni.setIdNivel(res.getInt("idnivel"));
                ni.setNivel(res.getString("nivel"));
                
                CvHabilidad cvh=new CvHabilidad(cv,ha,res.getInt("iddetallehabilidad"),ni);
                lst.add(cvh);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    public List<CvHabilidad>mostrarCvHabilidad(int idCurriculum) throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<CvHabilidad>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select curriculum.idcurriculum, habilidad.idhabilidad, habilidad.nombrehabilidad, "
                    + "iddetallehabilidad, nivel.idnivel, nivel.nivel from cvhabilidad "
                    + "inner join curriculum on cvhabilidad.idcurriculum=curriculum.idcurriculum "
                    + "inner join habilidad on cvhabilidad.idhabilidad=habilidad.idhabilidad "
                    + "inner join nivel on cvhabilidad.idnivel=nivel.idnivel where curriculum.idcurriculum=?";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, idCurriculum);
            res=pre.executeQuery();
            while(res.next())
            {
                Curriculum cv=new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                Habilidad ha=new Habilidad();
                ha.setIdHabilidad(res.getInt("idhabilidad"));
                ha.setNombreHabilidad(res.getString("nombrehabilidad"));
                
                Nivel ni=new Nivel();
                ni.setIdNivel(res.getInt("idnivel"));
                ni.setNivel(res.getString("nivel"));
                
                CvHabilidad cvh=new CvHabilidad(cv,ha,res.getInt("iddetallehabilidad"),ni);
                lst.add(cvh);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
   
    
    
    public List<Nivel>listaNivel() throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<Nivel>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select *from nivel";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Nivel ni=new Nivel();
                ni.setIdNivel(res.getInt("idnivel"));
                ni.setNivel(res.getString("nivel"));

                lst.add(ni);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
    public List<CvHabilidad>listaCvhId(String codigo) throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<CvHabilidad>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="SELECT idcurriculum, cvh.idhabilidad, nombrehabilidad, cvh.idnivel, nivel, iddetallehabilidad " +
                        "FROM cvhabilidad cvh " +
                        "inner join habilidad h on cvh.idhabilidad = h.idhabilidad  " +
                        "inner join nivel n on cvh.idnivel = n.idnivel " +
                        "where cvh.idcurriculum="+codigo;
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Curriculum cv=new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                Habilidad ha=new Habilidad();
                ha.setIdHabilidad(res.getInt("idhabilidad"));
                ha.setNombreHabilidad(res.getString("nombrehabilidad"));
                
                Nivel ni=new Nivel();
                ni.setIdNivel(res.getInt("idnivel"));
                ni.setNivel(res.getString("nivel"));
                
                CvHabilidad cvh=new CvHabilidad(cv,ha,res.getInt("iddetallehabilidad"),ni);
                lst.add(cvh);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
    public void insertarComentario(int valor, String comentario, int iddetalle) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="INSERT INTO public.comentarios(\n" +
"	iddetallehabilidad, comentario)\n" +
"	VALUES ("+iddetalle+",'"+comentario+"');INSERT INTO public.valoracion(\n" +
"	iddetallehabilidad, promedio)\n" +
"	VALUES ("+iddetalle+","+valor+")";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public List<Valoraciones>listaComentario(String codigo) throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<Valoraciones>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select distinct c.iddetallehabilidad, comentario, promedio, numerovaloraciones " +
                       "from comentarios c inner join valoracion v on v.iddetallehabilidad = c.iddetallehabilidad " +
                        "where c.iddetallehabilidad="+codigo;
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Valoraciones val = new Valoraciones();
                val.setIddetalle(res.getInt("iddetallehabilidad"));
                val.setComentario(res.getString("comentario"));
                val.setValoracion(res.getInt("promedio"));
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
}
