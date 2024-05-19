package com.redhat.billing.services;

import com.redhat.billing.dtos.BillingDTO;
import com.redhat.billing.exceptions.EntityNotFoundException;
import com.redhat.billing.models.BillingEntity;
import com.redhat.billing.repositories.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;

    public BillingServiceImpl(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    @Override
    public BillingDTO save(BillingDTO BillingDTO) {
        return new BillingDTO(billingRepository.save(BillingDTO.toBillingEntity()));
    }

    @Override
    public List<BillingDTO> findAll() {
        return billingRepository.findAll().stream().map(BillingDTO::new).toList();
    }

    @Override
    public BillingDTO findOneById(String id) {
        BillingEntity billing = billingRepository.findOneById(id);
        if (billing == null) {
            throw new EntityNotFoundException(id);
        }
        return new BillingDTO(billing);
    }

    @Override
    public BillingDTO findOneByName(String name) {
        BillingEntity billing = billingRepository.findOneByName(name);
        if (billing == null) {
            throw new EntityNotFoundException(name);
        }
        return new BillingDTO(billing);
    }

    @Override
    public long deleteAll() {
        return billingRepository.deleteAll();
    }

}
