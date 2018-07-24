package com.potoyang.learn.fileupload.entity;

public class ExcelInfo {
    private Integer programSetId;

    private String programSetName;

    private Integer programId;

    private String programName;

    private Integer periodNumber;

    private String path;

    public Integer getProgramSetId() {
        return programSetId;
    }

    public void setProgramSetId(Integer programSetId) {
        this.programSetId = programSetId;
    }

    public String getProgramSetName() {
        return programSetName;
    }

    public void setProgramSetName(String programSetName) {
        this.programSetName = programSetName == null ? null : programSetName.trim();
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName == null ? null : programName.trim();
    }

    public Integer getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(Integer periodNumber) {
        this.periodNumber = periodNumber;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    @Override
    public String toString() {
        return "ExcelInfo{" +
                "programSetId=" + programSetId +
                ", programSetName='" + programSetName + '\'' +
                ", programId=" + programId +
                ", programName='" + programName + '\'' +
                ", periodNumber=" + periodNumber +
                ", path='" + path + '\'' +
                '}';
    }
}