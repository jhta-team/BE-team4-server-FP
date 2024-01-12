package com.kj.product.dto;

import com.kj.product.entity.*;
import com.kj.productReview.entity.ProductReview;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ProductFindOneDto {
    private Long id;

    private String productName;

    private String productNumber;

    private int productPrice;

    private String gender;

    private String productSeason;

    private int clickCount;
    private String productDatailImage;
    private Long productLike;

    private List<ProductImage> productImages;
    private List<ProductSize> productSize;
    private List<ProductTag> productTags;
    private List<ProductReview> productReview = new ArrayList<>();


    @Builder
    public ProductFindOneDto(Product product, List<ProductSize> findProductSize, List<ProductReview> findProductReview, List<ProductTag> findProductTags, Long producLike) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productNumber = product.getProductNumber();
        this.productPrice = product.getProductPrice();
        this.gender = product.getGender();
        this.productSeason = product.getProductSeason();
        this.clickCount = product.getClickCount();
        this.productDatailImage = product.getProductDatailImage();
        this.productImages = product.getProductImages();
        this.productSize = findProductSize;
        this.productTags = findProductTags;
        this.productLike = producLike;
        this.productReview = findProductReview;
    }
}