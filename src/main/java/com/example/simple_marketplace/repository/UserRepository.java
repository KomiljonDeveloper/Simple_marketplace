package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByIdAndDeletedAtIsNull(Integer id);

    List<User> findAllByDeletedAtIsNull();

    Page<User> findAllByDeletedAtIsNull(Pageable pageable);
    @Query(
            value = "select * from users where" +
                    " id = coalesce(:id,id) and" +
                    " first_name ilike coalesce(concat('%',:fname,'%'),first_name) and" +
                    " last_name ilike coalesce(concat('%',:lname,'%'),last_name) and" +
                    " email ilike coalesce(concat('%',:email,'%'),email) and" +
                    " username ilike coalesce(concat('%',:uname,'%'),username) and" +
                    " deleted_at is null;",
            nativeQuery = true
    )
    Page<User> searchByBasic(
            @Param(value = "id") Integer id,
            @Param(value = "fname") String firstName,
            @Param(value = "lname") String lastName,
            @Param(value = "email") String email,
            @Param(value = "uname") String userName,
            Pageable pageable);
}
