package dao;

import entity.CompleteOrder;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class CompleteOrderDao extends AbstractDao<CompleteOrder> {
    @Override
    public CompleteOrder getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        CompleteOrder completeOrder = (CompleteOrder) session.get(CompleteOrder.class, id);
        session.getTransaction().commit();

        return completeOrder;
    }
}
