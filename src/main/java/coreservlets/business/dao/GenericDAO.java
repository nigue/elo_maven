package coreservlets.business.dao;

import coreservlets.business.model.IdEntity;
import java.util.List;

/**
 * Interfaz para un DAO generico que sirve como base para toda entidad del
 * sistema.
 * @author Ariel Aguayo B. (ariel.aguayo@orangepeople.cl)
 * @param <T>
 * @version 1.0
 */
public interface GenericDAO<T extends IdEntity> {

    /**
     * Hace persistente un objeto
     * @param object objeto que se desea persistir
     * @return de persistent object
     */
    T persist(T object);
    /**
     * Elimina un objeto
     * @param object el objeto que se desea eliminar
     */
    void delete(T object);
    /**
     * Encuentra un objeto a partir de su identificador unico
     * @param id identificador del objeto
     * @return el objeto encontado (null en caso de que no exista)
     */
    T find(long id);
    /**
     * Obtiene todos los objetos
     * @return lista de todos los objetos
     */
    List<T> findAll();
    /**
     * Obtiene la entidad asociada al DAO
     * @return la entidad
     */
    Class getEntityClass();

}
