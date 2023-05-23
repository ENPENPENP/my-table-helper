package cn.elphen.mytablehelper.api;

import cn.elphen.mytablehelper.api.exception.InvalidEntityException;

/**
 * Verify for <code>Entity</code>.
 * Use it to perform data structure and constraint checks on entity.
 * @author Elphen
 * @since 0.0.1
 */
public interface EntityVerifier {

    /**
     * Verify the integrity of entity object.
     * @param entity entity object
     * @throws InvalidEntityException throw exception when verify failed
     */
    void doVerify(Entity entity);

}
