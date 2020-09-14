package dao;

import entity.Order;
import exception.ServiceLayerException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional
public class OrderDao extends AbstractDao<Order> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDao.class);

    @Override
    public Order getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = (Order) session.get(Order.class, id);
        if (order == null) {
            LOGGER.info("Order is null");
            throw new ServiceLayerException("Order is null");
        }
        return order;
    }


}
