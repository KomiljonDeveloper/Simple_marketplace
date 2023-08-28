package com.example.simple_marketplace.utils;

import com.example.simple_marketplace.dto.ProductDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.modul.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl {


    private final EntityManager entityManager;

    public Page<Product> searchByAdvanced(Map<String, String> params) {

        int page = 0,size = 10;
        if (params.containsKey("page")){
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")){
            size = Integer.parseInt(params.get("size"));
        }

        String firstQuery = "select p from Product as p where true ";
        String secondQuery = "select count(p.productId) from Product as p where true ";


        StringBuilder stringBuilder = buildParams(params);

        Query queryOne = this.entityManager.createQuery(firstQuery+stringBuilder);
        Query queryTwo = this.entityManager.createQuery(secondQuery+stringBuilder);

        moreSetParameter(params,queryOne);
        moreSetParameter(params,queryTwo);

        queryOne.setFirstResult(size*page);
        queryOne.setMaxResults(size);

        return new PageImpl<>(queryOne.getResultList(), PageRequest.of(page,size),Long.parseLong(queryTwo.getSingleResult().toString()));
    }

    private void moreSetParameter(Map<String, String> params, Query firstQuery) {
        if (params.containsKey("id")){
            firstQuery.setParameter("id",params.get("id"));
        }
        if (params.containsKey("name")){
            firstQuery.setParameter("name",params.get("name"));
        }

    }

    private StringBuilder buildParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("id")){
            stringBuilder.append("AND p.productId = :id ");
        }
        if (params.containsKey("name")){
            stringBuilder.append("AND p.prod_name ilike concat('%',:name,'%')");
        }
        stringBuilder.append(" And deletedAt is null");
        return stringBuilder;

    }
}
