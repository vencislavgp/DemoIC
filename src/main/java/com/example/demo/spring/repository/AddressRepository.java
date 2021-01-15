package com.example.demo.spring.repository;

import com.example.demo.spring.entity.Address;
import com.example.demo.spring.repository.custom.AddressRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Address Jpa Repository
 */
public interface AddressRepository extends AddressRepositoryCustom,JpaRepository<Address, Long> {
}
