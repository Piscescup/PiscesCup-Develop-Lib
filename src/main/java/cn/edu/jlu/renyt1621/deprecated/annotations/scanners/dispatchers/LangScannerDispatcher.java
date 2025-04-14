package cn.edu.jlu.renyt1621.deprecated.annotations.scanners.dispatchers;

import cn.edu.jlu.renyt1621.deprecated.annotations.lang.Langs;
import cn.edu.jlu.renyt1621.deprecated.annotations.scanners.DataGenScanner;
import cn.edu.jlu.renyt1621.deprecated.annotations.scanners.datagen.lang.LangScanner;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-12
 * @Time 15:04
 */
public class LangScannerDispatcher
    extends ScannersDispatcher<DataGenScanner<Langs>>
{
    private static final LangScannerDispatcher LANG_DISPATCHER = new LangScannerDispatcher();

    private LangScannerDispatcher() {
        super();
        if ( LANG_DISPATCHER != null )
            throw new RuntimeException("LangScannerDispatcher is singleton");

        addScanner(new LangScanner());
    }

    public static LangScannerDispatcher instance() {
        return LANG_DISPATCHER;
    }

}
