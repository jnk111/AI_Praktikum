package ai.praktikum.aufgabe2.models;

public class ScheduleID {
	
	private String id;
	
	private ScheduleID(String id){
		this.id = "" + Integer.parseInt(id);
	}
	
	public String getId() {
		return id;
	}	
	
	public static ScheduleID get(String id){
		return new ScheduleID(id);
	}

	@Override
	public String toString() {
		
		return this.id;
	}

	@Override
	public int hashCode() {
		
		return this.id.hashCode() * 42;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj == null){
			return false;
		}
		
		if(obj instanceof ScheduleID){
			
			ScheduleID id = (ScheduleID) obj;
			return this.id.equals(id.getId());
		}
		
		return false;
	}	
}
