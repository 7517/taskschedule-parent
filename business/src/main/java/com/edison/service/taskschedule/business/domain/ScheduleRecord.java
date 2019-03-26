package com.edison.service.taskschedule.business.domain;

import com.edison.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 * 日程记录
 * @author icode
 */
@Entity()
@Table(name = "schedule_schedule_record")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class ScheduleRecord extends BaseEntity<Long>{

	public static final String PROPERTY_SCHEDULE_ID = "scheduleId";
	public static final String PROPERTY_SCHEDULE_RECORD_TEXT = "scheduleRecordText";
	public static final String PROPERTY_SCHEDULE_RECORD_VOICE = "scheduleRecordVoice";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 日程主键id
    * 
    */
    @Column(name = "schedule_id", nullable = true, updatable = true)
	private Long scheduleId;

    /**
    * 日程文本信息
    * 
    */
    @Column(name = "schedule_record_text", nullable = true, updatable = true)
	@Size(max = 255, message = "日程文本信息超长，最多255个字符")
	private String scheduleRecordText;

    /**
    * 日程语音地址
    * 
    */
    @Column(name = "schedule_record_voice", nullable = true, updatable = true)
	@Size(max = 255, message = "日程语音地址超长，最多255个字符")
	private String scheduleRecordVoice;

	public Long getScheduleId(){
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getScheduleRecordText(){
		return scheduleRecordText;
	}
	public void setScheduleRecordText(String scheduleRecordText) {
		this.scheduleRecordText = scheduleRecordText;
	}

	public String getScheduleRecordVoice(){
		return scheduleRecordVoice;
	}
	public void setScheduleRecordVoice(String scheduleRecordVoice) {
		this.scheduleRecordVoice = scheduleRecordVoice;
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

