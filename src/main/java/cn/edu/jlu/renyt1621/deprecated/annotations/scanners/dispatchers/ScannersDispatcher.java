package cn.edu.jlu.renyt1621.deprecated.annotations.scanners.dispatchers;

import cn.edu.jlu.renyt1621.deprecated.annotations.scanners.Scanner;
import cn.edu.jlu.renyt1621.deprecated.annotations.scanners.ScannerPackages;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-04-12
 * @Time 14:59
 */
@Deprecated
public abstract class ScannersDispatcher<S extends Scanner<? extends Annotation>> {
    protected List<S> scanners = new ArrayList<>();

    public ScannersDispatcher(List<S> scanners) {
        this.scanners = scanners;
    }

    public ScannersDispatcher() {}

    public S addScanner(S scanner) {
        this.scanners.add(scanner);
        return scanner;
    }

    public Map<S, List<Scanner.Target<Object>>> wrap(){
        Map<S, List<Scanner.Target<Object>>>
            result = new HashMap<>();

        for (S scanner : this.scanners) {
            ScannerPackages pcks = scanner.getClass().getDeclaredAnnotation(ScannerPackages.class);

            if ( pcks == null )
                throw new IllegalArgumentException(
                    "The scanner:" + scanner.getClass().getName() + "must be annotated by" + ScannerPackages.class.getCanonicalName()
                );

            for (String pck : pcks.value()) {
                try (
                    ScanResult scanResult = new ClassGraph()
                        .acceptPackages(pck)
                        .enableAllInfo()
                        .scan()
                ) {
                    List<Class<?>> classes = scanResult.getAllClasses()
                        .loadClasses();

                    // List<Scanner.Target<Object>> targetList = classes.stream()
                    //     .map(Class::getDeclaredFields)
                    //     .flatMap(Arrays::stream)
                    //     .map(scanner::wrap)
                    //     .toList();
                    List<Scanner.Target<Object>> targetList = new ArrayList<>();

                    for (Class<?> clazz : classes) {
                        Field[] fields = clazz.getDeclaredFields();

                        for (Field field : fields) {
                            Scanner.Target<Object> target = scanner.wrap(field);
                            targetList.add(target);
                        }
                    }

                    result.put(scanner, targetList);
                }

            }

        }
        return result;
    }

}
