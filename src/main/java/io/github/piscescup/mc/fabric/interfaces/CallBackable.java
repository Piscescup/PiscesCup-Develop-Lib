package io.github.piscescup.mc.fabric.interfaces;

/**
 * Represents a component that can accept and expose a {@link CallBack} implementation.
 *
 * <p>The generic type parameter {@code T} narrows the accepted callback to a specific subtype
 * of {@code CallBack}.  This allows clients to declare richer, domain-specific callback
 * contracts while preserving type safety. </p>
 * <p>
 *     Below is a simple example of a {@code CallBackable} implementation:
 * </p>
 *
 * <pre>{@code
 * public class Job
 *     implements CallBackable<JobCallback>
 * {
 *     private JobCallback callback;
 *
 *     @Override
 *     public void setCallBack(JobCallback callback) {
 *         this.callback = callback;
 *     }
 *
 *     @Override
 *     public JobCallback getCallBack() {
 *         return callback;
 *     }
 *
 *     public void run() {
 *         // …
 *         if (callback != null) {
 *             callback.onComplete();
 *         }
 *     }
 * }
 * }</pre>
 *
 * <h2>Thread safety</h2>
 * <p>Implementations should document whether they are thread-safe and how the callback is
 * invoked (synchronously, asynchronously, on which thread, etc.) so that callers can
 * reason about concurrency guarantees.</p>
 *
 * @param <T> the specific type of callback accepted and returned
 *
 * @author REN&nbsp;YuanTong
 * @since 1.1.0
 */
public interface CallBackable<T extends CallBack> {
    /**
     * Registers the given callback instance.  Passing {@code null} typically disables
     * callback notifications, though exact semantics are implementation-defined.
     *
     * @param callBack the callback to register, or {@code null} to clear
     */
    void setCallBack(T callBack);

    /**
     * Returns the currently registered callback, or {@code null} if none has been set.
     *
     * @return the active callback, or {@code null}
     */
    T getCallBack();
}
