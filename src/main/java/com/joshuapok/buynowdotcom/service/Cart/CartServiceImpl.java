package com.joshuapok.buynowdotcom.service.Cart;

import com.joshuapok.buynowdotcom.Repository.CartRepository;
import com.joshuapok.buynowdotcom.model.Cart;
import com.joshuapok.buynowdotcom.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart getCart(Long cartId) {
        return null;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return null;
    }

    @Override
    public void createNewCart(User user) {

    }

    @Override
    public void clearCart(Long cartId) {

    }

    @Override
    public BigDecimal getCartTotalPrice(Long cartId) {
        return null;
    }
}
