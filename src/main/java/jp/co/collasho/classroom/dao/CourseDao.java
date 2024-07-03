package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.dto.SearchCriteriaDto;
import jp.co.collasho.classroom.entity.CourseEntity;

/**
 * 講座データへのアクセス用オブジェクト
 */
public class CourseDao {
    /** コネクション */
    private Connection conn;

    /**
     * コンストラクタ
     * 
     * @param conn コネクション
     */
    public CourseDao(Connection conn) {
        this.conn = conn;
    }

    public CourseEntity selectByCourseId(String courseId) {
        String query = "select * from Courses where course_id = ?";

        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            pStmt.setString(1, courseId);
            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                return this.getEntityFromReslut(rs);
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。", e);
        }
    }

    /**
     * 出席番号から登録講座リストを取得する
     * 
     * @param studentId 出席番号
     * @return 登録講座エンティティのリスト
     */
    public List<CourseEntity> selectByStudentId(String studentId) {
        List<CourseEntity> courses = new ArrayList<>();
        String query =
                "select e.course_id, c.course_name, c.day_of_week, c.period from Enrollments as e inner join Courses as c on c.course_id = e.course_id where e.student_id = ? ;";

        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            pStmt.setString(1, studentId);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                courses.add(this.getEntityFromReslut(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。", e);
        }
        return courses;
    }

    /**
     * 検索条件に合致する講座リストを取得する
     * 
     * @param criteria 検索条件オブジェクト
     * @return 講座リスト
     */
    public List<CourseEntity> selectByCriteria(SearchCriteriaDto criteria) {
        List<CourseEntity> courses = new ArrayList<>();

        String query =
                "select course_id, course_name, day_of_week, period from Courses where course_id like ? and course_name like ? and day_of_week like ? and period like ? order by day_of_week asc, period asc;";

        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            pStmt.setString(1, "%" + criteria.getCourseId() + "%");
            pStmt.setString(2, "%" + criteria.getCourseName() + "%");
            pStmt.setString(3, "%" + criteria.getDayOfWeek().getNum() + "%");
            pStmt.setString(4, "%" + criteria.getPeriod() + "%");
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                courses.add(this.getEntityFromReslut(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。", e);
        }
        return courses;
    }

    /**
     * ResultSetから講座エンティティを生成
     * 
     * @param rs java.sql.ResultSet
     * @return 講座エンティティ
     * @throws SQLException
     */
    private CourseEntity getEntityFromReslut(ResultSet rs) throws SQLException {
        CourseEntity e = new CourseEntity();

        e.setCourseId(rs.getString("course_id"));
        e.setCourseName(rs.getString("course_name"));
        e.setDayOfWeekNum(rs.getString("day_of_week"));
        e.setPeriod(rs.getString("period"));

        return e;
    }
}
