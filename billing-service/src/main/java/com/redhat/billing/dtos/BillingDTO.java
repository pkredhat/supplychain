package com.redhat.billing.dtos;
import com.redhat.billing.models.BillingEntity;
import org.bson.types.ObjectId;

import java.util.Date;


public record BillingDTO(String id, String name, String headquarters, Date created) {

    public BillingDTO(BillingEntity p) {
        this(p.getId() == null ? new ObjectId().toHexString() : p.getId().toHexString(), p.getName(),
             p.getHeadquarters(), p.getCreated());
    }

    public BillingEntity toBillingEntity() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new BillingEntity(_id, name, headquarters, created);
    }
}
