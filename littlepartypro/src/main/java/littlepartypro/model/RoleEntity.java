package littlepartypro.model;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@Table("roles")
public class RoleEntity {
    @Id
    private int id;
    private String roleName;

}
