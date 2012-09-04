package coreservlets.business.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Clase base para todos los bean's de la aplicacion. Implementa serializable
 * e implementa el metodo toString() de forma conveniente para mejor logeo y
 * visualizacion de los objetos en tiempo de debug.
 * @author ariel
 * @version 1.0
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = -5747159829033690039L;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(
                this, ToStringStyle.SIMPLE_STYLE);
    }
}
