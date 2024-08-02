package com.jhonatan.empleados.jpa;

import com.jhonatan.empleados.jpa.logica.TbPersonaDao;

public class EmpleadosJpa {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        TbPersonaDao p1 = new TbPersonaDao();
        String m = p1.insertarPersona("Gabriel", "Lopez", 12, "912334567");
        System.out.println(m);
    }
}
