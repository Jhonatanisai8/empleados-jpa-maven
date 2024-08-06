package com.jhonatan.empleados.jpa.logica;

import com.jhonatan.empleados.jpa.persistencia.Usuarios;
import com.jhonatan.empleados.jpa.persistencia.UsuariosJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UsuarioDao {

    private Usuarios user = new Usuarios();
    private UsuariosJpaController usuariosJpaController = new UsuariosJpaController();

    public boolean login(String usuario, String contraseña) {

        EntityManager em = usuariosJpaController.getEntityManager();
        boolean valor;
        String mensaje = "";
        try {
            //sentencia JPQL
            Query query = em.createQuery("SELECT u.usuario,u.contrasenia FROM Usuarios u  WHERE u.usuario: usuario  and u.contrasenia:  contrasenia");
            //le asiganamos los paramtros
            query.setParameter("usuario", usuario);
            query.setParameter("contrasenia", contraseña);
            List result = query.getResultList();

            //si no esta vacio
            if (!result.isEmpty()) {
                valor = true;
            } else {
                valor = false;
            }

        } catch (Exception e) {
            valor = false;
            mensaje = "Ups no se pudo ingresar : "+e.getMessage();
        }
        return valor;
    }
}
