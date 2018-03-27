package my.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import member.memVO;
import member.MybatisConnector;
import my.db.BoardVO;

public class BoardDBMybatis extends MybatisConnector{
	private final String namespace = "ldg.mybatis";
	
	private static BoardDBMybatis instance = new BoardDBMybatis();
	private BoardDBMybatis() {
	}
	public static BoardDBMybatis getInstance() {
		return instance;
	}
	SqlSession sqlSession;
	
	//����� �ۼ�
	public int haruinsert(BoardVO mainboard, String id) {
		sqlSession = sqlSession();
		int cnt = 0;
		int number = sqlSession.selectOne(namespace+".getNextNumber2",mainboard);
		//��ȣ�� 0�� �ƴѰ��
		if(number!=0) {
			number = number+1;
		//��ȣ�� 0�� ���			
		}else {
			number = 1;
		}
		mainboard.setMain_num(number);
		mainboard.setMain_id(id);
		sqlSession.insert(namespace+".haruinsert",mainboard);
		sqlSession.commit();
		sqlSession.close();
		cnt ++;
		return cnt;
	}
	//�ѵ��ݾ� üũ
	public int moneyCheck(String id) {
		sqlSession = sqlSession();
		Map map = new HashMap();
		int count = 0;
		map.put("id", id);
		BoardVO board = new BoardVO();
		board =  sqlSession.selectOne(namespace+".moneyCheck",map);
		int tot =  Integer.parseInt(board.getMain_price());
		int money =  Integer.parseInt(board.getM_money());
		if(tot > money) {
   		 count = 1;
   	 	}
		return count;
	}
	//����� ����Ʈ
	public List mainList(String id, String key1, String key2, int startRow, int endRow, String startDt, String endDt) {
		sqlSession = sqlSession();
		Map map = new HashMap();
		
		if(startDt == "" || endDt == "") {
			startDt = null; endDt = null; }
		
		map.put("id", id);
		map.put("key1", key1);
		map.put("key2", key2);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		
		List mainList  = sqlSession.selectList(namespace+".mainList",map);
		sqlSession.commit();
		sqlSession.close();
		return mainList;
	}
	//����� ����Ʈ ī��Ʈ
	public int mainCount(String id, String key1, String key2, int startRow, int endRow, String startDt, String endDt) {
		sqlSession = sqlSession();
		
		Map map = new HashMap();
		
		if(startDt == "" || endDt == "") {
			startDt = null; endDt = null; }
		
		map.put("id", id);
		map.put("key1", key1);
		map.put("key2", key2);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		
		int count =  sqlSession.selectOne(namespace+".mainCount",map);
		sqlSession.commit();
		sqlSession.close();
		return count;
	}
	
	//����� ����Ʈ �հ�ݾ�
	public BoardVO totPrice(String id, String key1, String key2, String startDt, String endDt) {
		sqlSession = sqlSession();
		
		Map map = new HashMap();
		
		if(startDt == "" || endDt == "") {
			startDt = null; endDt = null; }
		
		map.put("id", id);
		map.put("key1", key1);
		map.put("key2", key2);
		map.put("startDt", startDt);
		map.put("endDt", endDt);

		String test1 = "";
		String test2 = "";
		BoardVO mainboard = null;
		
		if(key1 == null || key1.equals("1")) {
			//���� �հ踦 �������� ����
			mainboard =  sqlSession.selectOne(namespace+".totPrice",map);
			//���� �հ踦 test1�� ����.
			test1 = mainboard.getTot_spend();
		}
		if(key1 == null || key1.equals("2")) {
			//���� �հ踦 �������� ����
			mainboard =  sqlSession.selectOne(namespace+".totPrice2",map);
			//���� �հ踦 test2�� ����
			 test2 = mainboard.getTot_import();
		}
		if(key1 == null || key1.equals("1")) {
			//test1�� ��Ҵ� �հ踦 mainboard tot_spend�� �־���
		mainboard.setTot_spend(test1);
		}
		if(key1 == null || key1.equals("2")) {
			//test2�� ��Ҵ� �հ踦 mainboard tot_import�� �־���
		mainboard.setTot_import(test2);
		}
	    sqlSession.commit();
		sqlSession.close();
		return mainboard;
	}
	//����� �󼼺���
	public BoardVO Contentview(int num) {
		sqlSession = sqlSession();
		Map map = new HashMap();
		map.put("num", num);
		BoardVO vo = sqlSession.selectOne(namespace+".Contentview", map) ;	
		sqlSession.commit();
		sqlSession.close();
		return vo;
	}
	//����� ����
	public int updateBoard(BoardVO mainboard) {
		sqlSession = sqlSession();
		int check = sqlSession.update(namespace+".updateBoard", mainboard) ;	
		sqlSession.commit();
		sqlSession.close();
		return check;
	}
	//����� ����
	public int deleteBoard(int num) {
		sqlSession = sqlSession();
		int check = sqlSession.delete(namespace+".deleteBoard", num) ;	
		sqlSession.commit();
		sqlSession.close();
		return check;
	}
	
	
	
	
	
	//��Ʈ
	//�ĺ�
	public int foodCnt(String id,String main_option,String date1,String date2 ) {
		sqlSession = sqlSession();
		int count =0;
		Map map = new HashMap();
		
		map.put("id", id);
		map.put("main_option", main_option);
		map.put("date1", date1);
		map.put("date2", date2);
		
		System.out.println(map);
		
		count = sqlSession.selectOne(namespace+".foodCnt", map) ;	
		sqlSession.commit();
		sqlSession.close();
		return count;
	}
	//�����
	 public int carCnt(String id,String main_option,String date1,String date2 ) {
		 sqlSession = sqlSession();
		 int count =0;
			Map map = new HashMap();
			map.put("id", id);
			map.put("main_option", main_option);
			map.put("date1", date1);
			map.put("date2", date2);
			count = sqlSession.selectOne(namespace+".carCnt", map) ;	
			sqlSession.commit();
			sqlSession.close();
			return count;
	 }
	//�ǰ��Ƿ�
		 public int healthCnt(String id,String main_option,String date1,String date2 ) {
			 sqlSession = sqlSession();
				Map map = new HashMap();
				int count = 0;
				map.put("id", id);
				map.put("main_option", main_option);
				map.put("date1", date1);
				map.put("date2", date2);
				 count = sqlSession.selectOne(namespace+".healthCnt", map) ;	
				sqlSession.commit();
				sqlSession.close();
				return count;
		 }
		//��ź�
		   public int phoneCnt(String id,String main_option,String date1,String date2 ) {
			   sqlSession = sqlSession();
				Map map = new HashMap();
				int count =0;
				map.put("id", id);
				map.put("main_option", main_option);
				map.put("date1", date1);
				map.put("date2", date2);
				count = sqlSession.selectOne(namespace+".phoneCnt", map) ;	
				sqlSession.commit();
				sqlSession.close();
				return count;
			   
		   }
		 //��Ȱ��ǰ��
		   public int articleCnt(String id,String main_option,String date1,String date2 ) {
			   sqlSession = sqlSession();
				Map map = new HashMap();
				int count =0;
				map.put("id", id);
				map.put("main_option", main_option);
				map.put("date1", date1);
				map.put("date2", date2);
				count = sqlSession.selectOne(namespace+".articleCnt", map) ;	
				sqlSession.commit();
				sqlSession.close();
				return count;
		   }
		   //��ȭ��Ȱ��
		   public int culturalCnt(String id,String main_option,String date1,String date2 ) {
			   sqlSession = sqlSession();
			   int count = 0;
				Map map = new HashMap();
				map.put("id", id);
				map.put("main_option", main_option);
				map.put("date1", date1);
				map.put("date2", date2);
				
				System.out.println(map);
				count = sqlSession.selectOne(namespace+".culturalCnt", map);
				
				sqlSession.commit();
				sqlSession.close();
				return count;
		   }
		 //�̿��Ƿ�
		   public int beautyCnt(String id,String main_option,String date1,String date2 ) {
			   sqlSession = sqlSession();
				Map map = new HashMap();
				int count =0;
				map.put("id", id);
				map.put("main_option", main_option);
				map.put("date1", date1);
				map.put("date2", date2);
				count = sqlSession.selectOne(namespace+".beautyCnt", map) ;	
				sqlSession.commit();
				sqlSession.close();
				return count;
		   }
		   
		   //����
		   //�޿�
		   public int payCnt(String id,String main_option,String date1,String date2 ) {
			    sqlSession = sqlSession();
				Map map = new HashMap();
				int count = 0;
				map.put("id", id);
				map.put("main_option", main_option);
				map.put("date1", date1);
				map.put("date2", date2);
				count = sqlSession.selectOne(namespace+".payCnt", map) ;	
				sqlSession.commit();
				sqlSession.close();
				return count;
		   }
		 //���ڼ���
		   public int interestCnt(String id,String main_option,String date1,String date2 ) {
			   sqlSession = sqlSession();
			   int count = 0;
				Map map = new HashMap();
				map.put("id", id);
				map.put("main_option", main_option);
				map.put("date1", date1);
				map.put("date2", date2);
				count = sqlSession.selectOne(namespace+".interestCnt", map) ;	
				sqlSession.commit();
				sqlSession.close();
				return count;
		   }
		   
		 //��Ÿ
		   public int otherCnt(String id,String main_option,String date1,String date2 ) {
			   sqlSession = sqlSession();
			   int count = 0;
				Map map = new HashMap();
				map.put("id", id);
				map.put("main_option", main_option);
				map.put("date1", date1);
				map.put("date2", date2);
				count = sqlSession.selectOne(namespace+".otherCnt", map) ;	
				sqlSession.commit();
				sqlSession.close();
				return count;
		   }
}

	
	
	
	
	
	
	
	
	
	
