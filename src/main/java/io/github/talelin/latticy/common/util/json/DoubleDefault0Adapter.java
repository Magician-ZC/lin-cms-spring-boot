package io.github.talelin.latticy.common.util.json;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import io.github.talelin.latticy.common.util.TextUtil;

import java.lang.reflect.Type;

/**
 * @author wrs 393895279@qq.com
 * @date 2022/4/12.
 * @desc
 */
public class DoubleDefault0Adapter implements JsonSerializer<Double>, JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            if (TextUtil.equals(json.getAsString(),"") || TextUtil.equals(json.getAsString(),"null")|| TextUtil.equals(json.getAsString(),
                    "NULL")) {//定义为int类型,如果后台返回""或者null,则返回0
                return 0.00;
            }
        } catch (Exception ignore) {
        }
        try {
            return json.getAsDouble();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
