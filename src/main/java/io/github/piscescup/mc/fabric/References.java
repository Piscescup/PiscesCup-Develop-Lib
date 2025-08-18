package io.github.piscescup.mc.fabric;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author REN YuanTong
 */
public final class References {
    public static final String MOD_ID = "pc_develop_lib";

    public static final String MOD_NAME = "PiscesCup Develop Lib";

    public static final String MOD_VERSION = "1.1.2";

    public static final String ORIGIN_MOD_VERSION = "1.0.0";

    public static final String MC_VERSION = "1.21.5";

    public static final Logger MOD_LOGGER = LogManager.getLogger(MOD_ID);

    public static final Gson GSON = new GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .create();

}
