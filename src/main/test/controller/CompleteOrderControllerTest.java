package controller;

import dto.CompleteOrderDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import service.CompleteOrderService;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CompleteOrderControllerTest {

    @InjectMocks
    CompleteOrderController completeOrderController;

    @Mock
    CompleteOrderService completeOrderService;


    @Test
    public void listAllCompleteOrders() {
        when(completeOrderService.listAllCompleteOrders()).thenReturn(new ArrayList<CompleteOrderDto>());
        completeOrderController.listAllCompleteOrders();
        verify(completeOrderService).listAllCompleteOrders();
    }
}