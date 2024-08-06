package com.jhonatan.empleados.jpa.logica;

import com.jhonatan.empleados.jpa.persistencia.Usuarios;
import com.jhonatan.empleados.jpa.persistencia.UsuariosJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class UsuarioDao {

    private Usuarios user = new Usuarios();
    private UsuariosJpaController usuariosJpaController = new UsuariosJpaController();

    public boolean login(String usuario, String contraseña) {
        EntityManager em = usuariosJpaController.getEntityManager();
        boolean valor;
        try {
            // sentencia JPQL corregida
            Query query = em.createQuery("SELECT u FROM Usuarios u WHERE u.usuario = :usuario AND u.contrasenia = :contrasenia");
            // asignamos los parámetros
            query.setParameter("usuario", usuario);
            query.setParameter("contrasenia", contraseña);
            List result = query.getResultList();

            // si no está vacío
            valor = !result.isEmpty();
        } catch (Exception e) {
            valor = false;
        }
        return valor;
    }

}
