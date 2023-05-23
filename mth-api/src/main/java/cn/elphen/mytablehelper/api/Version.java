package cn.elphen.mytablehelper.api;

/**
 * Version information about the API module.
 * @author Elphen
 * @since  0.0.1
 */
public final class Version {

    private final static int major = 0;
    private final static int minor = 0;
    private final static int micro = 1;

    public final static String SEPARATOR = ".";


    /**
     * TODO: the version of API module, it must synchronously modify in every new version update
     */
    public final static String API_VERSION =
            String.join(SEPARATOR, String.valueOf(major), String.valueOf(minor), String.valueOf(micro));


}
