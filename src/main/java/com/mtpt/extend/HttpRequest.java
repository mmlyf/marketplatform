package com.mtpt.extend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.mtpt.config.BaseConfig;



public class HttpRequest {
	private static Logger log = Logger.getLogger(HttpRequest.class);
	/**
	 *向指定URL发送get请求
	 * 
	 */
 	public static String sendGet(String url, String param) {
		String urlparam = url +"?"+param;
		String result = "";
		BufferedReader reader = null;
		try {
			URL urlpa = new URL(urlparam);
			URLConnection conn = urlpa.openConnection();
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.connect();
			Map<String, List<String>> headerMap = conn.getHeaderFields();
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line = "";
			while(reader.ready()&&(line = reader.readLine())!=null) {
				result += line;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (reader!=null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
 	/**
 	 * 向指定的URL发送的post的方法
 	 * 
 	 */
 	public static String sendPost(String url,String param) {
 		String result = "";
 		PrintWriter pw = null;
 		BufferedReader bReader = null;
 		try {
			URL urlPost = new URL(url);
			URLConnection connection  = urlPost.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(4000);
			connection.setReadTimeout(5000);
			pw = new PrintWriter(connection.getOutputStream());
			pw.print(param);
			pw.flush();
			bReader  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lineStr = "";
			while(bReader.ready()&&(lineStr = bReader.readLine())!=null) {
				result += lineStr;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (bReader!=null) {
					bReader.close();
				}
				if(pw!=null) {
					pw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
 		return result;
 	}
 	
 	  /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是为json数组的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostJson(String url, String param) {
    	PrintWriter out = null;
		BufferedReader in = null;
		JSONObject jsonObject = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);

			conn.setDoInput(true);

			conn.setRequestProperty("Content-Type", "application/json");
			// 获取URLConnection对象对应的输出流（设置请求编码为UTF-8）
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
			System.err.println(out);
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 获取请求返回数据（设置返回数据编码为UTF-8）
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			System.err.println(result);//打印返回结果   
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}


		return result;
    } 
    
    
    /**
     * 调用协尔平台接口查询二次确认的内容
     * @param path
     * @param params
     * @return
     */
    public static String sendPostForSelectSecConfirm(String path,String params) {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			URL url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type", "application/json");
			String string = MD5.md("HSDT");
			System.out.println(string);
			connection.setRequestProperty("Access_Token",MD5.md(BaseConfig.XIEER_ACCOUNT));
			connection.setDoOutput(true);
			connection.setDoInput(true);
			MyDesUtils myDesUtils = new MyDesUtils(BaseConfig.XIEER_SCRIPT);
			String param = myDesUtils.encrypt(params);
			System.out.println(param+"\njiemi"+myDesUtils.decrypt(param));
			PrintWriter pWriter = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
			pWriter.print(param);
			pWriter.flush();
			if (connection.getResponseCode()==HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line ="";
				while((line = reader.readLine())!=null) {
					stringBuilder.append(line);
				}
				return stringBuilder.toString();
			}else {
				System.out.println("failed");
				return null;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.disconnect();
		}
		return null;
	}
}
