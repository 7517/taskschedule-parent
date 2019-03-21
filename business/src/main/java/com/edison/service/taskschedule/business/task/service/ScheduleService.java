package com.edison.service.taskschedule.business.task.service;


import com.edison.saas.common.jpa.GenericCrudService;
import com.edison.schedule.business.schedule.dao.ScheduleDao;
import com.edison.schedule.business.schedule.dao.ScheduleSpecification;
import com.edison.schedule.business.schedule.domain.Schedule;
import com.edison.schedule.business.schedule.dto.ScheduleCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("scheduleService")
@Slf4j
public class ScheduleService  extends GenericCrudService<Schedule, Long, ScheduleCondition, ScheduleDao> {

	@Autowired
	private ScheduleRecordService scheduleRecordService;
	@Override
	public Specification<Schedule> getSpecification(ScheduleCondition condition) {
		return new ScheduleSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Schedule.PROPERTY_REMINDER_TIME);
		return sort;
	}

	//添加日程
	public Map addSchedule(Schedule schedule, Long time){
		Map<String,Object> map=new HashMap<>();
		try {
			long time2 = schedule.getBeginAt().getTime();
			Long time1 = time * 60000;
			long t = time2 - time1;
			Timestamp timestamp = new Timestamp(t);
			schedule.setReminderTime(timestamp);
			this.add(schedule);
			map.put("flag",true);
			return map;
		}catch (Exception e){
			map.put("flag",false);
			return map;
		}
	}

	public List<Schedule> findByDate(Date beginAt,String uid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(beginAt);
		Date parse = null;
		try {
			parse = sdf.parse(format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp timestamp1 = new Timestamp(parse.getTime());
		Timestamp timestamp2 = new Timestamp(parse.getTime() + 86400000);
		List<Schedule> list = dao.findByBeginAt(timestamp1, timestamp2,uid);
		return list;
	}
	/*public Schedule addSchedule(Schedule schedule,Long time){
		Long t=time*6000;
		long time1 = schedule.getBeginAt().getTime();
		Long time2=time1-t;
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(time2);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(calendar);
		try {
			Date date = sdf.parse(format);
			schedule.setReminderTime(date);

			Schedule save = dao.save(schedule);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/

		public Schedule findScheduleDetails(Long id){
			Schedule s = dao.findById(id);
			return s;
		}


	public void deleteSchedule(Long id){
		dao.delete(id);
		scheduleRecordService.deleteByScheduleId(id);
	}


	public Schedule updateSchedule(Schedule schedule,Long id,Long time){
		Schedule s = dao.findById(id);
		long time1 = s.getBeginAt().getTime();
		Long t=time*60000;
		long time2=time1-t;
		Timestamp timestamp=new Timestamp(time2);
		s.setReminderTime(timestamp);
		Schedule save = dao.save(s);
		return save;

	}

}