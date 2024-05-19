package com.redhat.billing.repositories;

import com.redhat.billing.models.BillingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository {

    BillingEntity save(BillingEntity billingEntity);

    List<BillingEntity> findAll();

    BillingEntity findOneById(String id);

    BillingEntity findOneByName(String name);

    long deleteAll();
}
