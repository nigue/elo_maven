package coreservlets.business.model;

import coreservlets.model.BaseBean;
import org.apache.commons.lang.ObjectUtils;

public abstract class IdEntity extends BaseBean {

    private static final long serialVersionUID = 2202015144287538980L;

    public abstract Long getId();

    public abstract void setId(Long id);

    @Override
    public int hashCode() {
        int result = 0;
        if (this.getId() != null) {
            result = this.getId().hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o != null && getClass().isAssignableFrom(o.getClass())) {
            IdEntity entity = (IdEntity) o;
            result = ObjectUtils.equals(this.getId(), entity.getId());
        }
        return result;
    }
}
