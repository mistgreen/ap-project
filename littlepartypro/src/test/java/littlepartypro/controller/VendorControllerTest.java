package littlepartypro.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import littlepartypro.dto.SearchRequest;
import littlepartypro.model.Vendor;
import littlepartypro.repository.VendorRepository;

@ExtendWith(MockitoExtension.class)
class VendorControllerTest {

    @Mock
    private VendorRepository repository;
    @InjectMocks
    private VendorController vendorController;

    @Test
    void whenFindAllShouldCallFindAllOnVendorRepository() {
        vendorController.getAllVendors();
        verify(repository).findAll();
    }

    @Test
    void whenFindAllShouldReturnCorrectListOfVendors() {
        Vendor vendor = new Vendor(1, "A", "10 Street", "OL10 3ST", "planned party", "soft play provider", 1, 1, "");
        List<Vendor> expectedList = List.of(vendor);
        when(repository.findAll()).thenReturn(expectedList);

        List<Vendor> vendorList = vendorController.getAllVendors();

        assertEquals(expectedList, vendorList);
    }

    @Test
    void whenFilterByTypeShouldReturnCorrectVendorType() {
        Vendor vendorOne = new Vendor(1, "A", "10 Street", "OL10 3ST", "planned party", "soft play provider", 1, 1, "");
        Vendor vendorTwo = new Vendor(2, "B", "30 Road", "M2 5JG", "planned party", "soft play provider", 1, 1, "");
        List<Vendor> expectedList = List.of(vendorOne, vendorTwo);

        when(vendorController.getVendorsByVendorType("VENUE")).thenReturn(expectedList);
        assertEquals(expectedList, vendorController.getVendorsByVendorType("VENUE"));
    }

    @Test
    void whenFilterByRequestShouldCallGetSearchQueryOnRepository() {
        SearchRequest mockedSearch = new SearchRequest("request string");
        vendorController.getVendorBySearchRequest(mockedSearch);

        verify(repository).findAllByRequest("request string");
    }

    @Test
    void whenFilterByRequestWithValidQueryShouldReturnCorrectVendor() {
        Vendor vendorOne = new Vendor(1, "A", "10 Street", "OL10 3ST", "planned party", "soft play provider", 1, 1, "");
        Vendor vendorTwo = new Vendor(2, "B", "30 Road", "M2 5JG", "planned party", "soft play provider", 1, 1, "");
        SearchRequest mockedSearch = new SearchRequest("request string");
        List<Vendor> expectedList = List.of(vendorOne, vendorTwo);
        when(vendorController.getVendorBySearchRequest(mockedSearch)).thenReturn(expectedList);

        assertEquals(expectedList, vendorController.getVendorBySearchRequest(mockedSearch));
    }


}