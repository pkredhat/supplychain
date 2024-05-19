package com.redhat.vendor.dtos;
import com.redhat.vendor.models.VendorEntity;
import org.bson.types.ObjectId;

import java.util.Date;


public record VendorDTO(String id, String name, String headquarters, Date created) {

    public VendorDTO(VendorEntity p) {
        this(p.getId() == null ? new ObjectId().toHexString() : p.getId().toHexString(), p.getName(),
             p.getHeadquarters(), p.getCreated());
    }

    public VendorEntity toVendorEntity() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new VendorEntity(_id, name, headquarters, created);
    }
}
