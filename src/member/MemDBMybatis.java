package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import member.MybatisConnector;



public class MemDBMybatis extends MybatisConnector{
	private final String namespace = "ldg.mybatis";
	
	private static MemDBMybatis instance = new MemDBMybatis();
	private MemDBMybatis() {
	}
	public static MemDBMybatis getInstance() {
		return instance;
	}
	SqlSession sqlSession;
	
	//로그인
	public int login(String id, String pass) {
		sqlSession = sqlSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pass", pass);
		
		int x = sqlSession.selectOne(namespace+".login", map) ;	
		sqlSession.close();
		return x;
	}
	
	//이름 가져오기
	public String getname(String id) {
		sqlSession = sqlSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		
		String name = sqlSession.selectOne(namespace+".getname", map) ;	
		sqlSession.close();
		return name;
	}
	
	//회원등록
	public int insert(memVO member) {
		sqlSession = sqlSession();
		int cnt = 0;
		int number = sqlSession.selectOne(namespace+".getNextNumber",member);
		//번호가 0이 아닌경우
		if(number!=0) {
			number = number+1;
		//번호가 0인 경우			
		}else {
			number = 1;
		}
		member.setM_num(number);
		sqlSession.insert(namespace+".insertMem",member);
		sqlSession.commit();
		sqlSession.close();
		cnt ++;
		return cnt;
	}
	
	//내정보 보기
		public memVO myhomeView2(String id) {
			sqlSession = sqlSession();
			Map map = new HashMap();
			map.put("id", id);
			
			memVO member = sqlSession.selectOne(namespace+".myhomeView2", map) ;	
			sqlSession.commit();
			sqlSession.close();
			return member;
		}
	
	//내정보 보기
	public memVO myhomeView(String num) {
		sqlSession = sqlSession();
		Map map = new HashMap();
		map.put("num", num);
		
		memVO member = sqlSession.selectOne(namespace+".myhomeView", map) ;	
		sqlSession.commit();
		sqlSession.close();
		return member;
	}
	
	//내정보 수정
	public int updateMember(memVO member) {
		sqlSession = sqlSession();
		int pwdcheck = sqlSession.update(namespace+".updateMember", member) ;	
		sqlSession.commit();
		sqlSession.close();
		return pwdcheck;
	}
	
	//회원탈퇴
	public int deleteMember(String num, String passwd, String id) {
		sqlSession = sqlSession();
		Map map = new HashMap();
		map.put("num", num);
		map.put("passwd", passwd);
		map.put("id", id);
		int chk = sqlSession.delete(namespace+".deleteMember", map);
		sqlSession.commit();
		sqlSession.close();
		return chk;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//어드민 회원관리 (리스트)
	public List memList(int startRow, int endRow, String keyField, String keyWord) {
		sqlSession = sqlSession();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Map map = new HashMap();
		
		if(keyWord == "") {
			keyWord = null;
		}
		
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("keyField", keyField);
		map.put("keyWord", keyWord);
		
		
		
		System.out.println("회원리스트 : "+map);
		
		List memList = sqlSession.selectList(namespace+".memList", map) ;
		
		sqlSession.close();
		
		return memList;
	}
	
	//어드민 회원관리(카운트)
	public int SelectCountMem(int startRow, int endRow, String keyField, String keyWord) {
		int x=0;
		sqlSession = sqlSession();
		Map map = new HashMap();
	
		
		if(keyWord == "") {
			keyWord = null;
		}
		
		
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("keyField", keyField);
		map.put("keyWord", keyWord);
		System.out.println("회원카운트 : "+map);
		
		x = sqlSession.selectOne(namespace+".SelectCountMem", map) ;		//selectOne (오브젝트) /오브젝트인가? 컬렉션인가?
		sqlSession.close();
		return x;
		
		
	}
	
}
	
	
	
	
	
	
	
	
	
	
