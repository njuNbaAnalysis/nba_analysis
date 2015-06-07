package JsonServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

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
	 * 请求格式例:
	 * http://localhost:8080/JsonServer/getAllPlayers?Season=13-14&isPlayOff=true
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //使用JSONArray测试  
        JSONArray jsonArray = new JSONArray();  
        jsonArray.put("MCA");  
        jsonArray.put("kevin");  
        jsonArray.put("15-12-1998");
          
        //页面输出JSONArray的内容  
        PrintWriter out = response.getWriter();  
        out.print(jsonArray);  
        out.println("======================================");  
        for(int i=0;i<jsonArray.length();i++){  
            out.print(jsonArray.getString(i));  
        }  
	}

}
