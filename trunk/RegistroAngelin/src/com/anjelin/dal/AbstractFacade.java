/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.dal;

import com.anjelin.modelo.Persona;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Clase con los metodos basicos para la persistencia de una entidad JPA
 *
 * @author javrammo
 *
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    public static EntityManager EM = null;
    
    static{
        try {
            EM =Persistence.createEntityManagerFactory("RegistroAngelinPU").createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Entidad pasada como parametro
     */
    private Class<T> entityClass;

    /**
     * Constructor de la clase, con el parametro de entidad
     *
     * @param entityClass
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Crea o persiste la entidad
     *
     * @param entity
     */
    public void crear(T entity) {
        EM.persist(entity);
    }

    /**
     * edita la entidad
     *
     * @param entity
     */
    public void modificar(T entity) {
        //Object objeto=EM.getReference(Persona.class, ((Persona)entity).getId());
        //((Persona)objeto).setPersonaHuellaList(((Persona)entity).getPersonaHuellaList());
        EM.merge(entity);
    }

    /**
     * elimina la entidad
     *
     * @param entity
     */
    public void eliminar(T entity) throws Exception{
        EM.remove(EM.merge(entity));
    }

    /**
     * Busca la entidad por si Id
     *
     * @param id
     * @return
     */
    public T buscar(Object id) {
        return EM.find(entityClass, id);
    }

    /**
     * Retorna una lista con todas las entidades T
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> buscarTodos() {
        final CriteriaQuery cq = EM.getCriteriaBuilder()
                .createQuery();
        cq.select(cq.from(entityClass));
        return EM.createQuery(cq).getResultList();
    }

    /**
     * Busca por rangos.
     *
     * @param range
     * @return
     */
    public List<T> buscarPorRango(int[] range) {

        javax.persistence.criteria.CriteriaQuery cq = EM
                .getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = EM.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * Retrona el tamano de las entidades T
     *
     * @return
     */
    public int contar() {
        javax.persistence.criteria.CriteriaQuery cq = EM
                .getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(EM.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = EM.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * elimina la entidad sin hacer merge
     *
     * @param entity
     * @author grasotos
     *
     */
    public void remover(T entity) {
        EM.remove(entity);
    }

    //PSP_METRICS_METHOD_BEGIN: consultarQuery
    /**
     * Consulta un query parametrizado en los XML
     *
     *
     * @author javrammo
     * @param <T> tipo de entidad que va a mapear la consulta
     * @param nombreConculta -- Nombre de la consulta a buscar en los
     * query-*.xml
     * @param parametros -- Var-args con los parametros de la consulta
     * @return <T> List<T>
     */
    protected <T> List<T> consultarQuery(Class<T> tipoRetorno, final String nombreConculta, Object... parametros) {
        List<T> resultado = null;
        final Query query = EM.createNamedQuery(nombreConculta);
        int indiceParametro = 1;
        if (parametros != null && parametros.length > 0) {
            for (Object param : parametros) {
                query.setParameter(indiceParametro++, param);
            }
        }
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        resultado = query.getResultList();

        return resultado;
    }
    // PSP_METRICS_METHOD_END
}
