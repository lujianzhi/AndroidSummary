package com.example.lawson.androidsummery.junittest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Ian.Lu on 2017/9/15.
 * Project : AndroidSummary
 */
public class JUnitTestTest {

    private double a;
    private double b;
    private JUnitTest junitTest;

    @Before
    public void setUp() {
        a = 4.0;
        b = 8.0;
        junitTest = new JUnitTest();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void add() {
        TestCase.assertEquals(12.0, junitTest.add(a, b));
    }

    @Test
    public void sub() {
        TestCase.assertEquals(-4d, junitTest.sub(a, b));
    }

    @Test
    public void mul() {
        TestCase.assertEquals(32, junitTest.mul(a, b));
    }

    @Test
    public void div() {
        TestCase.assertEquals(0.5, junitTest.div(a, b));
    }

    @Test
    public void test() {
        try {
            File file = new File("E:\\AS\\Project\\AndroidSummary\\test\\nezha.json");
            FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
            BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
            StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
                System.out.println(s);
            }
            bReader.close();
            String json = sb.toString();
            System.out.println(json);
            JsonObject jsonObject = null;
            if (json != null) {
                jsonObject = getNeZhaJson(new JsonParser().parse(json).getAsJsonObject());
            }
            System.out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JsonObject getNeZhaJson(JsonObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        JsonObject nezhaJsonObj = new JsonObject();

        try {
            JsonObject nezhaJson = jsonObject.getAsJsonObject("nezha");
            if (nezhaJson != null) {
                for (Map.Entry<String, JsonElement> entry : nezhaJson.entrySet()) {
                    // 没有当前key时，添加（列表的曝光可能每次load more 都会有相同的节点数据）
                    if (!nezhaJsonObj.has(entry.getKey())) {
                        nezhaJsonObj.add(entry.getKey(), entry.getValue());
                    }
                }
            }

            Set<Map.Entry<String, JsonElement>> subJsonSet = jsonObject.entrySet();
            Iterator it = subJsonSet.iterator();
            List<JsonObject> jsonObjectList = new ArrayList<>();
            while (it.hasNext()) {
                Map.Entry<String, JsonElement> subJson = (Map.Entry<String, JsonElement>) it.next();
                if (subJson.getValue() instanceof JsonObject) {
                    JsonObject subNezhaJson = getNeZhaJson((JsonObject) subJson.getValue());
                    if (subNezhaJson != null) {
                        for (Map.Entry<String, JsonElement> entry : subNezhaJson.entrySet()) {
                            // 没有当前key时，添加（列表的曝光可能每次load more 都会有相同的节点数据）
                            if (!nezhaJsonObj.has(entry.getKey())) {
                                nezhaJsonObj.add(entry.getKey(), entry.getValue());
                            }
                        }
                    }
                    jsonObjectList.add(subNezhaJson);
                }
            }
            for (JsonObject sub : jsonObjectList) {
                System.out.println(sub.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return nezhaJsonObj;
        }
    }
}