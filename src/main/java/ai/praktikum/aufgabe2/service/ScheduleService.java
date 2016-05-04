package ai.praktikum.aufgabe2.service;

import java.util.HashMap;
import java.util.Map;

import ai.praktikum.aufgabe2.models.JSONWeekSchedule;
import ai.praktikum.aufgabe2.models.ScheduleID;
import ai.praktikum.aufgabe2.models.WeekSchedule;

public class ScheduleService {

	private Map<ScheduleID, WeekSchedule> schedules;
	
	public ScheduleService(){
		
		setSchedules(new HashMap<>());
	}
	
	public WeekSchedule getScheduleWithID(ScheduleID id) {
		
		WeekSchedule s = schedules.get(id);
		
		if(s != null){
			return s;
		}
		throw new IllegalArgumentException();
	}

	public String createSchedule(JSONWeekSchedule json) {
		
		WeekSchedule schedule = json.convert();
		ScheduleID id = getNextID();
		schedule.setId(id);
		schedules.put(id, schedule);	
		return id.toString();
	}

	public void modifyScheduleContent(WeekSchedule s, String content) {
		
		s.setContent(content);
	}

	public void deleteSchedule(ScheduleID s) {

		if(schedules.remove(s) == null){
			throw new IllegalArgumentException();
		};
	}

	public Map<ScheduleID, WeekSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(Map<ScheduleID, WeekSchedule> schedules) {
		this.schedules = schedules;
	}
	
	private synchronized ScheduleID getNextID(){
		
		return ScheduleID.get("" + (schedules.size() + 1));
		
	}

}
