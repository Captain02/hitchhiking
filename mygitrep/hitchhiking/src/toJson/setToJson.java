package toJson;

import com.mysql.jdbc.ResultSetMetaData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.sql.*;

public class setToJson {
	public static JSONArray resultSetToJsonObject(ResultSet rs) throws Exception
	  
    {   
  
       // json����  
  
        JSONArray json = new JSONArray();       
  
       // ��ȡ����   
       ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();   
  
       int columnCount = metaData.getColumnCount();   
  
       // ����ResultSet�е�ÿ������   
  
        while (rs.next()) {   
            // ����ÿһ��   
        	JSONObject jobj = new JSONObject();	
            for (int i = 1; i <= columnCount; i++) {   
  
                String columnName =metaData.getColumnLabel(i);   
  
                String value = rs.getString(columnName);   
  
                jobj.put(columnName, value);
            }  
            json.add(jobj);
        }  
  
       return json;   
  
    }  
	public static JSONArray resultSetToJsonObjectAll(ResultSet rs,JSONArray json) throws Exception
	  
    {   
  
       // ��ȡ����   
       ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();   
  
       int columnCount = metaData.getColumnCount();   
  
       // ����ResultSet�е�ÿ������   
  
        while (rs.next()) {   
            // ����ÿһ��   
        	JSONObject jobj = new JSONObject();	
            for (int i = 1; i <= columnCount; i++) {   
  
                String columnName =metaData.getColumnLabel(i);   
  
                String value = rs.getString(columnName);   
  
                jobj.put(columnName, value);
            }  
            json.add(jobj);
        }  
  
       return json;   
  
    }  
}
