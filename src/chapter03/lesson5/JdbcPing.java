package chapter03.lesson5;

import java.sql.*;

/**
 * DB 연결이 되는지 확인하는 간단한 ping 예제.
 * SELECT 1 을 실행해서 결과가 오면 연결 성공으로 본다.
 */
public class JdbcPing {
    // jdbc:mysql://호스트:포트/DB이름?옵션
    // sslMode=DISABLED : 로컬 실습용으로 SSL 암호화를 끈다
    private static final String URL = "jdbc:mysql://localhost:3306/kdt?sslMode=DISABLED";
    private static final String USER = "root";
    private static final String PASS = "kdtpass";

    public static void main(String[] args) throws SQLException {
        // try-with-resources: 블록이 끝나면 Connection / PreparedStatement / ResultSet 을 자동으로 close
        // DB 연결은 네트워크/서버 문제로 실패할 수 있어서 SQLException 처리가 필요하다
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS); // 실제 DB 접속
             PreparedStatement ps = conn.prepareStatement("SELECT 1"); // 실행할 SQL을 미리 준비
             ResultSet rs = ps.executeQuery()) { // SELECT 실행 결과(표)를 받는다. ResultSet은 커서처럼 한 행씩 이동

            // 커서를 다음 행으로 이동. 행이 있으면 true
            rs.next();

            // 1번 컬럼 값을 꺼낸다 (인덱스는 1부터 시작)
            // SELECT 1 의 결과는 숫자 1 → getBoolean(1) 이면 true 로 나온다
            // System.out.println("db ping= " + rs.getInt(1));
            System.out.println("db ping= " + rs.getBoolean(1));
        } // 여기서 conn, ps, rs 자동 종료
    }
}
