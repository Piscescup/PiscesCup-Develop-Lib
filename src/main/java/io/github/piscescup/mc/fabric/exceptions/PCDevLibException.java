package io.github.piscescup.mc.fabric.exceptions;

/**
 * <h2>Description</h2>
 *
 * <h2>Usages</h2>
 *
 * @author REN YuanTong
 * @Date 2025-07-28
 * @since 1.1.2
 */
public class PCDevLibException extends RuntimeException {
    public PCDevLibException(String message) {
        super(message);
    }

    public PCDevLibException(String message, Throwable cause) {
        super(message, cause);
    }

    public PCDevLibException(Throwable cause) {
        super(cause);
    }

    protected PCDevLibException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PCDevLibException(Class<?> clazz, String message) {
        super("Exception in class: " + clazz.getCanonicalName() + ", caused by: " + message);
    }
}
