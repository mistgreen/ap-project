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
    }

    @Test
    void givenVendorsExistInDatabase_whenRequestToGetVendorByValidType_shouldReturnAllVendorsOfThatType() {
        final ResponseEntity<Vendor[]> response = restTemplate.getForEntity(getVendorByTypeURI("VENUE"), Vendor[].class);
        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().length).isEqualTo(5);
        Arrays.stream(response.getBody()).forEach(vendor -> assertThat(vendor.vendorTypeId()).isEqualTo(1));
    }

    @Test
    void givenVendorsExistInDatabase_whenRequestToGetVendorsByTypeIsInvalid_shouldReturnAnError() {
        final ResponseEntity<Vendor[]> response = restTemplate.getForEntity(getVendorByTypeURI("Foo"), Vendor[].class);
        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    void givenVendorsExistInDatabase_whenSearchRequestIsProvided_shouldReturnCorrectVendors() {
        final ResponseEntity<Vendor[]> response = restTemplate.getForEntity(getVendorBySearchRequestURI("Foo"), Vendor[].class);
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

    private URI getVendorBySearchRequestURI(final String searchRequest) {
        return UriComponentsBuilder
            .fromHttpUrl("http://localhost:" + port + "/api/vendor/filter/search")
            .build()
            .toUri();
    }
}

