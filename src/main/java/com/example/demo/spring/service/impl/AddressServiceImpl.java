package com.example.demo.spring.service.impl;

import com.example.demo.spring.entity.Address;
import com.example.demo.spring.repository.AddressRepository;
import com.example.demo.spring.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Address Service implementation
 */
@Service
@Transactional(readOnly = true, rollbackFor = Throwable.class)
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> findByPersonId(long id) {
        return addressRepository.findAddressesByPersonId(id);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteById(long id) {
        addressRepository.deleteById(id);
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }
    @Resource
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}
