package chapter03.lesson5;

import java.sql.*;

/**
 * jdbc_post 테이블에 새 글을 INSERT 하는 예제.
 * 먼저 jdbc_member 에서 회원 id 하나를 찾은 뒤, 그 id 로 글을 넣는다.
 */
public class JdbcPostInsert {
    // jdbc:mysql://호스트:포트/DB이름?옵션
    // sslMode=DISABLED : 로컬 실습용으로 SSL 암호화를 끈다
    private static final String URL = "jdbc:mysql://localhost:3306/kdt?sslMode=DISABLED";
    private static final String USER = "root";
    private static final String PASS = "kdtpass";

    public static void main(String[] args) throws SQLException {
        // try-with-resources: 블록이 끝나면 Connection 을 자동으로 close
        // DB 연결은 네트워크/서버 문제로 실패할 수 있어서 SQLException 처리가 필요하다
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) { // 실제 DB 접속

            // 글(jdbc_post)은 회원(member_id)이 필요해서, 먼저 회원 id 하나를 조회한다
            int memberId = 0;
            String findSql = "SELECT id from jdbc_member ORDER BY id LIMIT 1;"; // 가장 작은 id 1건만

            try (PreparedStatement find = conn.prepareStatement(findSql); // SQL 준비
                 ResultSet rs = find.executeQuery()) { // SELECT 실행 → 결과 표

                // 회원이 한 명도 없으면 INSERT 할 수 없으므로 종료
                if (!rs.next()) {
                    System.out.println("no jdbc_member");
                    return;
                }

                memberId = rs.getInt("id"); // 찾은 회원 id 저장
            } // find, rs 종료

            // ? 는 나중에 값을 넣을 자리(플레이스홀더). SQL 문자열에 값을 직접 붙이지 않는다
            String sql = "INSERT INTO jdbc_post (member_id, title, body) VALUES (?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                // setXxx(몇 번째 ?, 값) — ? 순서도 1부터 시작
                ps.setInt(1, memberId);      // 1번 ? ← member_id
                ps.setString(2, "jdbc");     // 2번 ? ← title
                ps.setString(3, "from java"); // 3번 ? ← body

                // INSERT/UPDATE/DELETE 는 executeUpdate() 사용 (SELECT 는 executeQuery)
                // 반환값: 영향을 받은 행 수. INSERT 한 건이면 보통 1.
                int n = ps.executeUpdate();

                System.out.println("inserted " + n);
            } // ps 종료
        } // conn 종료 (DB 연결 종료)
    }
}
