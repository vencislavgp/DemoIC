package com.example.demo.spring.repository.custom;

import com.example.demo.spring.entity.Address;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface AddressRepositoryCustom {
    @Query(nativeQuery = true, value = "select address.* from T_ADDRESSES address where address.T_PEOPLE_ID = ?1")
    List<Address> findAddressesByPersonId(long personId);
}
