package dao;

import Database.ObjectDBUtil;
import Models.Client;
import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.ArrayList;



@Data
public class ClientDaoImpl implements ClientDaoI {

    @Override
    public void insertarCliente(Client client) {
        try {
            EntityManager em = ObjectDBUtil.getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            em.persist(client);

            em.flush();

            System.out.println("Nuevo cliente insertado en la base de datos: "+client);

            tx.commit();
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public void getCliente(Long id) {

        try {
            EntityManager em = ObjectDBUtil.getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Client c = em.find(Client.class, id);

            System.out.println(c);

            tx.commit();
        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    @Override
    public void listarMejoresClientes(Long quantity) {

        try {
            EntityManager em = ObjectDBUtil.getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Query query = em.createQuery("SELECT c FROM Client c WHERE c.totalSales >= :q AND c.state = 'activo' ", Client.class);
            query.setParameter("q", quantity);

            try {
               ArrayList clients = (ArrayList<Client>) query.getResultList();

               clients.forEach(c ->{
                   System.out.println(c);
               });

            } catch (NoResultException ignored) {

            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    public void estadisticas() {

        try {
            EntityManager em = ObjectDBUtil.getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Query queryCount = em.createQuery("SELECT sum(c.totalSales) FROM Client c ", Client.class);

            Query queryAvg = em.createQuery("SELECT Avg(c.totalSales) FROM Client c WHERE c.state = 'activo' ", Client.class);

            Query queryInactives = em.createQuery("SELECT Count(c) FROM Client c WHERE c.state = 'inactivo' AND c.totalSales > 0  ", Client.class);

            try {
                Long totalVentas = (Long) queryCount.getSingleResult();

                Double mediaVentas = (Double) queryAvg.getSingleResult();

                Long inactives = (Long) queryInactives.getSingleResult();


                System.out.println("El total de ventas de todos los clientes asciente a: " + totalVentas);

                System.out.println("El Promedio de ventas de los clientes activos es de: " + mediaVentas);

                System.out.println("La cantidad de clientes inactivos que tienen ventas mayores a 0 es de: " + inactives);

            } catch (NoResultException ignored) {

            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
