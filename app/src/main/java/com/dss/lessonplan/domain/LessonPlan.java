package com.dss.lessonplan.domain;

public class LessonPlan {

    private String aclass;
    private String subject;
    private String lesson;
    private String topic;
    private String objective;
    private String classwork;
    private String homework;
    private String time;
    private String date;
    private String message;

    public String getAclass() {
        return aclass;
    }

    public void setAclass(String aclass) {
        this.aclass = aclass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getClasswork() {
        return classwork;
    }

    public void setClasswork(String classwork) {
        this.classwork = classwork;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
