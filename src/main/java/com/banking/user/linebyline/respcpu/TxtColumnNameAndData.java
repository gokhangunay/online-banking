package com.banking.user.linebyline.respcpu;

public class TxtColumnNameAndData {

    private Integer id;
    private String applid;
    private String transaction;
    private String program;
    private String tasks;
    private String avgResponseTime;
    private String maxResponseTime;
    private String avgUserCpuTime;
    private String maxUserCpuTime;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplid() {
        return applid;
    }

    public void setApplid(String applid) {
        this.applid = applid;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getAvgResponseTime() {
        return avgResponseTime;
    }

    public void setAvgResponseTime(String avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }

    public String getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(String maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public String getAvgUserCpuTime() {
        return avgUserCpuTime;
    }

    public void setAvgUserCpuTime(String avgUserCpuTime) {
        this.avgUserCpuTime = avgUserCpuTime;
    }

    public String getMaxUserCpuTime() {
        return maxUserCpuTime;
    }

    public void setMaxUserCpuTime(String maxUserCpuTime) {
        this.maxUserCpuTime = maxUserCpuTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

