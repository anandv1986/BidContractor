package com.bid.contract.dao.impl;

import com.bid.contract.dao.ContractDao;
import com.bid.contract.entities.BidDetails;
import com.bid.contract.entities.ProjectDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 *  Repository class for persisting data to H2 DB
 *
 */
@Repository
public class ContractDaoImpl implements ContractDao {

    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<ProjectDetails> getProjectDetails(String projectName, Integer projectId) {
        String queryStr = "";
        if (projectId != null ) {
            queryStr = "Select id, projectName, projectDesc, maxBudget, lastDay from ProjectDetails p where p.id = ?1";
        } else if (projectName != null) {
            queryStr = "Select id, projectName, projectDesc, maxBudget, lastDay from ProjectDetails p where p.projectName = ?1";
        }
        Query query = entityManager.createQuery ( queryStr ).setParameter ( 1 , (projectId != null? projectId:projectName) );
        List<ProjectDetails> allProjects = query.getResultList ();
        return allProjects;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, isolation = Isolation.READ_UNCOMMITTED)
    public ProjectDetails createProject(ProjectDetails project) {
        entityManager.persist ( project );
        return project;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, isolation = Isolation.READ_UNCOMMITTED)
    public void createABid(BidDetails bidDetails) {
        entityManager.persist ( bidDetails );
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<BidDetails> getLowestBidForProject(String projectName) {
        //String queryStr = "Select b from BidDetails b INNER JOIN ProjectDetails p on p.projectName = b.projectName and b.projectName = ?1 and p.lastDay >= ?2 ";
        String queryStr = "select b from BidDetails b where bidAmount = (SELECT min(bidAmount) FROM BidDetails where projectName = ?1 group by projectName) and projectName = ?1 ";
        Query query = entityManager.createQuery ( queryStr ).setParameter ( 1, projectName );
        List<BidDetails> bidProjects = query.getResultList ();
        return bidProjects;
    }
}
