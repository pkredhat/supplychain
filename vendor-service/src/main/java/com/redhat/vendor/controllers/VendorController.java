package com.redhat.vendor.controllers;

import com.redhat.vendor.dtos.VendorDTO;
import com.redhat.vendor.exceptions.EntityNotFoundException;
import com.redhat.vendor.services.VendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VendorController {

    private final static Logger LOGGER = LoggerFactory.getLogger(VendorController.class);
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("vendor")
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO postVendor(@RequestBody VendorDTO vendorDTO) {
        LOGGER.info("POST /vendor {}", vendorDTO.name());
        return vendorService.save(vendorDTO);
    }

    @GetMapping("vendors")
    public List<VendorDTO> getVendors() {
        LOGGER.info("GET /vendors");
        return vendorService.findAll();
    }

    @GetMapping("vendor/id/{id}")
    public VendorDTO getVendorById(@PathVariable("id") String id) {
        LOGGER.info("GET /vendors/id/{}", id);
        return vendorService.findOneById(id);
    }

    @GetMapping("vendor/name/{name}")
    public VendorDTO getVendorByName(@PathVariable("name") String name) {
        LOGGER.info("GET /vendors/name/{}", name);
        return vendorService.findOneByName(name);
    }

    @DeleteMapping("vendors")
    public long deleteVendors() {
        LOGGER.info("DELETE /vendors");
        return vendorService.deleteAll();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "MongoDB didn't find any document.")
    public final void handleNotFoundExceptions(EntityNotFoundException e) {
        LOGGER.info("=> Vendor not found: {}", e.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public final void handleAllExceptions(RuntimeException e) {
        LOGGER.error("=> Internal server error.", e);
    }
}
