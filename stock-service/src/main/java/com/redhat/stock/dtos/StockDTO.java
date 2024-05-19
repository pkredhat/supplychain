package com.redhat.stock.dtos;
import com.redhat.stock.models.StockEntity;
import org.bson.types.ObjectId;

import java.util.Date;


public record StockDTO(String id, String name, String headquarters, Date created) {

    public StockDTO(StockEntity p) {
        this(p.getId() == null ? new ObjectId().toHexString() : p.getId().toHexString(), p.getName(),
             p.getHeadquarters(), p.getCreated());
    }

    public StockEntity toStockEntity() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new StockEntity(_id, name, headquarters, created);
    }
}
