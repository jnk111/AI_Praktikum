package ai.praktikum.aufgabe2.models;

import java.util.Objects;

public class WeekSchedule implements Validable, Convertable<JSONWeekSchedule>{

	private ScheduleID id;
	private String content;

	public WeekSchedule() {

		this(null, null);
	}
	
	public WeekSchedule(String content){
		
		this(null, content);
	}

	public WeekSchedule(ScheduleID id, String content) {

		this.id = id;
		this.content = content;

	}

	public ScheduleID getId() {
		return id;
	}

	public void setId(ScheduleID id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Schedule -> ID: " + this.id.toString() + "Content: " + this.content;
	}

	@Override
	public boolean isValid() {
		
		return this.content != null && !this.content.isEmpty();
	}

	@Override
	public JSONWeekSchedule convert() {
		
		return new JSONWeekSchedule(this.id.getId(), this.content);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WeekSchedule that = (WeekSchedule) o;
		return Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
