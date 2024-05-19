package com.redhat.vendor.services;

import com.redhat.vendor.dtos.VendorDTO;
import com.redhat.vendor.exceptions.EntityNotFoundException;
import com.redhat.vendor.models.VendorEntity;
import com.redhat.vendor.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorDTO save(VendorDTO VendorDTO) {
        return new VendorDTO(vendorRepository.save(VendorDTO.toVendorEntity()));
    }

    @Override
    public List<VendorDTO> findAll() {
        return vendorRepository.findAll().stream().map(VendorDTO::new).toList();
    }

    @Override
    public VendorDTO findOneById(String id) {
        VendorEntity vendor = vendorRepository.findOneById(id);
        if (vendor == null) {
            throw new EntityNotFoundException(id);
        }
        return new VendorDTO(vendor);
    }

    @Override
    public VendorDTO findOneByName(String name) {
        VendorEntity vendor = vendorRepository.findOneByName(name);
        if (vendor == null) {
            throw new EntityNotFoundException(name);
        }
        return new VendorDTO(vendor);
    }

    @Override
    public long deleteAll() {
        return vendorRepository.deleteAll();
    }

}
