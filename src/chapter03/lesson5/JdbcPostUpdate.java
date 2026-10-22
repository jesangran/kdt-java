package chapter03.lesson5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * jdbc_post 테이블의 기존 글 body 를 UPDATE 하는 예제.
 * WHERE id = ? 로 특정 글 1건만 수정한다.
 */
public class JdbcPostUpdate {
    // jdbc:mysql://호스트:포트/DB이름?옵션
    // sslMode=DISABLED : 로컬 실습용으로 SSL 암호화를 끈다
    private static final String URL = "jdbc:mysql://localhost:3306/kdt?sslMode=DISABLED";
    private static final String USER = "root";
    private static final String PASS = "kdtpass";

    public static void main(String[] args) throws SQLException {
        // ? 는 나중에 값을 넣을 자리(플레이스홀더)
        // SET body = ? → 바꿀 내용, WHERE id = ? → 어떤 글을 바꿀지
        String sql = "UPDATE jdbc_post SET body = ? WHERE id = ?";

        // try-with-resources: 블록이 끝나면 Connection / PreparedStatement 를 자동으로 close
        // DB 연결은 네트워크/서버 문제로 실패할 수 있어서 SQLException 처리가 필요하다
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS); // 실제 DB 접속
             PreparedStatement ps = conn.prepareStatement(sql)) { // SQL 준비

            // setXxx(몇 번째 ?, 값) — ? 순서도 1부터 시작
            ps.setString(1, "edited java"); // 1번 ? ← body (수정할 내용)
            ps.setInt(2, 4);                // 2번 ? ← id (수정할 글 번호)

            // INSERT/UPDATE/DELETE 는 executeUpdate() 사용 (SELECT 는 executeQuery)
            // 반환값: 영향을 받은 행 수. 해당 id 가 없으면 0
            int n = ps.executeUpdate();

            System.out.println("updated " + n);
        } // conn, ps 종료 (DB 연결 종료)
    }
}
