package dao;

import entity.CompleteOrder;
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
public class CompleteOrderDao extends AbstractDao<CompleteOrder> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompleteOrderDao.class);

    @Override
    public CompleteOrder getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        CompleteOrder completeOrder = (CompleteOrder) session.get(CompleteOrder.class, id);
        if(completeOrder == null){
            LOGGER.info("Complete Order is null");
            throw new ServiceLayerException("Complete Order is null");
        }
        return completeOrder;
    }

    public List<CompleteOrder> getLastTenOrders(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CompleteOrder order by orderId DESC");
        query.setMaxResults(10);
        List<CompleteOrder> orders = query.list();
        return orders;
    }
}
