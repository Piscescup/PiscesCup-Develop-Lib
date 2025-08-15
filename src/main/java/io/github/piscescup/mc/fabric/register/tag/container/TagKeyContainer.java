package io.github.piscescup.mc.fabric.register.tag.container;

import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * <h2>Description</h2>
 * A {@code TagContainer<T>} represents a container that groups a primary
 * {@link TagKey<T> target tag} with the elements and nested tags associated
 * with it. This interface defines methods for querying, adding, and removing
 * both the individual items of type {@code T} and additional {@code TagKey<T>}
 * instances that form a hierarchical tagging structure.
 *
 * <h2>Key Concepts</h2>
 * <ul>
 *   <li><b>Target tag:</b> The main {@code TagKey<T>} for which this container
 *       holds direct associations.</li>
 *   <li><b>Contained things:</b> Elements of type {@code T} directly assigned to
 *       the {@code TagKey}.</li>
 *   <li><b>Contained tags:</b> Other {@code TagKey<T>} instances nested beneath
 *       the primary tag, enabling multi-level tag hierarchies.</li>
 * </ul>
 *
 * <h2>Construction and Copying</h2>
 * <p>Implementations of this interface typically provide at least two ways to construct or copy instances:</p>
 * <ul>
 *   <li><b>Private constructor + Static Factory Method:</b> for initializing a container with a given tag:</li>
 * </ul>
 * <pre><code>
 * private PCBlockTagContainer(TagKey<Block> tag) {
 *     this.tag = tag;
 * }
 *
 * public static PCBlockTagContainer createFor(TagKey<Block> tag) {
 *     return new PCBlockTagContainer(tag);
 * }
 * </code></pre>
 * <ul>
 *   <li><b>Copy from Another Constructor:</b> for creating a new container from an existing one, preserving its state:</li>
 * </ul>
 * <pre><code>
 * private PCBlockTagContainer() {}
 *
 * public static @NotNull PCBlockTagContainer createFrom(PCBlockTagContainer other) {
 *     PCBlockTagContainer container = new PCBlockTagContainer();
 *     container.tag = other.tag;
 *     container.blocks.addAll(other.blocks);
 *     container.tags.addAll(other.tags);
 *     return container;
 * }
 * </code></pre>
 *
 * <h2>Optional Operations</h2>
 * <p>Methods that modify the container (such as {@code addContainedThing},
 * {@code removeContainedThing}, {@code addContainedTag}, etc.) are <i>optional
 * operations</i>. If an implementation does not support a given mutator, it
 * <i>must</i> throw {@link UnsupportedOperationException}.</p>
 *
 * <h2>Immutability and Thread Safety</h2>
 * <p>This interface does not mandate any particular synchronization or
 * immutability policy. If an implementation wishes to be thread-safe,
 * it must document its concurrency guarantees. In the absence of such
 * guarantees, concurrent modification by multiple threads results in
 * undefined behavior.</p>
 *
 * <h2>View vs. Storage</h2>
 * <p>Query methods ({@code getContainedThings}, {@code getContainedTags})
 * SHOULD return unmodifiable views of the underlying collections, or make
 * defensive copies, to prevent accidental external modification if the
 * implementation is mutable. Changes to the returned list should not
 * affect the container (unless explicitly documented).</p>
 *
 * @param <T> the type of elements managed by this container
 * @author REN YuanTong
 * @since 1.1.0
 */
public interface TagKeyContainer<T> {

    /**
     * Returns the primary {@code TagKey<T>} that this container represents.
     *
     * @return the target tag key
     */
    TagKey<T> getTargetTag();

    /**
     * Returns a view of the elements of type {@code T} currently contained
     * in this container. The returned list is expected to be safe for iteration
     * and should not allow modification unless explicitly documented.
     *
     * @return an unmodifiable list of contained elements
     */
    List<T> getContainedThings();

    /**
     * Returns a view of the nested {@code TagKey<T>} instances in this container.
     * These represent sub-tags grouped under the target tag.
     *
     * @return an unmodifiable list of nested tag keys
     */
    List<TagKey<T>> getContainedTags();

    /**
     * Adds the specified element to this container.
     *
     * @param thing the element to add
     * @return {@code true} if the container changed as a result
     * @throws UnsupportedOperationException if this operation is not supported
     */
    boolean addContainedThing(@NotNull T thing);

    /**
     * Adds all the elements in the provided collection to this container.
     *
     * @param things the collection of elements to add
     * @return {@code true} if the container changed as a result
     * @throws UnsupportedOperationException if this operation is not supported
     * @throws NullPointerException if {@code things} is null or contains nulls
     */
    boolean addContainedThings(@NotNull List<T> things);

    /**
     * Removes the specified element from this container, if present.
     *
     * @param thing the element to remove
     * @return {@code true} if the container changed as a result
     * @throws UnsupportedOperationException if this operation is not supported
     */
    boolean removeContainedThing(@NotNull T thing);

    /**
     * Adds the specified nested tag key to this container.
     *
     * @param tag the tag key to add
     * @return {@code true} if the container changed as a result
     * @throws UnsupportedOperationException if this operation is not supported
     */
    boolean removeContainedThings(@NotNull List<T> things);

    /**
     * Adds the specified nested tag key to this container.
     *
     * @param tag the tag key to add
     * @return {@code true} if the container changed as a result
     * @throws UnsupportedOperationException if this operation is not supported
     */
    boolean addContainedTag(@NotNull TagKey<T> tag);

    /**
     * Adds all the provided nested tag keys to this container.
     *
     * @param tags the collection of tag keys to add
     * @return {@code true} if the container changed as a result
     * @throws UnsupportedOperationException if this operation is not supported
     * @throws NullPointerException if {@code tags} is null or contains nulls
     */
    boolean addContainedTags(@NotNull List<TagKey<T>> tags);

    /**
     * Removes the specified nested tag key from this container.
     *
     * @param tag the tag key to remove
     * @return {@code true} if the container changed as a result
     * @throws UnsupportedOperationException if this operation is not supported
     */
    boolean removeContainedTag(@NotNull TagKey<T> tag);

    /**
     * Removes the specified nested tag keys from this container.
     *
     * @param tags the collection of tag keys to remove
     * @return {@code true} if the container changed as a result
     * @throws UnsupportedOperationException if this operation is not supported
     */
    boolean removeContainedTags(@NotNull List<TagKey<T>> tags);

    /**
     * Returns {@code true} if the container has no elements or nested tags.
     *
     * @return {@code true} if empty
     */
    boolean isEmpty();

    /**
     * Returns {@code true} if the specified element is present in this container.
     *
     * @param thing the element to check
     * @return {@code true} if present
     */
    boolean containsThing(@NotNull T thing);

    /**
     * Returns {@code true} if the specified tag key is nested in this container.
     *
     * @param tag the tag key to check
     * @return {@code true} if present
     */
    boolean containsTag(@NotNull TagKey<T> tag);
}
