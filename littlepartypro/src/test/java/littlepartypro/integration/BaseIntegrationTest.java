package littlepartypro.integration;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_METHOD;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.function.Supplier;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import littlepartypro.dto.SearchRequest;

@TestInstance(PER_METHOD)
@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)

public class BaseIntegrationTest {

    @Container
    @SuppressWarnings("rawtypes")
    private static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine");

    @LocalServerPort
    protected int port;

    protected static final RestTemplate restTemplate = new RestTemplate();

    @DynamicPropertySource
    static void registerDynamicProperties(final DynamicPropertyRegistry registry) {
        final Supplier<Object> jdbcUrlSupplier = () -> "jdbc:postgresql://"
            + postgreSQLContainer.getHost() + ":" + postgreSQLContainer.getFirstMappedPort()
            + "/" + postgreSQLContainer.getDatabaseName();

        final Supplier<Object> usernameSupplier = postgreSQLContainer::getUsername;
        final Supplier<Object> passwordSupplier = postgreSQLContainer::getPassword;

        registry.add("spring.datasource.url", jdbcUrlSupplier);
        registry.add("spring.datasource.username", usernameSupplier);
        registry.add("spring.datasource.password", passwordSupplier);

    }


    private final String insertVendorsSQL = """
                INSERT INTO vendors (title, address, postcode, short_desc , long_desc, url, tier_id, vendor_type_id)
                VALUES (:title, :address, :postcode, :shortDesc, :longDesc, :url, :tierId, :vendorTypeId)
        """;

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;

    public SearchRequest searchRequest = new SearchRequest();
}
