package worker;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.mdb;
import net.sf.json.JSONArray;
import toJson.setToJson;

/**
 * Servlet implementation class workerInfor
 */
@WebServlet("/workerInfor")
public class workerInfor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public workerInfor() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("userID");
		int userID=Integer.parseInt(id);	
		Connection conn=mdb.getconntion();//���ӵ����ݿ� 
		try {
			String sql="select * from tblUserInfo where userID='"+userID+"'";
			java.sql.Statement stmt=conn.createStatement();
			ResultSet r=stmt.executeQuery(sql);
			JSONArray jsonObj=setToJson.resultSetToJsonObject(r);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");					
			PrintWriter pw=response.getWriter();
			pw.print(jsonObj);
			pw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
