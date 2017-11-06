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
    
    public void insertarEmpleado(Empleado emp)
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="insert into empleado(idempleado, nombreempleado, direccion, telefono,fechanacimiento, foto) values (?,?,?,?,?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, emp.getIdEmpleado());
            pre.setString(2, emp.getNombreEmpleado());
            pre.setString(3, emp.getDireccion());
            pre.setString(4, emp.getTelefono());
            pre.setDate(5, Utilidades.convertFromStrToSqlDate(emp.getFechaNacimiento(), "yyyy-MM-dd"));
            pre.setString(6, emp.getFoto());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void modificarEmpleado(Empleado emp)
    {
        Conexion db = new Conexion();
        Connection conexion = null;
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
        }
    }
    
    public void eliminarEmpleado(Empleado emp)
    {
        Conexion db = new Conexion();
        Connection conexion = null;
        try {
            conexion = db.getConnection();
            String sql="delete from empleado where idempleado=?";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setInt(1, emp.getIdEmpleado());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Empleado>mostrarEmpleado()
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
        }
        return lst;
    }
}
