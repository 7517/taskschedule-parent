package com.edison.service.taskschedule.business.task.domain;

import com.edison.saas.common.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * 日程
 * @author icode
 */
@Entity()
@Table(name = "schedule_schedule")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class Schedule extends BaseEntity<Long>{

	public static final String PROPERTY_REMINDER_TIME = "reminderTime";
	public static final String PROPERTY_SCHEDULE_DETAILS = "scheduleDetails";
	public static final String PROPERTY_DESIGNEE_DEPT_UID = "designeeDeptUid";
	public static final String PROPERTY_DESIGNEE_UID = "designeeUid";
	public static final String PROPERTY_BEGIN_AT = "beginAt";
	public static final String PROPERTY_DESIGNEE_DEPT_ID = "designeeDeptId";
	public static final String PROPERTY_SCHEDULE_STATUS = "scheduleStatus";
	public static final String PROPERTY_END_AT = "endAt";
	public static final String PROPERTY_SCHEDULE_TITLE = "scheduleTitle";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 提醒时间
    * 
    */
    @Column(name = "reminder_time", nullable = true, updatable = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date reminderTime;

    /**
    * 日程详情
    * 
    */
    @Column(name = "schedule_details", nullable = true, updatable = true)
	@Size(max = 255, message = "日程详情超长，最多255个字符")
	private String scheduleDetails;

    /**
    * 被指派部门主任
    * 
    */
    @Column(name = "designee_dept_uid", nullable = true, updatable = true)
	@Size(max = 255, message = "被指派部门主任超长，最多255个字符")
	private String designeeDeptUid;

    /**
    * 被指派人
    * 
    */
    @Column(name = "designee_uid", nullable = true, updatable = true)
	@Size(max = 255, message = "被指派人超长，最多255个字符")
	private String designeeUid;

    /**
    * 开始时间
    * 
    */
    @Column(name = "begin_at", nullable = true, updatable = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginAt;

    /**
    * 被指派人部门
    * 
    */
    @Column(name = "designee_dept_id", nullable = true, updatable = true)
	@Size(max = 255, message = "被指派人部门超长，最多255个字符")
	private String designeeDeptId;

    /**
    * 日程状态
    * 
    */
    @Column(name = "schedule_status", nullable = true, updatable = true)
	private Long scheduleStatus;

    /**
    * 结束时间
    * 
    */
    @Column(name = "end_at", nullable = true, updatable = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endAt;

    /**
    * 日程标题
    * 
    */
    @Column(name = "schedule_title", nullable = true, updatable = true)
	@Size(max = 255, message = "日程标题超长，最多255个字符")
	private String scheduleTitle;

	public Date getReminderTime(){
		return reminderTime;
	}
	public void setReminderTime(Date reminderTime) {
		this.reminderTime = reminderTime;
	}

	public String getScheduleDetails(){
		return scheduleDetails;
	}
	public void setScheduleDetails(String scheduleDetails) {
		this.scheduleDetails = scheduleDetails;
	}

	public String getDesigneeDeptUid(){
		return designeeDeptUid;
	}
	public void setDesigneeDeptUid(String designeeDeptUid) {
		this.designeeDeptUid = designeeDeptUid;
	}

	public String getDesigneeUid(){
		return designeeUid;
	}
	public void setDesigneeUid(String designeeUid) {
		this.designeeUid = designeeUid;
	}

	public Date getBeginAt(){
		return beginAt;
	}
	public void setBeginAt(Date beginAt) {
		this.beginAt = beginAt;
	}

	public String getDesigneeDeptId(){
		return designeeDeptId;
	}
	public void setDesigneeDeptId(String designeeDeptId) {
		this.designeeDeptId = designeeDeptId;
	}

	public Long getScheduleStatus(){
		return scheduleStatus;
	}
	public void setScheduleStatus(Long scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Date getEndAt(){
		return endAt;
	}
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public String getScheduleTitle(){
		return scheduleTitle;
	}
	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

