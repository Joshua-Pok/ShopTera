package com.joshuapok.buynowdotcom.service.Cart;

import com.joshuapok.buynowdotcom.model.Cart;
import com.joshuapok.buynowdotcom.model.User;

import java.math.BigDecimal;

public interface CartService {
    Cart getCart(Long cartId);
    Cart getCartByUserId(Long userId);
    void createNewCart(User user);
    void clearCart(Long cartId);
    BigDecimal getCartTotalPrice(Long cartId);
}
