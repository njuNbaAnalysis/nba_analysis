package JsonServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class JsonServlet
 */
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); 
	}

	/**
	 * 数据格式例:
	 * http://localhost:8080/JsonServer/getAllPlayers?Season=13-14&isPlayOff=true
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();  
		String resultString = "";
		
		StringBuffer rawUrl = request.getRequestURL();
		String methodName = null;
		String paraNames[] = null;
		String paraValues[] = null;
		
		//得到各种数据
		try{
			methodName = rawUrl.toString().split("http://localhost:8080/JsonServer/")[1];
			
			Map<String, String[]> paraMap = request.getParameterMap();
			Set<String> keySet = paraMap.keySet();
			paraNames = new String[keySet.size()];
			paraValues = new String[keySet.size()];
			
			int i = 0;
			for(String token:keySet){
				paraNames[i] = token;
				String[] para1 = paraMap.get(token);
				paraValues[i] = para1[0];
				i ++;
			}
		}catch(Exception e){
			out.println("wrong parameters");
			return;
		}
		
		GetJsonData getJsonData = GetJsonData.getInstance();
		try {
			Method[] methods = getJsonData.getClass().getDeclaredMethods();
			Method method = null;
			for(Method token:methods){
				if(token.getName().equals(methodName)
						&& token.getParameterCount() == paraNames.length){
					method = token;
				}
			}
			JSONObject result = null;
			switch(paraNames.length){
			case 0:
				result = (JSONObject) method.invoke(getJsonData);
				break;
			case 1:
				result = (JSONObject) method.invoke(getJsonData,paraValues[0]);
				break;
			case 2:
				result = (JSONObject) method.invoke(getJsonData,paraValues[0],paraValues[1]);
				break;
			case 3:
				result = (JSONObject) method.invoke(getJsonData,paraValues[0],paraValues[1],paraValues[2]);
				break;
			case 4:
				result = (JSONObject) method.invoke(getJsonData,paraValues[0],paraValues[1],paraValues[2],paraValues[3]);
				break;
			}
			resultString += result;
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("methodName:" + methodName);
		System.out.println("paraNames");
		for(String token:paraNames){
			System.out.println(token);
		}
		System.out.println("paraValues");
		for(String token:paraValues){
			System.out.println(token);
		}

        out.println(resultString);
	}

}
