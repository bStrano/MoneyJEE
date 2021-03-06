/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.stralom.moneyspring.dao;

import br.stralom.moneyspring.entities.Balance;
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
public class BalanceDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Balance> findAll(Long idUser){
        String jpql = "select b from Balance b"
                + " join fetch b.bal_user u"
                + " where u.user_id = :idUser";
        TypedQuery<Balance> queryBalance = em.createQuery(jpql, Balance.class);
        queryBalance.setParameter("idUser",idUser);
        return queryBalance.getResultList();
    }
    
    public void save(Balance balance){
        em.persist(balance);
    }
}
