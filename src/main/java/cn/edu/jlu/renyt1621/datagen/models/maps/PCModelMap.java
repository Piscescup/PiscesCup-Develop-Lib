package cn.edu.jlu.renyt1621.datagen.models.maps;

import net.minecraft.client.data.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author REN YuanTong
 * @Description 
 * @Date 2025-04-16
 * @Time 16:11
 */ 
public abstract class PCModelMap<T> {
    protected Map<T, Model> MODEL_MAP = new HashMap<>();

    protected PCModelMap() {}

    public int size() {
        return MODEL_MAP.size();
    }

    public boolean isEmpty() {
        return MODEL_MAP.isEmpty();
    }

    public boolean containsKey(T t) {
        return MODEL_MAP.containsKey(t);
    }

    public Model put(T t, Model model) {
        return MODEL_MAP.put(t, model);
    }

    public void putAll(Map<T, Model> map) {
        MODEL_MAP.putAll(map);
    }

    public Model remove(T t) {
        return MODEL_MAP.remove(t);
    }

    public void clear() {
        MODEL_MAP.clear();
    }

    public Set<T> keySet() {
        return MODEL_MAP.keySet();
    }

    public Set<Map.Entry<T, Model>> entrySet() {
        return MODEL_MAP.entrySet();
    }

    public Map<T, Model> get() {
        return MODEL_MAP;
    }
}
