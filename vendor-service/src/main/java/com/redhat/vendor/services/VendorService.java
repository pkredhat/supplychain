package com.redhat.vendor.services;

import com.redhat.vendor.dtos.VendorDTO;
import java.util.List;


public interface VendorService {

    VendorDTO save(VendorDTO VendorDTO);

    List<VendorDTO> findAll();

    VendorDTO findOneById(String id);

    VendorDTO findOneByName(String name);

    long deleteAll();
}
