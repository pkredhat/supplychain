package com.redhat.billing.controllers;

import com.redhat.billing.dtos.BillingDTO;
import com.redhat.billing.exceptions.EntityNotFoundException;
import com.redhat.billing.services.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BillingController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BillingController.class);
    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("billing")
    @ResponseStatus(HttpStatus.CREATED)
    public BillingDTO postBilling(@RequestBody BillingDTO billingDTO) {
        LOGGER.info("POST /billing {}", billingDTO.name());
        return billingService.save(billingDTO);
    }

    @GetMapping("billings")
    public List<BillingDTO> getBillings() {
        LOGGER.info("GET /billings");
        return billingService.findAll();
    }

    @GetMapping("billing/id/{id}")
    public BillingDTO getBillingById(@PathVariable("id") String id) {
        LOGGER.info("GET /billings/id/{}", id);
        return billingService.findOneById(id);
    }

    @GetMapping("billing/name/{name}")
    public BillingDTO getBillingByName(@PathVariable("name") String name) {
        LOGGER.info("GET /billings/name/{}", name);
        return billingService.findOneByName(name);
    }

    @DeleteMapping("billings")
    public long deleteBillings() {
        LOGGER.info("DELETE /billings");
        return billingService.deleteAll();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "MongoDB didn't find any document.")
    public final void handleNotFoundExceptions(EntityNotFoundException e) {
        LOGGER.info("=> Billing not found: {}", e.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public final void handleAllExceptions(RuntimeException e) {
        LOGGER.error("=> Internal server error.", e);
    }
}
