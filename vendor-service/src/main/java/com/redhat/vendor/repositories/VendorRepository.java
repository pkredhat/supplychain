package com.redhat.vendor.repositories;

import com.redhat.vendor.models.VendorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository {

    VendorEntity save(VendorEntity vendorEntity);

    List<VendorEntity> findAll();

    VendorEntity findOneById(String id);

    VendorEntity findOneByName(String name);

    long deleteAll();
}
