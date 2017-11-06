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
public class CrudCvIdioma extends Conexion{
    
   public void insertarCvIdioma(CvIdioma cvi) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="insert into cvidioma(idcurriculum,idnivel,ididioma) values(?,?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, cvi.getCurriculum().getIdCurriculum());
            pre.setInt(2, cvi.getNivel().getIdNivel());
            pre.setInt(3, cvi.getIdioma().getIdIdioma());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {

            throw e;
        }
    }
    
    public void modificarCvIdioma(CvIdioma cvi) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="update cvidioma set idnivel=? where idcurriculum=? and ididioma=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, cvi.getNivel().getIdNivel());
            pre.setInt(2, cvi.getCurriculum().getIdCurriculum());
            pre.setInt(3, cvi.getIdioma().getIdIdioma());
            
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public void eliminarCvIdioma(CvIdioma cvi) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try 
        {
            conexion = db.getConnection();
            String sql="delete from cvidioma where idcurriculum=? and ididioma=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, cvi.getCurriculum().getIdCurriculum());
            pre.setInt(2, cvi.getIdioma().getIdIdioma());
            pre.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    
    public List<CvIdioma>mostrarCvIdioma() throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<CvIdioma>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select curriculum.idcurriculum, idioma.ididioma, idioma.nombreidioma, nivel.idnivel, nivel.nivel  "
                    + "from cvidioma inner join curriculum on cvidioma.idcurriculum=curriculum.idcurriculum "
                    + "inner join idioma on cvidioma.ididioma=idioma.ididioma "
                    + "inner join nivel on cvidioma.idnivel=nivel.idnivel";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next())
            {
                Curriculum cv=new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                Idioma idi=new Idioma();
                idi.setIdIdioma(res.getInt("ididimona"));
                idi.setNombreIdioma(res.getString("nombreidioma"));
                
                Nivel ni=new Nivel();
                ni.setIdNivel(res.getInt("idnivel"));
                ni.setNivel(res.getString("nivel"));
                
                CvIdioma cvi=new CvIdioma(cv,idi,ni);
                lst.add(cvi);
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
            String sql="select * from nivel order by idnivel";
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
    public List<CvIdioma>mostrarCvIdioma(int idCurriculum) throws Exception
    {
        Conexion db=new Conexion();
        Connection conexion =null;
        ResultSet res;
        List<CvIdioma>lst=new ArrayList();
        try 
        {
            conexion = db.getConnection();
            String sql="select curriculum.idcurriculum, idioma.ididioma, idioma.nombreidioma, nivel.idnivel, nivel.nivel  "
                    + "from cvidioma inner join curriculum on cvidioma.idcurriculum=curriculum.idcurriculum "
                    + "inner join idioma on cvidioma.ididioma=idioma.ididioma "
                    + "inner join nivel on cvidioma.idnivel=nivel.idnivel  where curriculum.idcurriculum=?";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, idCurriculum);
            res=pre.executeQuery();
            while(res.next())
            {
                Curriculum cv=new Curriculum();
                cv.setIdCurriculum(res.getInt("idcurriculum"));
                
                Idioma idi=new Idioma();
                idi.setIdIdioma(res.getInt("ididioma"));
                idi.setNombreIdioma(res.getString("nombreidioma"));
                
                Nivel ni=new Nivel();
                ni.setIdNivel(res.getInt("idnivel"));
                ni.setNivel(res.getString("nivel"));
                
                CvIdioma cvi=new CvIdioma(cv,idi,ni);
                lst.add(cvi);
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
        return lst;
    }
    
}
