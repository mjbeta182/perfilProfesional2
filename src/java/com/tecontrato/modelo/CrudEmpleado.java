package com.tecontrato.modelo;

import com.tecontrato.conexion.Conexion;
import com.tecontrato.utilidades.Utilidades;
import java.sql.*;
import java.util.*;

/**
 *
 * @author lguil
 */
public class CrudEmpleado extends Conexion{
    
    public int insertarEmpleado(Empleado emp) throws SQLException
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        int resultado = 0;
        try {
            conexion = db.getConnection();
            String sql="insert into empleado(idempleado, nombreempleado, direccion, telefono,fechanacimiento, foto) values (?,?,?,?,?,'userdefault.png')";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, emp.getIdEmpleado());
            pre.setString(2, emp.getNombreEmpleado());
            pre.setString(3, emp.getDireccion());
            pre.setString(4, emp.getTelefono());
            pre.setDate(5, Utilidades.convertFromStrToSqlDate(emp.getFechaNacimiento(), "yyyy-MM-dd"));

            resultado = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conexion.close();
        }
        return resultado;
    }
    
    public int  modificarEmpleado(Empleado emp) throws SQLException
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        int resultado = 0;
        try {
            conexion = db.getConnection();
            String sql="update empleado set nombreempleado=?, direccion=?, telefono=?, fechanacimiento=?, foto=? where idempleado=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setString(1, emp.getNombreEmpleado());
            pre.setString(2, emp.getDireccion());
            pre.setString(3, emp.getTelefono());
            pre.setDate(4, Utilidades.convertFromStrToSqlDate(emp.getFechaNacimiento(), "yyyy-MM-dd"));
            pre.setString(5, emp.getFoto());
            pre.setInt(6, emp.getIdEmpleado());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conexion.close();
        }
        return  resultado;
    }
    
    public int eliminarEmpleado(Empleado emp) throws SQLException
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        int resultado = 0;
        try {
            conexion = db.getConnection();
            String sql="delete from empleado where idempleado=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, emp.getIdEmpleado());
            resultado = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conexion.close();
        }
        return  resultado;
    }
    
    public List<Empleado>mostrarEmpleado() throws SQLException
    {
        ResultSet res;
        List<Empleado>lst=new ArrayList();
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="select * from empleado order by idempleado";
            PreparedStatement pre = conexion.prepareCall(sql);
            res=pre.executeQuery();
            while(res.next()){
                Empleado emp=new Empleado();
                emp.setIdEmpleado(res.getInt("idempleado"));
                emp.setNombreEmpleado(res.getString("nombreempleado"));
                emp.setDireccion(res.getString("direccion"));
                emp.setTelefono(res.getString("telefono"));
                emp.setFechaNacimiento(Utilidades.invertirFechas(res.getString("fechanacimiento"), "yyyy-mm-dd", "dd-mm-yyyy"));
                emp.setFoto(res.getString("foto"));
                lst.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conexion.close();
        }
        return lst;
    }
    
      public int modificarFoto(Empleado em) throws Exception
    {
        int resultado = 0;
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="update empleado set foto=? where idempleado=?";
            PreparedStatement pre = conexion.prepareStatement(sql); 
            
            pre.setString(1, em.getFoto());
            pre.setInt(2, em.getIdEmpleado());

            resultado = pre.executeUpdate();  
            
        } catch (Exception e) {
            
            throw e;
        }finally{
            conexion.close();
        }
        return resultado;
    }
       public List<Empleado>obtenerEmpleados(int id) throws Exception
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        ResultSet res;
        List<Empleado>lst= new ArrayList();
        try {
            conexion = db.getConnection();
            String sql="select * from empleado where idempleado=?";
            PreparedStatement pre = conexion.prepareCall(sql);
            pre.setInt(1, id);
            res = pre.executeQuery();
            while(res.next())
            {
                Empleado emp=new Empleado();
                emp.setIdEmpleado(res.getInt("idempleado"));
                emp.setNombreEmpleado(res.getString("nombreempleado"));
                emp.setDireccion(res.getString("direccion"));
                emp.setTelefono(res.getString("telefono"));
                emp.setFechaNacimiento(Utilidades.invertirFechas(res.getString("fechanacimiento"), "yyyy-mm-dd", "dd-mm-yyyy"));
                emp.setFoto(res.getString("foto"));
                lst.add(emp);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    
}
