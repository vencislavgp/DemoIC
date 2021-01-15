package com.example.demo.spring.service;

import com.example.demo.spring.entity.Address;

import java.util.List;

/**
 * услуги предоставени за Address
 */
public interface AddressService {
    /**
     * търсене на Address по идентификатор
     * @param id
     * @return
     */
    Address findById(Long id);
    List<Address> findByPersonId(long id);
    Address save(Address address);
    void deleteById(long id);

}
