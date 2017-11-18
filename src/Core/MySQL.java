package Core;
import java.sql.*;

public class MySQL { 

public static String path = "Blockchain";
public static Connection con; 
public static CallableStatement resSet;
public static Statement statmt;
public static void connect() throws ClassNotFoundException, SQLException {
         con = null;
         Class.forName("org.sqlite.JDBC");
         con = DriverManager.getConnection("jdbc:sqlite:"+path+".s3db");
         statmt = con.createStatement();
         System.out.println("База Подключена!");
     }
public static void Add(int index, String previousHash, String time, String data, String hash) {
PreparedStatement ps;
try {
	ps = getConnection().prepareStatement("INSERT INTO Blocks ('index','previousHash','time','data','hash') VALUES (?,?,?,?,?)");
	ps.setInt(1, index);
	ps.setString(2, previousHash);
	ps.setString(3, time);
	ps.setString(4, data);
	ps.setString(5, hash);
	ps.executeUpdate();
} catch(SQLException e) {
	e.printStackTrace();
}
}
public static void AddIP(String ip, String identificator) {
PreparedStatement ps;
try {
	ps = getConnection().prepareStatement("INSERT INTO Net ('ip','identificator') VALUES (?,?)");
	ps.setString(1, ip);
	ps.setString(2, identificator);
	ps.executeUpdate();
} catch(SQLException e) {
	e.printStackTrace();
}
}
public static Block Read() {
	Block save = new Block();
	try {
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Blocks WHERE ID = (SELECT MAX(ID) FROM Blocks)"); //извлекаем инфу
        
		if (rs.next() == true) { //если такое значение есть
			int index = rs.getInt("index"); //выводим его
			String previousHash = rs.getString("previousHash");
			String time = rs.getString("time");
			String data = rs.getString("data");
			String hash = rs.getString("hash");
			System.out.println(index);
			System.out.println(previousHash);
			System.out.println(time);
			System.out.println(data);
			System.out.println(hash);
			save.setIndex(index);
			save.setPreviousHash(previousHash);
			save.setTimestamp(time);
			save.setData(data);
			save.setHash(hash);
			return save;
		}
	}catch(SQLException e){
		e.printStackTrace();
	}
	return null;
}
public static void disconnect() { 
         if (isConnected()) { 
             try { 
                  con.close(); 
             } catch (SQLException e) { 
                  e.printStackTrace(); 
                 } 
           } 
     } 
public static boolean isConnected() { 
return (con == null ? false : true); 
     } 
public static Connection getConnection() { 
return con; 
     } 
public static void main(String[] args) throws ClassNotFoundException{
	
}
}