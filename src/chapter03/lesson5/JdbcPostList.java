package chapter03.lesson5;

import java.sql.*;

/**
 * jdbc_post 테이블의 글 목록을 조회해서 출력하는 예제.
 * SELECT 결과를 한 행씩 읽어 id, title 을 콘솔에 찍는다.
 */
public class JdbcPostList {
    // jdbc:mysql://호스트:포트/DB이름?옵션
    // sslMode=DISABLED : 로컬 실습용으로 SSL 암호화를 끈다
    private static final String URL = "jdbc:mysql://localhost:3306/kdt?sslMode=DISABLED";
    private static final String USER = "root";
    private static final String PASS = "kdtpass";

    public static void main(String[] args) throws SQLException {
        // 실행할 SQL. ORDER BY id 로 id 오름차순 정렬
        String sql = "SELECT id, title from jdbc_post ORDER BY id";

        // try-with-resources: 블록이 끝나면 Connection / PreparedStatement 를 자동으로 close
        // DB 연결은 네트워크/서버 문제로 실패할 수 있어서 SQLException 처리가 필요하다
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS); // 실제 DB 접속
             PreparedStatement ps = conn.prepareStatement(sql)) { // SQL을 미리 준비 (나중에 ? 파라미터도 넣을 수 있음)

            // ResultSet 도 사용이 끝나면 닫아야 해서 안쪽 try-with-resources 로 감싼다
            try (ResultSet rs = ps.executeQuery()) { // SELECT 실행 → 결과 표(행/열)를 받는다
                // rs.next(): 커서를 다음 행으로 이동. 행이 있으면 true, 없으면 false
                while (rs.next()) {
                    // 컬럼 이름으로 값을 꺼낼 수 있다 (번호 getInt(1) 대신 getInt("id"))
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    System.out.println(id + " " + title);
                }
            } // rs 종료
        } // conn, ps 종료 (DB 연결 종료)
    }
}
