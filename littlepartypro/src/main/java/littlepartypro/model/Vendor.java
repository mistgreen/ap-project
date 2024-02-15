package littlepartypro.model;

import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotBlank;

@Table("vendors")
public record Vendor(
    @Id
    Integer id,
    @NotBlank
    String title,
    @NotBlank
    String address,
    @NotBlank
    String postcode,
    @Column("short_desc")
    String shortDesc,
    @Column("long_desc")
    String longDesc,
    @Column("tier_id")
    Integer tierId,
    @Column("vendor_type_id")
    Integer vendorTypeId,
    String url
) {

}
