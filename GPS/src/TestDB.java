import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB
{

	static String sql = null;
	static DBHelper db1 = null;
	static ResultSet ret = null;

	public static void main(String[] args)
	{
		sql = "select * from HPO where ID = 'HP:0001145'";
		db1 = new DBHelper(sql);
		try
		{
			ret = db1.pst.executeQuery();// ִ����䣬�õ������
			while (ret.next())
			{
				String uid = ret.getString(1);
				String ufname = ret.getString(2);
				String ulname = ret.getString(3);
				String udate = ret.getString(4);
				System.out.println(uid + "\t" + ufname + "\t" + ulname + "\t" + udate);
				System.out.println(udate.equals(""));
			} // ��ʾ����
			ret.close();
			db1.close();// �ر�����
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

}
