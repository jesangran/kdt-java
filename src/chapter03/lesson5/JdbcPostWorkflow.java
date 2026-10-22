package chapter03.lesson5;

import java.sql.*;

/**
 * 회원 조회 → jdbc-flow 글 확인/추가 → 본문 수정 → 최종 결과 출력 흐름 예제.
 * Connection / PreparedStatement / ResultSet / executeQuery / executeUpdate 만 사용한다.
 */
public class JdbcPostWorkflow {
    // jdbc:mysql://호스트:포트/DB이름?옵션
    // sslMode=DISABLED : 로컬 실습용으로 SSL 암호화를 끈다
    private static final String URL = "jdbc:mysql://localhost:3306/kdt?sslMode=DISABLED";
    private static final String USER = "root";
    private static final String PASS = "kdtpass";

    public static void main(String[] args) throws SQLException {
        // try-with-resources: 블록이 끝나면 Connection 을 자동으로 close
        // DB 연결은 네트워크/서버 문제로 실패할 수 있어서 SQLException 처리가 필요하다
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) { // 실제 DB 접속

            // 1) 첫 번째 회원 id 조회. 없으면 종료
            int memberId ;
            String findMemberSql = "SELECT id from jdbc_member ORDER BY id LIMIT 1";

            try (PreparedStatement ps = conn.prepareStatement(findMemberSql); // SQL 준비
                 ResultSet rs = ps.executeQuery()) { // SELECT 실행 → 결과 표

                if (!rs.next()) {
                    System.out.println("no jdbc_member");
                    return;
                }

                memberId = rs.getInt("id"); // 찾은 회원 id 저장
            } // find, rs 종료

            // 2) title = 'jdbc-flow' 인 기존 행 id 찾기
            // int 는 null 이 없어서, 못 찾으면 0 으로 둔다 (id 는 보통 1부터 시작)
            int postId = 0;
            String findPostSql = "SELECT id from jdbc_post WHERE title = ? ORDER BY id";

            try (PreparedStatement ps = conn.prepareStatement(findPostSql)) {
                ps.setString(1, "jdbc-flow"); // 1번 ? ← title

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        postId = rs.getInt("id");
                    }
                } // rs 종료
            } // ps 종료

            // 3) 행이 없을 때만 INSERT (title = jdbc-flow, body = draft)
            // 4) INSERT 한 경우에만 id 를 다시 조회 (2번에서 찾았으면 postId 그대로 사용)
            if (postId == 0) {
                // ? 는 나중에 값을 넣을 자리(플레이스홀더)
                String insertSql = "INSERT INTO jdbc_post (member_id, title, body) VALUES (?, ?, ?)";

                try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                    ps.setInt(1, memberId);       // 1번 ? ← member_id
                    ps.setString(2, "jdbc-flow"); // 2번 ? ← title
                    ps.setString(3, "draft");     // 3번 ? ← body

                    // INSERT/UPDATE/DELETE 는 executeUpdate() 사용
                    int n = ps.executeUpdate();
                    System.out.println("inserted " + n);
                } // ps 종료

                try (PreparedStatement ps = conn.prepareStatement(findPostSql)) {
                    ps.setString(1, "jdbc-flow");

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            postId = rs.getInt("id");
                        }
                    } // rs 종료
                } // ps 종료
            }

            // 5) 해당 id 한 행의 본문을 verified 로 수정
            String updateSql = "UPDATE jdbc_post SET body = ? WHERE id = ?";

            try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                ps.setString(1, "verified"); // 1번 ? ← body
                ps.setInt(2, postId);        // 2번 ? ← id

                ps.executeUpdate(); //autocloseable
            } // ps 종료

            // 6) 최종 id · title · body 출력
            String selectSql = "SELECT id, title, body from jdbc_post WHERE id = ?";

            try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
                ps.setInt(1, postId);

                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String body = rs.getString("body");
                    System.out.println(id + " " + title + " " + body);
                } // rs 종료
            } // ps 종료
        } // conn 종료 (DB 연결 종료)
    }
}
