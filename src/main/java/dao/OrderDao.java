package dao;

import entity.Order;
import org.hibernate.Session;


public class OrderDao extends AbstractDao<Order> {

    @Override
    public Order getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Order order = (Order) session.load(Order.class, id);
        session.getTransaction().commit();
        return order;
    }
}
