package com.jhonatan.empleados.jpa.logica;

import com.jhonatan.empleados.jpa.persistencia.Tbpersona;
import com.jhonatan.empleados.jpa.persistencia.TbpersonaJpaController;

public class TbPersonaDao {

    private TbpersonaJpaController controller = new TbpersonaJpaController();
    private Tbpersona tbpersona = new Tbpersona();
    private String mensaje = "";

    /*Métodos */
    public String insertarPersona(String nombres, String apellidos, int edad, String telefono) {
        try {
            tbpersona.setIdtbpersona(Integer.BYTES);
            tbpersona.setNombres(nombres);
            tbpersona.setApellidos(apellidos);
            tbpersona.setEdad(edad);
            tbpersona.setTelefono(telefono);
            /*llamamos al metodo de la clase controladora*/
            controller.create(tbpersona);
            mensaje = "se inserto la persona correctamente";
        } catch (Exception e) {
            System.out.println("error al momento de insertar: " + e.getMessage());
            mensaje = "Ocurrio un error al insertar la persona: ";
        }
        return mensaje;
    }

    public String actualizarPersona() {
        return mensaje;
    }

    public String eliminarPersona() {
        return mensaje;
    }
}
