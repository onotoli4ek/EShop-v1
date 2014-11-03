package test;


import entity.JpaUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MyPersistence {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("quiz");
        EntityManager entityManager =
                entityManagerFactory.createEntityManager();
        selectById(entityManager);


    }
    //EntityManager
    public static void selectById(EntityManager entityManager) {
        JpaUser user = entityManager.find(JpaUser.class, 1);
        System.out.println(">> User " + user);
    }

    //NamedQuery
    public static void selectByEmail (EntityManager entityManager){
        List list = entityManager.createNamedQuery("findAllCustomersWithEmailLike")
                .setParameter("email", "%.com")
                .setMaxResults(10)
                .getResultList();
        System.out.println(">> List " + list);
    }

    //JPQL
    public static void selectByName (EntityManager entityManager){
        List list = entityManager.createQuery(
                "SELECT u FROM JpaUser u WHERE login=:login")
                .setParameter("login", "Mike")
                .setMaxResults(10)
                .getResultList();
        System.out.println(">> List " + list);
    }

    //Criteria
    public static void selectAll (EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaUser> criteriaQuery = criteriaBuilder.createQuery(JpaUser.class);
        Root<JpaUser> root = criteriaQuery.from(JpaUser.class);
        criteriaQuery.select(root);
        TypedQuery<JpaUser> typedQuery = entityManager.createQuery(criteriaQuery);
        List<JpaUser> allUsers = typedQuery.getResultList();
        System.out.println(">> allUsers " + allUsers);
    }

}
