package ts;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest {
	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet result=null;
		String url="jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
		String user="root";
		String password="1117";
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载驱动
			connection=DriverManager.getConnection(url,user,password);//建立连接
			System.out.println(connection); 
			
			
			statement=connection.prepareStatement("select * from Region limit 0,1");
	
			result=statement.executeQuery();
			while(result.next())
			{
				System.out.print(result.getString(1));
				System.out.print(" "+result.getString(2));
				System.out.println(" "+result.getString(3));
			}
			try {
				if(statement!=null)
					statement.close();
			} catch (Exception e) {
				// TODO: handle exception
			}	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			try {
				if(result!=null)
					result.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			try {
				if(statement!=null)
					statement.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			try {
				if(connection!=null)
					connection.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
