/*package my.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import board.BoardDataBean;

서버에서 DB에 있는 자료를 가져오는 실행할수 있도록 도와주는 
접근제한자, 메소드명, 파라미터, 리턴타입(오브젝트 int, String, setter getteror,  컬렉션)

public class MybatisDao extends MybatisConnector{
	private final String namespace = "ldg.mybatis";
	private static MybatisDao instance = new MybatisDao();
	public static MybatisDao getInstance() {
		return instance;
	}
	SqlSession sqlSession;
	
	public List<BoardDataBean> selectBoard(){
		sqlSession = sqlSession();
		System.out.println("selectboard");
		try {
			return sqlSession.selectList(namespace + ".boardList");
		}finally {
			sqlSession.close();
		}
	}
	
	public List<BoardDataBean> selectBoard(int num){
		sqlSession = sqlSession();
		System.out.println("selectboard");
		Map map = new HashMap();
			map.put("num", num);
		try {
			return sqlSession.selectList(namespace + ".boardList", map);
		}finally {
			sqlSession.close();
		}
	}
	
	public List<BoardDataBean> selectBoard(String boardid){
		sqlSession = sqlSession();
		System.out.println("selectboard");
		Map map = new HashMap();
		map.put("boardid", boardid);
		try {
			return sqlSession.selectList(namespace + ".boardList", map);
		}finally {
			sqlSession.close();
		}
	}
}
*/