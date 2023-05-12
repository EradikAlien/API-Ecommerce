package com.ecommerce.APIecommerce.repository;

import com.ecommerce.APIecommerce.model.Cart;
import com.ecommerce.APIecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
