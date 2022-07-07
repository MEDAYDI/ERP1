package com.example.ordre.service;

import com.example.ordre.dto.OrderRequest;
import com.example.ordre.dto.Response;
import com.example.ordre.repository.OrderRepo;
import com.example.ordre.model.Erp;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service

public class OrderService {
    private OrderRepo orderRepo ;


    public OrderService(OrderRepo orderRepo) {
       this.orderRepo=orderRepo;

    }

    public void   createOrder(OrderRequest orderRequest) {

      Erp order=new Erp(orderRequest.getOf(),orderRequest.getClient()
      ,orderRequest.getLot(),orderRequest.getQteLot());
        orderRepo.save(order);
    }


    public List<Erp> getAllOrders() {
       return orderRepo.findAll() ;
    }

    public Erp findOrderById(Long id){
        return orderRepo.findOrderById(id);
    }

    public List<Erp> findOrderByOf(String of){
        return orderRepo.findOrderByOf(of);
    }

    public List<Erp> findOderByClient(String client){
        return orderRepo.findOderByClient(client);
    }


    public void deleteOrderById(Long id){
         orderRepo.deleteOrderById(id);
    }

    public int updateOrderById(Long id , Response orderRequest){
      return   orderRepo.updateOrderById(id,orderRequest.getClient() ,orderRequest.getOf()
                ,orderRequest.getLot(),orderRequest.getQteLot());

    }
}
