package com.jhonatan.empleados.jpa;

import com.jhonatan.empleados.jpa.iu.frmLogin;
import com.jhonatan.empleados.jpa.iu.frmPersona;
import com.jhonatan.empleados.jpa.iu.frmomboBoxPersona;

public class EmpleadosJpa {

    public static void main(String[] args) {
        d();
        //a();
    }

    public static void d() {
        frmLogin d = new frmLogin();
        d.setVisible(true);
    }

    public static void g() {
        frmPersona ven = new frmPersona();
        ven.setVisible(true);
    }

    public static void a() {
        frmomboBoxPersona ven = new frmomboBoxPersona();
        ven.setVisible(true);
    }

}
