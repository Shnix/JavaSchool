package service;

import dao.CompleteOrderDao;
import dto.CompleteOrderDto;
import dtoconverter.Converter;
import entity.CompleteOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompleteOrderService {

    private CompleteOrderDao completeOrderDao;

    private Converter<CompleteOrder, CompleteOrderDto> converter;

    @Autowired
    public CompleteOrderService(CompleteOrderDao completeOrderDao,
                                Converter<CompleteOrder, CompleteOrderDto> converter) {
        this.completeOrderDao = completeOrderDao;
        this.converter = converter;
    }

    public List<CompleteOrderDto> listAllCompleteOrders() {
        return completeOrderDao.list().stream().map(o -> converter.convertIntoDto(o))
                .collect(Collectors.toList());
    }
}
