package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


public class WhiskyRepositoryImpl implements WhiskyRepositoryCustom {

    @Autowired
    EntityManager entityManager;

//  TODO:   * get all the whiskies for a particular year

    @Transactional
    public List<Whisky> getWhiskiesForParticularYear(int year) {
        List<Whisky> whiskiesFound = null;
        Session session = entityManager.unwrap (Session.class);

        try {
            Criteria cr = session.createCriteria (Whisky.class);
            cr.add (Restrictions.eq ("year", year));
            whiskiesFound = cr.list ();
        } catch (HibernateException e) {
            e.printStackTrace ();
        } finally {
            session.close ();
        }

        return whiskiesFound;
    }

//  TODO:   * get all the whisky from a particular region

    @Transactional
    public List<Whisky> getWhiskiesForRegion(Distillery distillery) {
        List<Whisky> regionalWhiskies = null;
        Session session = entityManager.unwrap (Session.class);

        try {
            Criteria cr = session.createCriteria (Whisky.class);
            cr.createAlias ("distilleries", "distillery");
            cr.add (Restrictions.eq ("distillery.region", distillery.getRegion ()));
            regionalWhiskies = cr.list ();
        } catch (HibernateException e) {
            e.printStackTrace ();
        } finally {
            session.close ();
        }

        return regionalWhiskies;
    }

//  WANT TO PASS IN REGION - getWhiskiesForRegion(String region) {


//  TODO:   * get all the whisky from a particular distillery that's a specific age (if any)

}
