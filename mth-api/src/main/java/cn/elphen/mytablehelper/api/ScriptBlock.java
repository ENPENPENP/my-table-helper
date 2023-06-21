package cn.elphen.mytablehelper.api;

/**
 * A container for the database scripts, supported storage multi line script.
 * <p><strong>Note:</strong> The container should storage and output the scripts sequential.</p>
 *
 * @author Elphen
 * @since 0.0.1
 */
public interface ScriptBlock<S> extends Iterable<S>, Wrapper {

    /**
     * The line count of script block.
     *
     * @return line count
     */
    long count();

    /**
     * The script of specified line number.
     *
     * @param lineNumber line number
     * @return the script if specified line existed
     * @throws IndexOutOfBoundsException throw the exception if line number is out of bounds of the total line range
     *                                   or less than zero.
     */
    S get(int lineNumber);

}
