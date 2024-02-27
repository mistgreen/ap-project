package littlepartypro.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.junit.jupiter.Testcontainers;

import littlepartypro.model.Vendor;

@Testcontainers
@ContextConfiguration
public class IntegrationTest extends BaseIntegrationTest {

    @Test
    void givenVendorsExistInDatabase_whenRequestToGetAllVendors_shouldReturnAllVendors() {
        final ResponseEntity<Vendor[]> response = restTemplate.getForEntity(getAllVendorsURI(), Vendor[].class);
        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().length).isEqualTo(10);

//        final Vendor vendor = new Vendor(null, "My Vendor", "Manchester", "M307HZ", "Cake Factory", "Very Big Cake Factory", 1, 1, "www.thecakefactory.co.uk");
//
//        insertVendor(vendor);
//
//        final ResponseEntity<Vendor[]> response2 = restTemplate.getForEntity(getAllVendorsURI(), Vendor[].class);
//
//        assertEquals(response2.getStatusCode(), HttpStatusCode.valueOf(200));
//        assertThat(response2.getBody()).isNotEmpty();
//        assertThat(response2.getBody().length).isEqualTo(11);
    }

    @Test
    void givenVendorsExistInDatabase_whenRequestToGetVendorByType_shouldReturnAllVendorsOfThatType() {
        final ResponseEntity<Vendor[]> response = restTemplate.getForEntity(getVendorByTypeURI("VENUE"), Vendor[].class);
        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().length).isEqualTo(5);
        Arrays.stream(response.getBody()).forEach(vendor -> assertThat(vendor.vendorTypeId()).isEqualTo(1));
    }


    private URI getAllVendorsURI() {
        return UriComponentsBuilder
            .fromHttpUrl("http://localhost:" + port + "/api/vendor")
            .build()
            .toUri();
    }

    private URI getVendorByTypeURI(final String vendorType) {
        return UriComponentsBuilder
            .fromHttpUrl("http://localhost:" + port + "/api/vendor/filter/vendorType/" + vendorType)
            .build()
            .toUri();
    }
}

