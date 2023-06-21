package cn.elphen.mytablehelper.api.support.flow;

import cn.elphen.mytablehelper.api.exception.UnExpectedTokenException;

import java.util.*;

/**
 * @author Elphen
 */
public class StringScriptBlock extends AbstractScriptBlock<String> {

    private final static String BLANK_SINGLE_QUOTER = "''";
    private final static String BLANK_SINGLE_QUOTER_COLON = "'';";
    private final static String BLANK_DOUBLE_QUOTER = "\"\"";
    private final static String BLANK_DOUBLE_QUOTER_COLON = "\"\";";
    private final static Set<String> SKIPPED_BLANK_STRING = new HashSet<>(Arrays.asList(BLANK_SINGLE_QUOTER,
            BLANK_SINGLE_QUOTER_COLON, BLANK_DOUBLE_QUOTER, BLANK_DOUBLE_QUOTER_COLON));


    private final List<String> lines;

    public StringScriptBlock(char separator, String originalScript) {
        super(separator, originalScript);
        lines = doSplitString();
    }

    public StringScriptBlock(String originalScript) {
        super(originalScript);
        lines = doSplitString();
    }

    @Override
    public long count() {
        return lines.size();
    }

    @Override
    public String get(int lineNumber) {
        return lines.get(lineNumber);
    }

    @Override
    public Iterator<String> iterator() {
        return lines.iterator();
    }

    @Override
    protected List<String> doSplitString() {
        LinkedList<String> linkedList = new LinkedList<>();

        // record the last one char iterated
        Character preChar = null;
        // record the last one quoter iterated
        Character preQuoter = null;
        // is in quoter windows
        boolean quoted = false;

        final char[] charArray = this.originalScript.toCharArray();
        StringBuilder lineBuffer = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            final char c = charArray[i];

            // remove useless char
            if (c == '\r' || c == '\t' || c == '\n') {
                continue;
            }
            lineBuffer.append(c);

            if (i + 1 == charArray.length) {
                if (quoted && preQuoter != c) {
                    throw new UnExpectedTokenException(String.format("Invalid token at index<%s>" + " : '%s'," +
                                    "if single or double quotation marks are used, check whether the input is correct.",
                            i, c));
                }
                String lineString = lineBuffer.toString();
                if (!SKIPPED_BLANK_STRING.contains(lineString)) {
                    linkedList.addLast(lineBuffer.toString());
                    lineBuffer = new StringBuilder();
                }
                continue;
            }

            // met quotation char
            if (c == '\'' || c == '"') {
                if (quoted) {
                    if (preQuoter == c) {
                        quoted = false;
                        preQuoter = null;
                    } else {
                        throw new UnExpectedTokenException(String.format("Invalid token at index<%s>: '%s',if single" +
                                        " or double quotation marks are used, check whether the input is correct.",
                                i, c));
                    }
                } else {
                    quoted = true;
                    preQuoter = c;
                }
            }
            // met separator char
            else if (c == separator) {
                if (!quoted) {
                    if (preChar != null && preChar != separator) {
                        String lineString = lineBuffer.toString();
                        if (!SKIPPED_BLANK_STRING.contains(lineString)) {
                            linkedList.addLast(lineBuffer.toString());
                            lineBuffer = new StringBuilder();
                        }
                    }
                }
            }

            preChar = c;
        }
        return linkedList;
    }

    @Override
    public String toString() {
        return "StringScriptBlock{" +
                "lines=" + lines +
                '}';
    }
}
