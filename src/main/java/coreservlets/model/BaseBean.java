package coreservlets.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BaseBean implements Serializable {

    private static final long serialVersionUID = -5747159829033690039L;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(
                this, ToStringStyle.SIMPLE_STYLE);
    }
}
