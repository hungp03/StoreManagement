
package app.storemanagement.controller;

import app.storemanagement.model.BaseEntity;

/**
 *
 * @author Hung Pham
 * @param <T>
 */
public interface BaseController<T extends BaseEntity> {
    boolean add(T entity);
    boolean update(T entity);
    boolean delete(T entity);
}
