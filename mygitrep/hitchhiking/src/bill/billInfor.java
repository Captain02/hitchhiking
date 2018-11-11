package bill;

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
 * Servlet implementation class billInfor
 */
@WebServlet("/billInfor")
public class billInfor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String sql;   
    public billInfor() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID=request.getParameter("userID");
		int id=Integer.parseInt(userID);
		String type=request.getParameter("type");
		type=new String(type.getBytes("ISO8859-1"), "UTF-8");
		System.out.println(type);
		Connection conn=mdb.getconntion();//���ӵ����ݿ� 
		try {
			if(type.equals("����:�ۺ�")) {
				sql="select * from tblBill where userID='"+id+"'"+" ORDER BY billDate DESC";
			}else if(type.equals("��������")) {
				sql="select * from tblBill where userID='"+id+"'"+" and (billType='���ö����˻�' or billType='���������˻�' or billType='���Ͷ����˻�')";
			}else if(type.equals("�����ͽ�")) {
				sql="select * from tblBill where userID='"+id+"'"+" and (billType='�����ͽ�' or billType='�����ͽ�' or billType='�����ͽ�')";
			}else if(type.equals("��ֵ����")) {
				sql="select * from tblBill where userID='"+id+"'"+" and (billType='΢������' or billType='֧��������')";
			}else {
				sql="select * from tblBill where userID='"+id+"'"+" and billType='"+type+"'"+" ORDER BY billDate DESC";
			}			
			java.sql.Statement stmt=conn.createStatement();
			ResultSet r=stmt.executeQuery(sql);
			JSONArray jsonObj=setToJson.resultSetToJsonObject(r);
			System.out.println(jsonObj);
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
