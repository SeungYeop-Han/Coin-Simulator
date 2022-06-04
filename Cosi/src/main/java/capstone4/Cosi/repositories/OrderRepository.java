package capstone4.Cosi.repositories;

import capstone4.Cosi.domains.FilledOrder;
import capstone4.Cosi.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.orderState = 'FILLED' WHERE o.id = :id")
    public void concludeOrder(@Param("id") Long id);
}
