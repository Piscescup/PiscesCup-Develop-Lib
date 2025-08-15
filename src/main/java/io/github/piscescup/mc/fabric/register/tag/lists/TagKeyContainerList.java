package io.github.piscescup.mc.fabric.register.tag.lists;

import io.github.piscescup.mc.fabric.register.tag.container.TagKeyContainer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A typed collection wrapper that groups together multiple {@link TagKeyContainer}
 * instances and exposes common operations for working with them.
 * <p>
 * Implementations typically use this interface when they need to manage several
 * tag–value containers under the same “key space” (for example, one container
 * per plug-in, module, or logical grouping).  By centralizing access, client
 * code can iterate over or manipulate all containers without knowing the
 * concrete implementation details of each one.
 * </p>
 *
 * <p>
 *     The implementation of this interface should be a singleton.
 * </p>
 *
 * <h2>Usage</h2>
 * <pre>{@code
 * public class PCBlockTagKeyContainerList
 *     implements TagKeyContainerList<PCBlockTagContainer>
 * {
 *     private static volatile PCBlockTagKeyContainerList INSTANCE;
 *
 *     private final List<PCBlockTagContainer> itemTagMap = new ArrayList<>();
 *
 *     private PCBlockTagKeyContainerList() {}
 *
 *     public static PCBlockTagKeyContainerList instance() {
 *         if (INSTANCE == null) {
 *             synchronized (PCItemTagKeyContainerList.class) {
 *                 if (INSTANCE == null) {
 *                     INSTANCE = new PCBlockTagKeyContainerList();
 *                 }
 *             }
 *         }
 *         return INSTANCE;
 *     }
 *     @Override
 *     public List<PCBlockTagContainer> getContainerList() {
 *         return itemTagMap;
 *     }
 *
 *     @Override
 *     public boolean addContainer(@NotNull PCBlockTagContainer blockTag) {
 *         Objects.requireNonNull(blockTag);
 *         return this.itemTagMap.add(blockTag);
 *     }
 * }
 * }</pre>
 * @param <C> the concrete subtype of {@code TagContainer} that this list holds.
 * @author REN YuanTong
 * @since 1.1.0
 * @see TagKeyContainer
 */
public interface TagKeyContainerList<C extends TagKeyContainer<?>> {
    /**
     * Returns the internal list of containers.
     * <p>
     * Whether the returned list is mutable depends on the concrete
     * implementation.  Callers should treat it as <em>read-only</em> unless the
     * implementing class explicitly documents otherwise.
     *
     * @return a list containing all managed containers, ordered according to
     *         the rules defined by the implementation (often insertion order)
     */
    List<C> getContainerList();

    /**
     * Adds the specified container to this list.
     *
     * @param container the {@code TagContainer} instance to add;
     *                  must not be {@code null}
     * @return {@code true} if the container was added, or {@code false} if the
     *         list already contained an equivalent instance or the
     *         implementation otherwise refuses the addition
     * @throws NullPointerException if {@code container} is {@code null} and the
     *         implementation does not accept {@code null} values
     */
    boolean addContainer(@NotNull C container);
}
