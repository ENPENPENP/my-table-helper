package cn.elphen.mytablehelper.api;

/**
 * A consumer type class for generating a executable database script by a parsed model.
 *
 * @author Elphen Liu
 * @since 0.0.1
 */
public interface ScriptGenerator {

    /**
     * Generate a database script by a parsed model.
     *
     * @param entity parsed model
     * @return generated script
     * @see Model
     */
    String generateScript(Entity entity);

}
