package com.mu.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * gson处理null值的工具类
 */
public class StringNullAdapter extends TypeAdapter<String> {

    @Override
    public void write(JsonWriter jsonWriter, String s) throws IOException {
        if (s==null){//序列化使用的是adapter的write方法
            //jsonWriter.nullValue();  这是gson处理空字符串的方法,就是将空字符串设置为null
            jsonWriter.value("null");//重写这个方法,如果是空字符串,就什么都不写
            return;
        }
        jsonWriter.value(s);//若果不是空字符串,就调用gson自己的方法
    }

    @Override
    public String read(JsonReader jsonReader) throws IOException {
        if(jsonReader.peek()== JsonToken.NULL){
            jsonReader.nextNull();
            return "null";
        }
        return null;
    }
}
