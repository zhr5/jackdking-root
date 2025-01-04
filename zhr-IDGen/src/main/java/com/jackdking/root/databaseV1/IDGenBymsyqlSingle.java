package com.jackdking.root.databaseV1;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.*;

public class IDGenBymsyqlSingle {

        private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/comm?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "123";

        public static String getId(int bizType) {
            String selectSql = "SELECT id FROM id_generator WHERE biz_type = ?";
            String updateSql = "UPDATE id_generator SET id = id + 1 WHERE biz_type = ?";
            String insertSql = "INSERT INTO id_generator (biz_type, id) VALUES (?, 1)";

            try (Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement selectStmt = c.prepareStatement(selectSql);
                 PreparedStatement updateStmt = c.prepareStatement(updateSql);
                 PreparedStatement insertStmt = c.prepareStatement(insertSql)) {

                // 查询 ID
                selectStmt.setInt(1, bizType);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        // 更新 ID
                        updateStmt.setInt(1, bizType);
                        updateStmt.executeUpdate();
                        return String.valueOf(id);
                    } else {
                        // 插入新记录  理论上应该初始化为 1，但为了兼容旧数据，这里初始化为 0
                        insertStmt.setInt(1, bizType);
                        insertStmt.executeUpdate();
                        return "1";
                    }
                }

            } catch (SQLException e) {
                // 记录详细日志并抛出自定义异常
                e.printStackTrace();
                throw new RuntimeException("Failed to generate ID for bizType: " + bizType, e);
            }
        }
    public static void main(String[] args) {
        System.out.println(getId(1));
    }

}
