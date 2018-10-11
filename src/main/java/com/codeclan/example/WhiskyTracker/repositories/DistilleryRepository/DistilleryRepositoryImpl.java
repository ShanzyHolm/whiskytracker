package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.sun.xml.internal.bind.v2.TODO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {

    @Autowired
    EntityManager entityManager;

//  TODO:   * get all the distilleries for a particular region

    @Transactional
    public List<Distillery> getDistilleriesByRegion(String region) {
        List<Distillery> distilleriesFound = null;
        Session session = entityManager.unwrap (Session.class);

        try {
            Criteria cr = session.createCriteria (Distillery.class);
            cr.add (Restrictions.eq ("region", region));
            distilleriesFound = cr.list ();
        } catch (HibernateException e) {
            e.printStackTrace ();
        } finally {
            session.close ();
        }

        return distilleriesFound;
    }


//  TODO:   * get all the whisky from a particular distillery that's a specific age (if any)
//  TODO:   * Get disilleries that have whiskies that are 12 years old
}
