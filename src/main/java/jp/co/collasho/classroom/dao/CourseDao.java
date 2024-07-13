package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.constants.DbConstants;
import jp.co.collasho.classroom.constants.ErrorMessages;
import jp.co.collasho.classroom.dto.SearchCriteriaDto;
import jp.co.collasho.classroom.entity.CourseEntity;

/**
 * Courseテーブル用DAO
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

    /**
     * SELECT 講座IDを指定して取得する
     * 
     * @param courseId 講座コード
     * @return CourseEntity
     */
    public CourseEntity selectByCourseId(String courseId) {
        String query =
                "select c.course_id, c.course_name, t.day_of_week, t.period from Courses as c inner join TimeTable as t on t.course_id = c.course_id where c.course_id = ?";

        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            pStmt.setString(1, courseId);
            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                return this.getEntityFromReslut(rs);
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(ErrorMessages.DAO_SELECT_ERROR, e);
        }
    }

    /**
     * SELECT 出席番号を指定して登録講座リストを取得する
     * 
     * @param studentId 出席番号
     * @return 講座エンティティのリスト
     */
    public List<CourseEntity> selectByStudentId(String studentId) {
        List<CourseEntity> courses = new ArrayList<>();
        String query =
                "select c.course_id, c.course_name, t.day_of_week, t.period from Courses as c inner join TimeTable as t on t.course_id = c.course_id inner join Enrollments as e on e.course_id = t.course_id where e.student_id = ?;";

        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            pStmt.setString(1, studentId);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                courses.add(this.getEntityFromReslut(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(ErrorMessages.DAO_SELECT_ERROR, e);
        }
        return courses;
    }

    /**
     * SELECT 検索条件に合致する講座リストを取得する
     * 
     * @param criteria 検索条件オブジェクト
     * @return 講座エンティティのリスト
     */
    public List<CourseEntity> selectByCriteria(SearchCriteriaDto criteria) {
        List<CourseEntity> courses = new ArrayList<>();

        String query =
                "select c.course_id, c.course_name, t.day_of_week, t.period from Courses as c inner join TimeTable as t on t.course_id = c.course_id where c.course_id like ? and c.course_name like ? and t.day_of_week like ? and t.period like ? order by t.day_of_week asc, t.period asc;";

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
            throw new RuntimeException(ErrorMessages.DAO_SELECT_ERROR, e);
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
        CourseEntity course = new CourseEntity();

        course.setCourseId(rs.getString(DbConstants.COURSE_ID));
        course.setCourseName(rs.getString(DbConstants.COURSE_NAME));
        course.setDayOfWeekNum(rs.getString(DbConstants.DAY_OF_WEEK));
        course.setPeriod(rs.getString(DbConstants.PERIOD));

        return course;
    }
}
