package com.jhonatan.empleados.jpa.logica;

import com.jhonatan.empleados.jpa.persistencia.Tbpersona;
import com.jhonatan.empleados.jpa.persistencia.TbpersonaJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

    public String actualizarPersona(int idPersona, String nombres, String apellidos, int edad, String telefono) {
        try {
            tbpersona.setIdtbpersona(idPersona);
            tbpersona.setNombres(nombres);
            tbpersona.setApellidos(apellidos);
            tbpersona.setEdad(edad);
            tbpersona.setTelefono(telefono);
            /*llamamos al metodo de la clase controladora*/
            controller.edit(tbpersona);
            mensaje = "se actualizo la persona correctamente";
        } catch (Exception e) {
            System.out.println("error al momento de actualizar: " + e.getMessage());
            mensaje = "No se puede actualizar la informacion. \n" + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarPersona(int id) {
        try {
            controller.destroy(id);
            mensaje = "eliminado persona correctamente ";
        } catch (Exception e) {
            mensaje = "no se pudo eliminar";
            System.out.println("Error al eliminar " + e.getMessage());
        }
        return mensaje;
    }

    public void listarPersona(JTable tblPersonas, String nombres) {
        DefaultTableModel model;
        String[] titulos = {"ID", "NOMBRES", "APELLIDOS", "EDAD", "TELEFONO"};
        model = new DefaultTableModel(null, titulos);
        List<Tbpersona> listaPersona = buscarPersona(nombres);
        String[] datosPersona = new String[titulos.length];
        for (Tbpersona persona : listaPersona) {
            datosPersona[0] = persona.getIdtbpersona() + "";
            datosPersona[1] = persona.getNombres();
            datosPersona[2] = persona.getApellidos();
            datosPersona[3] = persona.getEdad() + "";
            datosPersona[4] = persona.getTelefono();

            /*la guardamos al arreglo*/
            model.addRow(datosPersona);
        }

        /*le asiganamos a la tabla el modelo*/
        tblPersonas.setModel(model);
    }

    private List<Tbpersona> buscarPersona(String nombres) {
        EntityManager em = controller.getEntityManager();
        Query query = em.createQuery("SELECT p FROM Tbpersona p WHERE p.nombres LIKE :nombres");
        query.setParameter("nombres", "%" + nombres + "%");

        List<Tbpersona> lista = query.getResultList();
        return lista;
    }

    public Tbpersona buscarPersonaId(int id) {
        Tbpersona tbPersona;
        EntityManager em = controller.getEntityManager();
        Query query = em.createQuery("SELECT p FROM Tbpersona p WHERE p.idtbpersona = idtbpersona");
        query.setParameter("idtbpersona", id);
        
        /*Casteamos a tipo persona*/
        tbPersona = (Tbpersona) query.getSingleResult();
        return tbpersona;
    }
}
