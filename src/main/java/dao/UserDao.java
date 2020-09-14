package dao;


import entity.User;
import exception.ServiceLayerException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;


@Component
@Transactional
public class UserDao extends AbstractDao<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    @Override
    public User getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        if (user == null) {
            LOGGER.info("User is null");
            throw new ServiceLayerException("User is null");
        }
        return user;
    }

    public User findByUsername(String userName) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery("from User where name = :param ");
            query.setParameter("param", userName);
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            throw new RuntimeException("No Such User");
        }
    }
}
