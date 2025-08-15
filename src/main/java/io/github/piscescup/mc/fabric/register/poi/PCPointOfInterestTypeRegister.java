package io.github.piscescup.mc.fabric.register.poi;

import io.github.piscescup.mc.fabric.register.PCRegister;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <h2>Description</h2>
 * A util for registering a POI.
 *
 * <h2>Usages</h2>
 * <p>
 *     The static method: {@link #createFor} can createFor a {@code PCPointOfInterestTypeRegister} in the given {@code Identifier}
 * </p>
 * <p>
 *     Below is a sample usage:
 * </p>
 * <pre>{@code
 * public static final PCPointOfInterestTypeRegister FIREWORK_VILLAGER_TEST_POI_1 =
 *     PCPointOfInterestTypeRegister.createFor(MOD_ID, "fire_work_poi")
 *         .workBlock(ModBlocks.PC_BLOCK)
 *         .ticketCount(1)
 *         .searchDistance(1)
 *         .registerAndBuild()
 *         .addToList(POI_TYPES);
 * }</pre>
 *<p>
 *     And then register the class in the mod entry:
 * </p>
 * <pre>{@code
 * public class PiscesCupDevelopLib implements ModInitializer {
 * 	  @Override
 *    public void onInitialize() {
 * 		// This code runs as soon as Minecraft is in a mod-load-ready state.
 * 		// However, some things (like resources) may still be uninitialized.
 * 		// Proceed with mild caution.
 * 		MOD_LOGGER.info("Hello Fabric world!");
 * 		MOD_LOGGER.info("Hello, " + MOD_NAME);
 * 		ModItems.register();
 * 		ModBlocks.register();
 * 		ModItemGroups.register();
 * 		ModTags.register();
 * 		ModAdvancements.register();
 * 		KeyAction.registerTranslations();
 *
 * 		ModVillagerPOIs.register();
 * 		ModVillagerProfessions.register();
 * 		ModVillagers.register();
 * 		ModVillagerTrades.register();
 * 		MOD_LOGGER.info("Finish registering");
 *    }
 * }
 * }</pre>
 * @author REN YuanTong
 * @Date 2025-07-02
 * @since 1.1.0
 */
public final class PCPointOfInterestTypeRegister
    extends PCRegister<PointOfInterestType, PCPointOfInterestTypeRegister, POIPostRegisterConfig>
    implements POIPreRegisterConfig.POIWorkBlockConfig, POIPreRegisterConfig.POIOptionConfig, POIPostRegisterConfig
{
    private int ticketCount = 1;
    private int searchDistance = 1;
    private final List<Block> workBlocks = new ArrayList<>();

    private PCPointOfInterestTypeRegister(Identifier id) {
        super(id);
    }

    /**
     * Create a {@code POIPreRegisterConfig.POIWorkBlockConfig} for the given {@code Identifier}.
     * @param id The {@code Identifier} for the POI to be registered.
     */
    public static POIPreRegisterConfig.POIWorkBlockConfig createFor(Identifier id) {
        return new PCPointOfInterestTypeRegister(id);
    }

    /**
     * Create a {@code POIPreRegisterConfig.POIWorkBlockConfig} for the given {@code fullPath}.
     * @param fullPath The {@code fullPath} for the POI to be registered, such as: {@code "minecraft:stone"}.
     */
    public static POIPreRegisterConfig.POIWorkBlockConfig createFor(String fullPath) {
        return new PCPointOfInterestTypeRegister(Identifier.of(fullPath));
    }

    /**
     * Create a {@code POIPreRegisterConfig.POIWorkBlockConfig} for the given {@code namespace} and {@code path}.
     * @param namespace The {@code namespace} for the POI to be registered, such as: {@code "minecraft"}.
     * @param path The {@code path} for the POI to be registered, such as: {@code "stone"}.
     */
    public static POIPreRegisterConfig.POIWorkBlockConfig createFor(String namespace, String path) {
        return new PCPointOfInterestTypeRegister(Identifier.of(namespace, path));
    }

    /**
     * Add the work block to the POI.
     * @param block The block to be added.
     */
    @Override
    public POIPreRegisterConfig.POIOptionConfig workBlock(Block block) {
        Objects.requireNonNull(block);
        this.workBlocks.add(block);
        return this;
    }

    /**
     * Add work blocks to the POI.
     * @param blocks The blocks to be added.
     */
    @Override
    public POIPreRegisterConfig.POIOptionConfig workBlocks(List<Block> blocks) {
        Objects.requireNonNull(blocks);
        this.workBlocks.addAll(blocks);
        return this;
    }

    /**
     * Add work blocks to the POI.
     * @param blocks The block to be added.
     */
    @Override
    public POIPreRegisterConfig.POIOptionConfig workBlocks(Block... blocks) {
        Objects.requireNonNull(blocks);
        this.workBlocks.addAll(Arrays.asList(blocks));
        return this;
    }

    /**
     * Set the maximum number of entities that can be associated with it at the same time.
     * @param ticketCount The maximum number of entities that can be associated with it at the same time.
     */
    @Override
    public POIPreRegisterConfig.POIOptionConfig ticketCount(int ticketCount) {
        this.ticketCount = ticketCount;
        return this;
    }

    /**
     * Set the maximum range within which entities can search for this point of interest.
     * @param searchDistance The maximum range.
     */
    @Override
    public POIPreRegisterConfig.POIOptionConfig searchDistance(int searchDistance) {
        this.searchDistance = searchDistance;
        return this;
    }


    @Override
    public POIPostRegisterConfig registerAndBuild() {
        this.key = RegistryKey.of(
            RegistryKeys.POINT_OF_INTEREST_TYPE,
            this.id
        );
        int size = this.workBlocks.size();
        this.targetRegistered = PointOfInterestHelper.register(
            this.id, this.ticketCount,
            this.searchDistance,
            this.workBlocks.toArray(new Block[size])
        );

        return this;
    }

}
