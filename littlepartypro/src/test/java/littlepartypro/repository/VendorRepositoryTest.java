package littlepartypro.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import littlepartypro.model.Vendor;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class VendorRepositoryTest {

    @Mock
    private VendorRepository vendorRepositoryMock;

    @Test
    public void testFindAllByRequest() {
        // Mock data
        String request = "test";
        List<Vendor> mockVendors = Arrays.asList(
            new Vendor(1, "Test Vendor 1", "Test Address 1", "12345", "Short desc 1", "Long desc 1", 1, 1, "http://test1.com"),
            new Vendor(2, "Test Vendor 2", "Test Address 2", "67890", "Short desc 2", "Long desc 2", 1, 2, "http://test2.com")
        );

        // Mock behavior
        when(vendorRepositoryMock.getAllVendorsByRequest(request)).thenReturn(mockVendors);

        // Test
        List<Vendor> foundVendors = vendorRepositoryMock.getAllVendorsByRequest(request);
        assertEquals(mockVendors.size(), foundVendors.size());
        assertEquals(mockVendors, foundVendors);
    }

    @Test
    public void testFindAllByVendorType() {
        // Mock data
        String vendorType = "VENUE";
        List<Vendor> mockVendors = Arrays.asList(
            new Vendor(1, "Test Vendor 1", "Test Address 1", "12345", "Short desc 1", "Long desc 1", 1, 1, "http://test1.com"),
            new Vendor(2, "Test Vendor 2", "Test Address 2", "67890", "Short desc 2", "Long desc 2", 1, 1, "http://test2.com")
        );

        // Mock behavior
        when(vendorRepositoryMock.getAllVendorsByVendorType(vendorType)).thenReturn(mockVendors);

        // Test
        List<Vendor> foundVendors = vendorRepositoryMock.getAllVendorsByVendorType(vendorType);
        assertEquals(mockVendors.size(), foundVendors.size());
        assertEquals(mockVendors, foundVendors);
    }
}

