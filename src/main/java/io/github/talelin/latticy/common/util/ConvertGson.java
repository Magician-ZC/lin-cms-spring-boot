package io.github.talelin.latticy.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import io.github.talelin.latticy.common.util.json.DoubleDefault0Adapter;
import io.github.talelin.latticy.common.util.json.IntegerDefault0Adapter;
import io.github.talelin.latticy.common.util.json.LongDefault0Adapter;

import java.io.Reader;
import java.lang.reflect.Type;

public class ConvertGson {

    private static final Gson remoteGson;

    static {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        //下划线转驼峰
////        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

        //解决 Gson 将 int 转换为 double 的问题
        remoteGson = new GsonBuilder()
                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .registerTypeAdapter(Double.class, new JsonSerializer<Double>() {

                    @Override
                    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                        if (src == src.longValue())
                            return new JsonPrimitive(src.longValue());
                        return new JsonPrimitive(src);
                    }
                }).create();

    }

    private ConvertGson(){

    }
    public static Gson create() {
        return remoteGson;
    }

//    private static class GsonHolder {
//        private static Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(new ExclusionStrategy() {
//            @Override
//            public boolean shouldSkipField(final FieldAttributes f) {
//                Expose expose = f.getAnnotation(Expose.class);
//                return expose != null && !expose.deserialize();
//            }
//
//            @Override
//            public boolean shouldSkipClass(final Class<?> clazz) {
//                return false;
//            }
//        })
//                .addSerializationExclusionStrategy(new ExclusionStrategy() {
//                    @Override
//                    public boolean shouldSkipField(final FieldAttributes f) {
//                        Expose expose = f.getAnnotation(Expose.class);
//                        return expose != null && !expose.serialize();
//                    }
//
//                    @Override
//                    public boolean shouldSkipClass(final Class<?> clazz) {
//                        return false;
//                    }
//                })
//                .create();
//    }

    public static <T> T fromJson(String json, Class<T> type) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(json, type);
    }

    public static <T> T fromJson(JsonElement object, Class<T> type) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(object, type);
    }

    public static <T> T fromJson(JsonElement object, Type type) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(object, type);
    }

    public static <T> T fromJson(String json, Type type) {
        return create().fromJson(json, type);
    }

    public static <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(reader, typeOfT);
    }

    public static <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
        return create().fromJson(json, classOfT);
    }

    public static <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(json, typeOfT);
    }

    public static String toJson(Object src) {
        return create().toJson(src);
    }

    public static String toJson(Object src, Type typeOfSrc) {
        return create().toJson(src, typeOfSrc);
    }

    public static JsonElement toJsonElement(Object src) {
        return create().toJsonTree(src);
    }

    public static JsonElement toJsonTree(Object src){
        return create().toJsonTree(src);
    }
}
