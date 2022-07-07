package com.example.ordre.repository;

import com.example.ordre.model.Erp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepo  extends JpaRepository<Erp,Long> {

    Erp findOrderById(Long id);
    @Query("select e from Erp e where e.of= :of")
    List<Erp> findOrderByOf(String of);
    @Query("select e from Erp e where e.client= :client")
    List<Erp> findOderByClient(String client);
    @Transactional
    @Modifying
    void deleteOrderById(Long id);
    @Transactional
    @Modifying
    @Query("update Erp e set e.client= :client,e.of= :of,e.lot= :lot,e.qteLot= :qteLot where  e.id= :id")
    int updateOrderById(Long id  , String client , String of , String lot , Integer qteLot);
}
