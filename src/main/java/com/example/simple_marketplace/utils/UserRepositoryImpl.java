package com.example.simple_marketplace.utils;

import com.example.simple_marketplace.modul.User;
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
public class UserRepositoryImpl {

    private final EntityManager entityManager;

    public Page<User> advancedSearch(Map<String,String> params){
        int page = 0,size = 10;
        if (params.containsKey("page")){
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")){
            size = Integer.parseInt(params.get("size"));
        }

        String firstQuery = "select u from User as u where true ";
        String secondQuery = "select count(u.id) from User as u where true ";

        StringBuilder params1 = buildParams(params);
        StringBuilder params2 = buildParams(params);

        Query queryOne = this.entityManager.createQuery(firstQuery + params1);
        Query queryTwo = this.entityManager.createQuery(secondQuery + params2);

        setMoreParameter(params,queryOne);
        setMoreParameter(params,queryTwo);

        queryOne.setFirstResult(size*page);
        queryOne.setMaxResults(size);


        return new PageImpl<>(queryOne.getResultList(), PageRequest.of(page,size), Long.parseLong(queryTwo.getSingleResult().toString()));
    }

    private void setMoreParameter(Map<String, String> params, Query queryOne) {
        if (params.containsKey("id")){
            queryOne.setParameter("id",params.get("id"));
        }
        if (params.containsKey("firstname")){
            queryOne.setParameter("firstname",params.get("firstname"));
        }
        if (params.containsKey("lastname")){
            queryOne.setParameter("lastname",params.get("lastname"));
        }
        if (params.containsKey("email")){
            queryOne.setParameter("email",params.get("email"));
        }
        if (params.containsKey("username")){
            queryOne.setParameter("username",params.get("username"));
        }

    }

    public static StringBuilder buildParams(Map<String,String> params){
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("id")){
          stringBuilder.append("AND u.id = :id ");
        }
     if (params.containsKey("firstname")){
          stringBuilder.append("AND u.firstName ilike concat('%',:firstname,'%') ");
        }
     if (params.containsKey("lastname")){
          stringBuilder.append("AND u.lastName ilike concat('%',:lastname,'%') ");
        }
     if (params.containsKey("email")){
          stringBuilder.append("AND u.email ilike concat('%',:email,'%') ");
        }
     if (params.containsKey("username")){
          stringBuilder.append("AND u.username ilike concat('%',:username,'%') ");
        }
       return stringBuilder;
    }


}
