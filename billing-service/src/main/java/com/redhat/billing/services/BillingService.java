package com.redhat.billing.services;

import com.redhat.billing.dtos.BillingDTO;
import java.util.List;


public interface BillingService {

    BillingDTO save(BillingDTO BillingDTO);

    List<BillingDTO> findAll();

    BillingDTO findOneById(String id);

    BillingDTO findOneByName(String name);

    long deleteAll();
}
