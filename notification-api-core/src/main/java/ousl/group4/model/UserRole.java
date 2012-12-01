package ousl.group4.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
public class UserRole extends BaseModelObject implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "user_id")),
            @AttributeOverride(name = "roleId", column = @Column(name = "role_id"))
    })
    private UserRolePK primaryKey = new UserRolePK();

    public UserRolePK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(UserRolePK primaryKey) {
        this.primaryKey = primaryKey;
    }

}
