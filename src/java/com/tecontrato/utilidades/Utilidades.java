/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecontrato.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Kevin López
 */
public class Utilidades {

    public static String invertirFechas(String fechaStr, String formatFrom, String formatTo) {
        String resp = null;
        SimpleDateFormat formaterFrom, formaterTo;
        Date fechaDate;
        try {
            //System.out.println("fecha INI:"+fechaStr+", formato:"+formatFrom);
            if (fechaStr != null && !fechaStr.equals("")) {
                formaterFrom = new SimpleDateFormat(formatFrom != null ? formatFrom : "yyyy/mm/dd");
                fechaDate = formaterFrom.parse(fechaStr);
                formaterTo = new SimpleDateFormat(formatTo != null ? formatTo : "dd-mm-yyyy");
                resp = formaterTo.format(fechaDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public static java.sql.Date convertFromStrToSqlDate(String fechaStr, String formatFrom) {
        java.sql.Date resp = null;
        SimpleDateFormat formaterFrom;
        Date fechaDate;
        try {
            if (fechaStr != null && !fechaStr.equals("")) {
                formaterFrom = new SimpleDateFormat(formatFrom != null ? formatFrom : "yyyy-MM-dd");
                fechaDate = formaterFrom.parse(fechaStr);
                resp = new java.sql.Date(fechaDate.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

}
