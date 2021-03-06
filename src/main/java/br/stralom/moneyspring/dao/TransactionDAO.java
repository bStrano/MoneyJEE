/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.stralom.moneyspring.dao;

import br.stralom.moneyspring.entities.Category;
import br.stralom.moneyspring.entities.Transaction;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno Strano
 */
@Repository
@Transactional
public class TransactionDAO {

    @PersistenceContext
    private EntityManager em;

    public void save(Transaction transaction) {
        em.persist(transaction);
    }

    public Transaction find(Long id) {
        return em.find(Transaction.class, id);
    }

    public void merge(Transaction transaction) {
        em.merge(transaction);
    }

    public List<Transaction> showAll() {
        String jpql = "select distinct t from Transaction t join fetch t.tra_categories";
        TypedQuery<Transaction> traQuery = em.createQuery(jpql, Transaction.class);
        return traQuery.getResultList();
    }

    public List<Transaction> findLikeName(String name) {
        String jpql = "select t from Transaction t where t.tra_name like :pName";
        TypedQuery<Transaction> traQuery = em.createQuery(jpql, Transaction.class);
        traQuery.setParameter("pName", name);
        return traQuery.getResultList();
    }

    public Transaction findByName(String name) {
        String jpql = "select tra from Transaction tra "
                + "join fetch tra.tra_instalments "
                + "join fetch tra.tra_categories "
                + "join fetch  tra.tra_company "
                + "where tra.tra_name like :pName ";
        TypedQuery<Transaction> traQuery = em.createQuery(jpql, Transaction.class);
        traQuery.setParameter("pName", name);
        return traQuery.getSingleResult();
    }

    public Transaction findById(Long id) {
        String jpql = "select tra from Transaction tra "
                + "join fetch tra.tra_instalments "
                + "join fetch tra.tra_categories "
                + "join fetch  tra.tra_company "
                + "where tra.tra_id like :pId ";
        TypedQuery<Transaction> traQuery = em.createQuery(jpql, Transaction.class);
        traQuery.setParameter("pId", id);
        return traQuery.getSingleResult();
    }

    public List<Transaction> findAll(Long idUser, Long idBalance) {
        String jpql = "select tra from Transaction tra"
                + " join fetch tra.tra_instalments"
                + " join fetch tra.tra_user u"
                + " join fetch tra.tra_balance b"
                + " where u.user_id = :idUser"
                + " and b.bal_id = :idBalance ";
        TypedQuery<Transaction> traQuery = em.createQuery(jpql, Transaction.class);
        traQuery.setParameter("idUser", idUser);
        traQuery.setParameter("idBalance", idBalance);
        return traQuery.getResultList();

    }

    public List<Transaction> findAllWithInstalment() {
        String jpql = "select tra from Transaction tra"
                + " join fetch tra.tra_instalments"
                + " join fetch tra.tra_categories"
                + " join fetch tra.tra_company";
        TypedQuery<Transaction> insQuery = em.createQuery(jpql, Transaction.class);

        return insQuery.getResultList();

    }

}
