package com.beesinergi.model;

public class AppStatus {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_status.pk_app_status
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    private Integer pkAppStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_status.status_name
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    private String statusName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_status.status_type
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    private String statusType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_status.status_code
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    private String statusCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_status.pk_app_status
     *
     * @return the value of app_status.pk_app_status
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    public Integer getPkAppStatus() {
        return pkAppStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_status.pk_app_status
     *
     * @param pkAppStatus the value for app_status.pk_app_status
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    public void setPkAppStatus(Integer pkAppStatus) {
        this.pkAppStatus = pkAppStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_status.status_name
     *
     * @return the value of app_status.status_name
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_status.status_name
     *
     * @param statusName the value for app_status.status_name
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName == null ? null : statusName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_status.status_type
     *
     * @return the value of app_status.status_type
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    public String getStatusType() {
        return statusType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_status.status_type
     *
     * @param statusType the value for app_status.status_type
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    public void setStatusType(String statusType) {
        this.statusType = statusType == null ? null : statusType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_status.status_code
     *
     * @return the value of app_status.status_code
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_status.status_code
     *
     * @param statusCode the value for app_status.status_code
     *
     * @mbggenerated Fri Nov 27 11:26:43 ICT 2015
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode == null ? null : statusCode.trim();
    }
}