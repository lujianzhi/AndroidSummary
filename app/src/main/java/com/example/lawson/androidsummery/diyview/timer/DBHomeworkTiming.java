package com.example.lawson.androidsummery.diyview.timer;

/**
 * Created by Ian.Lu on 2017/9/27.
 * Project : AndroidSummary
 */

public class DBHomeworkTiming {

    private Long id;
    private Long homeworkDtlId;
    private Long timing;
    private String quizId;
    private Long studentId;

    public DBHomeworkTiming() {
    }

    public DBHomeworkTiming(Long id) {
        this.id = id;
    }

    public DBHomeworkTiming(Long id, Long homeworkDtlId, Long timing, String quizId, Long studentId) {
        this.id = id;
        this.homeworkDtlId = homeworkDtlId;
        this.timing = timing;
        this.quizId = quizId;
        this.studentId = studentId;
    }

    /**
     * @Title: getId <BR>
     * @Description: please write your description <BR>
     * @return: Long <BR>
     */
    public Long getId() {
        return id;
    }

    /**
     * @Title: setId <BR>
     * @Description: please write your description <BR>
     * @return: Long <BR>
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @Title: getHomeworkDtlId <BR>
     * @Description: please write your description <BR>
     * @return: Long <BR>
     */
    public Long getHomeworkDtlId() {
        return homeworkDtlId;
    }

    /**
     * @Title: setHomeworkDtlId <BR>
     * @Description: please write your description <BR>
     * @return: Long <BR>
     */
    public void setHomeworkDtlId(Long homeworkDtlId) {
        this.homeworkDtlId = homeworkDtlId;
    }

    /**
     * @Title: getTiming <BR>
     * @Description: please write your description <BR>
     * @return: Long <BR>
     */
    public Long getTiming() {
        return timing;
    }

    /**
     * @Title: setTiming <BR>
     * @Description: please write your description <BR>
     * @return: Long <BR>
     */
    public void setTiming(Long timing) {
        this.timing = timing;
    }

    /**
     * @Title: getQuizId <BR>
     * @Description: please write your description <BR>
     * @return: String <BR>
     */
    public String getQuizId() {
        return quizId;
    }

    /**
     * @Title: setQuizId <BR>
     * @Description: please write your description <BR>
     * @return: String <BR>
     */
    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    /**
     * @Title: getStudentId <BR>
     * @Description: please write your description <BR>
     * @return: Long <BR>
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * @Title: setStudentId <BR>
     * @Description: please write your description <BR>
     * @return: Long <BR>
     */
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }


}
