package dao;

import entity.Order;
import org.hibernate.Session;
import org.springframework.stereotype.Component;


@Component
public class OrderDao extends AbstractDao<Order> {

    @Override
    public Order getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Order order = (Order) session.get(Order.class, id);
        session.getTransaction().commit();

        return order;
    }
}
