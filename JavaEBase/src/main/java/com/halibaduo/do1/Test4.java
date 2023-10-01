package com.halibaduo.do1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author halibaduo
 * Description: B站热搜数据快知晓
 */
public class Test4 {
    static String num = "1"; /* num是从1到26的  */
    public static void main(String[] args) {
        do {

            String url = "https://api.bilibili.com/x/web-interface/popular?ps=20&pn="+num; // 替换为你要访问的JSON数据的URL

            try {
                URL jsonUrl = new URL(url);
                URLConnection connection = jsonUrl.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                StringBuilder jsonBuilder = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line);
                }

                reader.close();

                String jsonString = jsonBuilder.toString();
                //System.out.println(jsonString); // 获取了json

                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray listArray = jsonObject.getJSONObject("data").getJSONArray("list");

                    for (int i = 0; i < listArray.length(); i++) {
                        JSONObject item = listArray.getJSONObject(i);
                        String title = item.getString("title");
                        String pic = item.getString("pic");
                        String ownerName = item.getJSONObject("owner").getString("name");

                        System.out.println("视频标题: " + title);
                        System.out.println("视频封面: " + pic);
                        System.out.println("作者名称: " + ownerName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            int num1 = Integer.parseInt(num) + 1;
            num = String.valueOf(num1);
        } while (Integer.parseInt(num) > 25);
    }
}
