package com.klef.jfsd.exam.EndLab;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ClientDemo {

    public static void main(String[] args) {
        
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        Project proj1=new Project("JFSD",6,25000,"Charith");
        Project proj2=new Project("MSwD",7,35000,"God");
        
        session.save(proj1);
        session.save(proj2);
        
        transaction.commit();
        
        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Project> query = criteriaBuilder.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);
        query.select(root);

        List<Project> allProjects = session.createQuery(query).getResultList();
        System.out.println("All Projects:");
        allProjects.forEach(System.out::println);

        query = criteriaBuilder.createQuery(Project.class);
        root = query.from(Project.class);
        query.select(root).where(criteriaBuilder.gt(root.get("fees"), 30000));

        List<Project> expensiveProjects = session.createQuery(query).getResultList();
        System.out.println("Projects with fees > 30000:");
        expensiveProjects.forEach(System.out::println);
        
        
        session.close();
        sessionFactory.close();
        
        
        
        
        
    }
}
