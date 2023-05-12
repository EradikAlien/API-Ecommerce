package com.ecommerce.APIecommerce.controller;

import com.ecommerce.APIecommerce.common.ApiResponse;
import com.ecommerce.APIecommerce.dto.ProductDto;
import com.ecommerce.APIecommerce.model.Product;
import com.ecommerce.APIecommerce.model.User;
import com.ecommerce.APIecommerce.model.WishList;
import com.ecommerce.APIecommerce.service.AuthenticationService;
import com.ecommerce.APIecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token){

        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        WishList wishList = new WishList(user,product);

        wishListService.createWishList(wishList);

        ApiResponse apiResponse = new ApiResponse(true,"added to wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


        @GetMapping("/{token}")
        public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token")String token){

            authenticationService.authenticate(token);

            User user = authenticationService.getUser(token);

           List<ProductDto> productDtos = wishListService.getWishListForUser(user);
            return new ResponseEntity<>(productDtos,HttpStatus.OK);
        }

}
