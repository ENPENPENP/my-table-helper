package cn.elphen.mytablehelper.api;

/**
 * A handler for converting a object to specified Entity object
 * @author Elphen
 * @since 0.0.1
 * @see Entity
 * @param <E> The type which extends or implements <code>Entity</code>
 */
public interface EntityHandler<E extends Entity> {

    E handle(final Object object);

}
