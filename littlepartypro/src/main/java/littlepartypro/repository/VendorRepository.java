package littlepartypro.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import littlepartypro.model.Vendor;
import java.util.List;
public interface VendorRepository extends ListCrudRepository<Vendor, Integer> {


    @Query("""
        SELECT * FROM vendors
        WHERE title LIKE '%'||:request||'%'
        OR address LIKE '%'||:request||'%'
        OR short_desc LIKE '%'||:request||'%'
""")
    List<Vendor> findAllByRequest(String request);

    @Query("""
SELECT * FROM vendors
JOIN vendor_type ON vendors.vendor_type_id = vendor_type.id
WHERE vendor_type.vendor_type_name = :vendorType
""")
    List<Vendor> findAllByVendorType(String vendorType);
}



