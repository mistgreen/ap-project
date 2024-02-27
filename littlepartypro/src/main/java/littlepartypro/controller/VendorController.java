package littlepartypro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import littlepartypro.dto.SearchRequest;
import littlepartypro.model.Vendor;
import littlepartypro.repository.VendorRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vendor")
@CrossOrigin //TODO may want to update this to change permissions to front end
@RequiredArgsConstructor
public class VendorController {

    private final VendorRepository repository;

    @GetMapping
    public List<Vendor> getAllVendors() {
        return repository.findAll();
    }

    @GetMapping("/filter/vendorType/{vendorType}")
    public List<Vendor> getVendorsByVendorType(@PathVariable String vendorType) {
        return repository.getAllVendorsByVendorType(vendorType);
    }

    @PostMapping("/filter/search")
    public List<Vendor> getVendorBySearchRequest(@RequestBody SearchRequest request) {
        String searchQuery = request.getSearchQuery();
        return repository.getAllVendorsByRequest(searchQuery);
    }
}