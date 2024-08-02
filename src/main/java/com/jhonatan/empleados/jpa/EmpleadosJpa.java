package com.jhonatan.empleados.jpa;

import com.jhonatan.empleados.jpa.logica.TbPersonaDao;

public class EmpleadosJpa {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        TbPersonaDao p1 = new TbPersonaDao();
        String m = p1.actualizarPersona(3, "Maria", "Ojeda", 45, "955331117");
        System.out.println(m);
    }
}
