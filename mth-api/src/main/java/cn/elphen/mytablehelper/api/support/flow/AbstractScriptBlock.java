package cn.elphen.mytablehelper.api.support.flow;

import cn.elphen.mytablehelper.api.ScriptBlock;

import java.util.List;

/**
 * @author Elphen
 */
public abstract class AbstractScriptBlock<S> extends AbstractWrapper implements ScriptBlock<S> {

    public static final char DEFAULT_SEPARATOR = ';';

    /**
     * The line separator used to split the original script string, default value is ";".
     */
    protected final char separator;

    /**
     * Original script string.
     */
    protected final String originalScript;

    public AbstractScriptBlock(char separator, String originalScript) {
        if (originalScript == null || originalScript.length() == 0) {
            throw new IllegalArgumentException("The original script must not blank.");
        }
        this.separator = separator;
        this.originalScript = originalScript;
        doSplitString();
    }

    public AbstractScriptBlock(String originalScript) {
        if (originalScript == null || originalScript.length() == 0) {
            throw new IllegalArgumentException("The original script must not blank.");
        }
        this.separator = DEFAULT_SEPARATOR;
        this.originalScript = originalScript;
        doSplitString();
    }

    public char getSeparator() {
        return separator;
    }

    public String getOriginalScript() {
        return originalScript;
    }

    /**
     * The actual operation to split script string.
     * @return all line split sequential from the original script, removed all blank string i.g. <code>'',"","";,'';</code>.
     */
    protected abstract List<S> doSplitString();
}
