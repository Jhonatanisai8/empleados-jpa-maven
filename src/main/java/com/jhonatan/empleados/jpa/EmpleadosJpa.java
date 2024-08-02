package com.jhonatan.empleados.jpa;

import com.jhonatan.empleados.jpa.logica.TbPersonaDao;

public class EmpleadosJpa {
    
    public static void main(String[] args) {
        TbPersonaDao dao = new TbPersonaDao();
        System.out.println(dao.eliminarPersona(9));
    }
}
