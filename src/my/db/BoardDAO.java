package my.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import member.memVO;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {
	}
	public static BoardDAO getInstance() {
		return instance;
	}
	
	
	//DB����
	public static Connection getConnection(){
		Connection con = null;
		try {
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
			String dbUser = "scott";
			String dbPass = "tiger";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
		}

	public void close(Connection con, ResultSet rs, PreparedStatement pstmt) {
		if(rs!=null) 
			try {
				rs.close();
			}catch(SQLException ex) {}
		if(pstmt!=null)
			try {
				pstmt.close();
			}catch(SQLException ex) {}
		if(con!=null)
			try {
				con.close();
			}catch(SQLException ex) {}
		}
	
	
	//���Ժ� �հ� �������� 
    public BoardVO totPrice(String id,String key1, String key2, String startDt, String endDt) throws SQLException{
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       BoardVO mainboard = null;
       StringBuffer sql = new StringBuffer();
       System.out.println("1=============id��"+id);
       try {
          conn = getConnection(); 
          //��¥ �Է�, �׸� �Է� (������ �������� ����)
             sql.append ("select (select nvl(TO_CHAR(sum(main_price),'999,999,999,999,999'),0) from mainboard where main_option='1' ");
          if(key2 != null && !key2.equals("��������")) {
             sql.append ("and main_account=? ");
          }
          if(startDt != null && !startDt.equals("") && endDt != null && !endDt.equals("")) {         
             sql.append ("and MAIN_WRITEDAY between ? and ?" );
          }
             sql.append ("and main_id= ?" );
             sql.append (" ) totspend, " ); 
             sql.append ("(select nvl(TO_CHAR(sum(main_price),'999,999,999,999,999'),0) from mainboard where main_option='2' " );
          if(key2 != null && !key2.equals("��������")) {         
             sql.append ("and main_account=? ");
          }
          if(startDt != null && !startDt.equals("") && endDt != null && !endDt.equals("")) {
             sql.append ("and MAIN_WRITEDAY between ? and ?" );
          }
             sql.append ("and main_id= ?" );
             sql.append (") totimport " );
             sql.append (" from mainboard where main_id= ? " ); 
             sql.append ("group by main_id " );
             
             pstmt = conn.prepareStatement(sql.toString());
             
          if(key2 != null && !key2.equals("��������") && startDt != null && !startDt.equals("") && endDt != null && !endDt.equals("")){
             pstmt.setString(1, key2);
             pstmt.setString(2, startDt);
             pstmt.setString(3, endDt);
             pstmt.setString(4, id);
             pstmt.setString(5, key2);
             pstmt.setString(6, startDt);
             pstmt.setString(7, endDt);
             pstmt.setString(8, id);
             pstmt.setString(9, id);
          }else if(startDt != null && !startDt.equals("") && endDt != null && !endDt.equals("")) {
                pstmt.setString(1, startDt);
                pstmt.setString(2, endDt);
                pstmt.setString(3, id);
                pstmt.setString(4, startDt);
                pstmt.setString(5, endDt);
                pstmt.setString(6, id);
                pstmt.setString(7, id);
          }else if(key2 != null && !key2.equals("��������")){
             pstmt.setString(1, key2);
             pstmt.setString(2, id);
             pstmt.setString(3, key2);
             pstmt.setString(4, id);
             pstmt.setString(5, id);
          }else {
             pstmt.setString(1, id);
             pstmt.setString(2, id);
             pstmt.setString(3, id);
             
          }
             rs = pstmt.executeQuery();
          if(rs.next()) {
             mainboard = new BoardVO();
             mainboard.setTot_spend(rs.getString("totspend"));
             mainboard.setTot_import(rs.getString("totimport"));
          }   
       }catch(Exception e) {
             e.printStackTrace();
       }finally {close(conn, rs, pstmt);}
       return mainboard;
    }
	
	
	
	
	//����θ���Ʈ (List)
	public List mainList(String id, String key1, String key2, int startRow, int endRow, String startDt,String endDt)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List mainList = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = getConnection();
			
				sql.append ("select * from (select rownum rnum ,a.* "); 
				sql.append ("from (select main_num, to_char(to_date(main_writeday),'YYYY-MM-DD') main_writeday, decode(main_option,'1','����','����') main_option, main_account, main_content, TO_CHAR( main_price ,'999,999,999,999,999') main_price " ); 
				sql.append ("from mainBoard where main_id=? ");
				
				if(key1 != null && !key1.equals("�׸���")){			
					sql.append ("and mainBoard.MAIN_OPTION=? ");
				}
				if(key2 != null && !key2.equals("��������")) {
				sql.append ("and mainBoard.MAIN_ACCOUNT=? ");
				}				
			    if(startDt != null && !startDt.equals("") && endDt != null && !endDt.equals("")) {
				sql.append (" and main_writeday between ? and ? " );
			    }
				
			sql.append ("order by main_writeday desc)a) where rnum between ? and ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			if(key1 != null && !key1.equals("�׸���") && key2 != null && !key2.equals("��������") && startDt != null && !startDt.equals("") && endDt != null && !endDt.equals("")){	
				System.out.println("1���׸��������");
				pstmt.setString(2, key1);
				pstmt.setString(3, key2);
				pstmt.setString(4, startDt);
				pstmt.setString(5, endDt);
				pstmt.setInt(6, startRow);
				pstmt.setInt(7, endRow);
			}else if(key1 != null && !key1.equals("�׸���") && startDt != null && !startDt.equals("") && endDt != null && !endDt.equals("")) {
				System.out.println("2���׸�� ��¥���������");
				pstmt.setString(2, key1);
				pstmt.setString(3, startDt);
				pstmt.setString(4, endDt);
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, endRow);
			}else if(key1 != null && !key1.equals("�׸���") && key2 != null && !key2.equals("��������")) {
				System.out.println("3���׸�� ��¥���������");
				pstmt.setString(2, key1);
				pstmt.setString(3, key2);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
			}else if(key1 != null && !key1.equals("�׸���")) {
				pstmt.setString(2, key1);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
			}else {
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
				rs = pstmt.executeQuery();
	
			if(rs.next()) {
				mainList = new ArrayList();
				do {
					BoardVO mainboard = new BoardVO();
					mainboard.setMain_num(rs.getInt("main_num"));
					mainboard.setMain_writeday(rs.getString("main_writeday"));
					mainboard.setMain_option(rs.getString("main_option"));
					mainboard.setMain_account(rs.getString("main_account"));
					mainboard.setMain_content(rs.getString("main_content"));
					mainboard.setMain_price(rs.getString("main_price"));
					mainList.add(mainboard);
				
				} while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {close(conn, rs, pstmt);}
		return mainList;
		}
	
	//main �Խù��� Count
	public int mainCount(String id, String key1, String key2, int startRow, int endRow, String startDt,String endDt) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int count = 0;
		
		
		try {
			conn = getConnection();
			
			sql.append ("select count(*) from (select rownum rnum ,a.* "); 
			sql.append ("from (select main_num, to_char(to_date(main_writeday),'YYYY-MM-DD') main_writeday, decode(main_option,'1','����','����') main_option, main_account, main_content, TO_CHAR( main_price ,'999,999,999,999,999') main_price " ); 
			sql.append ("from mainBoard where main_id=? ");

			if(key1 != null && !key1.equals("�׸���")){			
				sql.append ("and mainBoard.MAIN_OPTION=? ");
			}
			if(key2 != null && !key2.equals("��������")) {
			sql.append ("and mainBoard.MAIN_ACCOUNT=? ");
			}				
		    if(startDt != null && !startDt.equals("") && endDt != null && !endDt.equals("")) {
			sql.append (" and main_writeday between ? and ? " );
		    }
			sql.append ("order by main_writeday desc)a) ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			if(key1 != null && !key1.equals("�׸���") && key2 != null && !key2.equals("��������") && startDt != null && !startDt.equals("") && endDt != null && !endDt.equals("")){	
				System.out.println("1���׸�� ��¥���������");
				pstmt.setString(2, key1);
				pstmt.setString(3, key2);
				pstmt.setString(4, startDt);
				pstmt.setString(5, endDt);
			}else if(key1 != null && !key1.equals("�׸���") && startDt != null && !startDt.equals("") && endDt != null && !endDt.equals("")) {
				System.out.println("2���׸�� ��¥���������");
				pstmt.setString(2, key1);
				pstmt.setString(3, startDt);
				pstmt.setString(4, endDt);
			}else if(key1 != null && !key1.equals("�׸���") && key2 != null && !key2.equals("��������")) {
				System.out.println("3���׸�� ��¥���������");
				pstmt.setString(2, key1);
				pstmt.setString(3, key2);
			}else if(key1 != null && !key1.equals("�׸���")){
				System.out.println("4���׸�� ��¥���������");
				pstmt.setString(2, key1);
			}

			rs = pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {close(conn, rs, pstmt);}
		return count;
		}	
	

	
	//���Ժ� ���� (Insert)
	public void insert(BoardVO mainboard, String id)throws SQLException{
		String sql="";
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number=0;
		try {
			pstmt = con.prepareStatement("select boardSer.nextval from dual");
			rs = pstmt.executeQuery();
			if(rs.next()) 
				number = rs.getInt(1)+1;
			else number = 1;
			
			sql="insert into mainBoard(main_num,main_writeday,main_option,main_account,main_content,main_price,main_id) "
					+ "values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, mainboard.getMain_writeday());
			pstmt.setString(3, mainboard.getMain_option());
			pstmt.setString(4, mainboard.getMain_account());
			pstmt.setString(5, mainboard.getMain_content());
			pstmt.setString(6, mainboard.getMain_price());
			pstmt.setString(7, id);
			pstmt.executeQuery();
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally {
			close(con, rs, pstmt);
		}
	}
	
	
	//����� �󼼺���
	public BoardVO Contentview(int num)throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		BoardVO mainboard = null;
		
		try {
			conn = getConnection();
			//sql = "select * from mainBoard where main_num = ?";
			sql = "select main_num, main_writeday, decode(main_option,'1','����','����') main_option, "
					+ " main_account, main_content, main_price from mainBoard where main_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mainboard = new BoardVO();
				mainboard.setMain_num(rs.getInt("main_num"));
				mainboard.setMain_writeday(rs.getString("main_writeday"));
				mainboard.setMain_option(rs.getString("main_option"));
				mainboard.setMain_account(rs.getString("main_account"));
				mainboard.setMain_content(rs.getString("main_content"));
				mainboard.setMain_price(rs.getString("main_price"));
				}	
			}catch(Exception e) {
				e.printStackTrace();
		}finally {
			close(conn, null, pstmt);
		}
		return mainboard;
		}
	
	
	
	//����� ����
	public int updateBoard(BoardVO mainboard, int num, String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		int check = 0;
		try {
			conn = getConnection();
			 sql = "update mainboard set main_writeday=?,main_content=?, main_price=? where main_num=? and main_id=?";
				
			 pstmt = conn.prepareStatement(sql);
			 
				pstmt.setString(1, mainboard.getMain_writeday());
				pstmt.setString(2, mainboard.getMain_content());
				pstmt.setString(3, mainboard.getMain_price());
				pstmt.setInt(4, num);
				pstmt.setString(5, id);
				
				check = pstmt.executeUpdate();				
		}catch(Exception e) {
				e.printStackTrace();
		}finally {
			close(conn, null, pstmt);
		}return check;
	}
	
	
	
	
	
	//����� ����
	public int deleteMember(int num, String id)throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		System.out.println("num==="+num);
		System.out.println("id===="+id);
		try {
			conn = getConnection();
				String sql="delete from mainboard where main_num=? and main_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, id);
				x = pstmt.executeUpdate();
		
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			close(conn, rs, pstmt);
		}
		return x;
		}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//�����׸�
	
	// �ĺ�
	   public int foodCnt(String id,String main_option,String date1,String date2 )throws SQLException  {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql="";
	      int count = 0;
	      System.out.println("22222222");
	      
	      try {
	         conn = getConnection();
	            sql = "select sum(main_price) from MAINBOARD where main_id = ? and main_option = ? and main_account='�ĺ�' "
	            		+ " and main_writeday between ? and ? ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            pstmt.setString(2, main_option);
	            pstmt.setString(3, date1);
	            pstmt.setString(4, date2);
	            rs = pstmt.executeQuery();
	            System.out.println("3==========="+sql);
	         if(rs.next()) {
	            count=rs.getInt(1);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {close(conn, rs, pstmt);}
	      return count;
	      }
	   
	   //�����
	   public int carCnt(String id,String main_option,String date1,String date2 ) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql="";
	      int count = 0;
	      
	      try {
	         conn = getConnection();
	            sql = "select sum(main_price) from MAINBOARD where main_id = ? and main_option = ? and main_account='�����' "
	            		+ " and main_writeday between ? and ? ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            pstmt.setString(2, main_option);
	            pstmt.setString(3, date1);
	            pstmt.setString(4, date2);
	            rs = pstmt.executeQuery();
	            
	         if(rs.next()) {
	            count=rs.getInt(1);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {close(conn, rs, pstmt);}
	      return count;
	   }
	   
	   //�ǰ��Ƿ�
	   public int healthCnt(String id,String main_option,String date1,String date2 ) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql="";
	      int count = 0;
	      
	      try {
	         conn = getConnection();
	            sql = "select sum(main_price) from MAINBOARD where main_id = ? and main_option = ? and main_account='�ǰ��Ƿ�' "
	            		+ " and main_writeday between ? and ? ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            pstmt.setString(2, main_option);
	            pstmt.setString(3, date1);
	            pstmt.setString(4, date2);
	            rs = pstmt.executeQuery();
	            
	         if(rs.next()) {
	            count=rs.getInt(1);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {close(conn, rs, pstmt);}
	      return count;
	   }
	   
	 //��ź�
	   public int phoneCnt(String id,String main_option,String date1,String date2 ) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql="";
	      int count = 0;
	      
	      try {
	         conn = getConnection();
	            sql = "select sum(main_price) from MAINBOARD where main_id = ? and main_option = ? and main_account='��ź�' "
	            		+ " and main_writeday between ? and ? ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            pstmt.setString(2, main_option);
	            pstmt.setString(3, date1);
	            pstmt.setString(4, date2);
	            rs = pstmt.executeQuery();
	            
	         if(rs.next()) {
	            count=rs.getInt(1);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {close(conn, rs, pstmt);}
	      return count;
	   }
	   
	   //��Ȱ��ǰ��
	   public int articleCnt(String id,String main_option,String date1,String date2 ) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql="";
	      int count = 0;
	      
	      try {
	         conn = getConnection();
	            sql = "select sum(main_price) from MAINBOARD where main_id = ? and main_option = ? and main_account='��Ȱ��ǰ��' "
	            		+ " and main_writeday between ? and ? ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            pstmt.setString(2, main_option);
	            pstmt.setString(3, date1);
	            pstmt.setString(4, date2);
	            rs = pstmt.executeQuery();
	            
	         if(rs.next()) {
	            count=rs.getInt(1);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {close(conn, rs, pstmt);}
	      return count;
	   }
	   
	 //��ȭ��Ȱ��
	   public int culturalCnt(String id,String main_option,String date1,String date2 ) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql="";
	      int count = 0;
	      
	      try {
	         conn = getConnection();
	            sql = "select sum(main_price) from MAINBOARD where main_id = ? and main_option = ? and main_account='��ȭ��Ȱ��' "
	            		+ " and main_writeday between ? and ? ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            pstmt.setString(2, main_option);
	            pstmt.setString(3, date1);
	            pstmt.setString(4, date2);
	            rs = pstmt.executeQuery();
	            
	         if(rs.next()) {
	            count=rs.getInt(1);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {close(conn, rs, pstmt);}
	      return count;
	   }
	   
	   //�̿��Ƿ�
	   public int beautyCnt(String id,String main_option,String date1,String date2 ) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql="";
	      int count = 0;
	      
	      try {
	         conn = getConnection();
	            sql = "select sum(main_price) from MAINBOARD where main_id = ? and main_option = ? and main_account='�̿��Ƿ�' "
	            		+ " and main_writeday between ? and ? ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            pstmt.setString(2, main_option);
	            pstmt.setString(3, date1);
	            pstmt.setString(4, date2);
	            rs = pstmt.executeQuery();
	            
	         if(rs.next()) {
	            count=rs.getInt(1);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {close(conn, rs, pstmt);}
	      return count;
	   }   	
		
	   
	   
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//�����׸�
	 //�޿�
	   public int payCnt(String id,String main_option,String date1,String date2 ) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql="";
	      int count = 0;
	      
	      try {
	         conn = getConnection();
	            sql = "select sum(main_price) from MAINBOARD where main_id = ? and main_option = ? and main_account='�޿�' "
	            		+ " and main_writeday between ? and ? ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            pstmt.setString(2, main_option);
	            pstmt.setString(3, date1);
	            pstmt.setString(4, date2);
	            rs = pstmt.executeQuery();
	            
	         if(rs.next()) {
	            count=rs.getInt(1);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {close(conn, rs, pstmt);}
	      return count;
	   }   	
	   
	 //����
	   public int interestCnt(String id,String main_option,String date1,String date2 ) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql="";
	      int count = 0;
	      
	      try {
	         conn = getConnection();
	            sql = "select sum(main_price) from MAINBOARD where main_id = ? and main_option = ? and main_account='���ڼ���' "
	            		+ " and main_writeday between ? and ? ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            pstmt.setString(2, main_option);
	            pstmt.setString(3, date1);
	            pstmt.setString(4, date2);
	            rs = pstmt.executeQuery();
	            
	         if(rs.next()) {
	            count=rs.getInt(1);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {close(conn, rs, pstmt);}
	      return count;
	   }   	
		
	 //��Ÿ
	   public int otherCnt(String id,String main_option,String date1,String date2 ) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql="";
	      int count = 0;
	      
	      try {
	         conn = getConnection();
	            sql = "select sum(main_price) from MAINBOARD where main_id = ? and main_option = ? and main_account='��Ÿ' "
	            		+ " and main_writeday between ? and ? ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            pstmt.setString(2, main_option);
	            pstmt.setString(3, date1);
	            pstmt.setString(4, date2);
	            rs = pstmt.executeQuery();
	            
	         if(rs.next()) {
	            count=rs.getInt(1);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {close(conn, rs, pstmt);}
	      return count;
	   }
	   
	//���� �ݾ� üũ
	public int moneyCheck(String id) {
	  Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql="";
      memVO mem = null;
      int count = 0;
      
      try {
         conn = getConnection();
            sql =	"select  ( " + 
            		"select sum(main_price) main_price from mainboard where main_id = ? " + 
            		" and main_option ='1' and main_writeday between to_char(TRUNC(sysdate,'MM'),'YYYYMMDD') and to_char(last_day(sysdate),'YYYYMMDD') " + 
            		")main_price, " + 
            		"(select to_number(m_money) m_money from memberbd where m_id = ?) m_money " + 
            		"from mainboard " + 
            		"group by main_id";
            System.out.println("�ݾ��� ����==="+sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, id);
            rs = pstmt.executeQuery();
            
         if(rs.next()) {
        	 mem = new memVO();
        	 int tot = rs.getInt(1);
        	 int money = rs.getInt(2);
        	 System.out.println(tot);
        	 System.out.println(money);
        	 if(tot > money) {
        		 System.out.println("����Ÿ��???????????");
        		 count ++;
        	 }
         }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {close(conn, rs, pstmt);}
      return count;
		
	}   	
		
	
}
