package com.joshuapok.buynowdotcom.Repository;

import com.joshuapok.buynowdotcom.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByProductId(Long productId);
}
