package ai.praktikum.aufgabe2.models;

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
	public int hashCode() {

		return this.id.hashCode() + this.content.hashCode() * 42;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (obj instanceof WeekSchedule) {
			WeekSchedule s = (WeekSchedule) obj;
			
			return (s.id == null && s.content == null 
							&& this.id == null && this.content == null)
								|| (s.id.equals(this.id) && this.content.equals(s.content));
		}
		return false;
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
}
