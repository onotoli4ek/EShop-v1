package test;


import entity.JpaUser;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class UserService {
    public EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    public static void main(String[] args) {
        UserService service = new UserService();
        JpaUser user = new JpaUser();
        user.setEmail("uuuu@ua");
        user.setLogin("2232");
        user.setPassword("z3uuuzz");
        service.add(user);
        System.exit(0);
    }
    public JpaUser add (JpaUser user) {
        em.getTransaction().begin();
        JpaUser carFromDB = em.merge(user);
        em.getTransaction().commit();
        return carFromDB;
    }
}
