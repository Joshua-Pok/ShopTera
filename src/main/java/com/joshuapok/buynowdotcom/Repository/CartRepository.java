package com.joshuapok.buynowdotcom.Repository;

import com.joshuapok.buynowdotcom.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
