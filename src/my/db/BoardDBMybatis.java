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
	
	//가계부 작성
	public int haruinsert(BoardVO mainboard, String id) {
		sqlSession = sqlSession();
		int cnt = 0;
		int number = sqlSession.selectOne(namespace+".getNextNumber2",mainboard);
		//번호가 0이 아닌경우
		if(number!=0) {
			number = number+1;
		//번호가 0인 경우			
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
	//한도금액 체크
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
	//가계부 리스트
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
	//가계부 리스트 카운트
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
	
	//가계부 리스트 합계금액
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
			//지출 합계를 가져오는 쿼리
			mainboard =  sqlSession.selectOne(namespace+".totPrice",map);
			//지출 합계를 test1에 담음.
			test1 = mainboard.getTot_spend();
		}
		if(key1 == null || key1.equals("2")) {
			//수입 합계를 가져오는 쿼리
			mainboard =  sqlSession.selectOne(namespace+".totPrice2",map);
			//수입 합계를 test2에 담음
			 test2 = mainboard.getTot_import();
		}
		if(key1 == null || key1.equals("1")) {
			//test1에 담았던 합계를 mainboard tot_spend에 넣어줌
		mainboard.setTot_spend(test1);
		}
		if(key1 == null || key1.equals("2")) {
			//test2에 담았던 합계를 mainboard tot_import에 넣어줌
		mainboard.setTot_import(test2);
		}
	    sqlSession.commit();
		sqlSession.close();
		return mainboard;
	}
	//가계부 상세보기
	public BoardVO Contentview(int num) {
		sqlSession = sqlSession();
		Map map = new HashMap();
		map.put("num", num);
		BoardVO vo = sqlSession.selectOne(namespace+".Contentview", map) ;	
		sqlSession.commit();
		sqlSession.close();
		return vo;
	}
	//가계부 수정
	public int updateBoard(BoardVO mainboard) {
		sqlSession = sqlSession();
		int check = sqlSession.update(namespace+".updateBoard", mainboard) ;	
		sqlSession.commit();
		sqlSession.close();
		return check;
	}
	//가계부 삭제
	public int deleteBoard(int num) {
		sqlSession = sqlSession();
		int check = sqlSession.delete(namespace+".deleteBoard", num) ;	
		sqlSession.commit();
		sqlSession.close();
		return check;
	}
	
	
	
	
	
	//차트
	//식비
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
	//교통비
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
	//건강의료
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
		//통신비
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
		 //생활용품비
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
		   //문화생활비
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
		 //미용의류
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
		   
		   //수입
		   //급여
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
		 //이자수익
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
		   
		 //기타
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

	
	
	
	
	
	
	
	
	
	
