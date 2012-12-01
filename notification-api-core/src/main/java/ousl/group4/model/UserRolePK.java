package ousl.group4.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRolePK extends BaseModelObject implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
