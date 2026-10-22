package chapter03.lesson5;

import java.sql.*;

/**
 * JDBC 에서 트랜잭션을 열고, INSERT 두 건을 넣은 뒤 ROLLBACK 하는 예제.
 * SQL 의 START TRANSACTION / ROLLBACK 을 Java 로는
 * setAutoCommit(false) / conn.rollback() 으로 표현한다.
 */
public class JdbcTransactionRollback {
    // jdbc:mysql://호스트:포트/DB이름?옵션
    // sslMode=DISABLED : 로컬 실습용으로 SSL 암호화를 끈다
    private static final String URL = "jdbc:mysql://localhost:3306/kdt?sslMode=DISABLED";
    private static final String USER = "root";
    private static final String PASS = "kdtpass";

    public static void main(String[] args) throws SQLException {
        // try-with-resources: 블록이 끝나면 Connection 자동 close
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            // JDBC 기본은 autoCommit=true → SQL 한 줄마다 자동 COMMIT
            // false 로 바꾸면: START TRANSACTION 과 비슷하게 "묶음"이 시작된다
            // 이 안의 INSERT 는 commit() 하기 전까지 DB에 확정되지 않는다
            conn.setAutoCommit(false);

            try {
                // ? 에 값을 바꿔 가며 같은 PreparedStatement 로 INSERT 두 번
                String sql = "INSERT INTO tray (item) VALUES (?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, "java-first");
                    ps.executeUpdate(); // 아직 임시 (미확정)

                    ps.setString(1, "java-second");
                    ps.executeUpdate(); // 아직 임시 (미확정)
                }

                // Atomicity: 묶음 전체를 취소 → 두 INSERT 모두 없던 일로
                // SQL 의 ROLLBACK; 과 같음
                conn.rollback();
                System.out.println("rollback");
            } catch (SQLException e) {
                // 중간에 예외가 나도 임시 변경이 남지 않게 다시 rollback
                conn.rollback();
                throw e; // 예외는 그대로 밖으로 전달
            } finally {
                // 다음 작업(아래 SELECT)이나 Connection 재사용을 위해 기본값으로 복구
                conn.setAutoCommit(true);
            }

            // rollback 이 잘 됐는지 확인: java- 로 시작하는 item 이 몇 개인지
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM tray WHERE item LIKE ?")) {
                ps.setString(1, "java-%");
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    // 방금 넣은 두 건은 취소됐으므로, 예전에 남은 행이 없다면 0
                    System.out.println("count=" + rs.getInt(1));
                }
            }
        } // conn 종료
    }
}
