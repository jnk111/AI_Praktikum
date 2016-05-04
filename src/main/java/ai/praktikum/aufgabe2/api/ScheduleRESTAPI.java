package ai.praktikum.aufgabe2.api;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import ai.praktikum.aufgabe2.models.JSONWeekSchedule;
import ai.praktikum.aufgabe2.models.ScheduleID;
import ai.praktikum.aufgabe2.models.WeekSchedule;
import ai.praktikum.aufgabe2.service.ScheduleService;
import ai.praktikum.aufgabe2.statuscodes.StatusCodes;
import spark.Request;

public class ScheduleRESTAPI {

	private static final ScheduleService SERVICE = new ScheduleService();
	private static final Gson GSON = new Gson();

	
	public ScheduleRESTAPI() {
		initGET();
		initPOST();
		initPUT();
		initDELETE();
		initExceptions();
	}

	private void initGET() {
		get("/plan/:id", (req, res) -> {

			ScheduleID id = ScheduleID.get(req.params(":id"));
			WeekSchedule s = SERVICE.getScheduleWithID(id);
			res.status(StatusCodes.OK);
			return GSON.toJson(s.convert());			
		});

	}

	private void initPOST() {
		post("/plan", (req, res) -> {
			
			checkContentTypeJson(req);
			JSONWeekSchedule json = GSON.fromJson(req.body(), JSONWeekSchedule.class);
			String id = SERVICE.createSchedule(json);
			res.status(StatusCodes.OK);
			res.header("Location", "http://localhost:4567/plan/" + id);
			return StatusCodes.OK + StatusCodes.OK_CREATE_MSG;
			
		});

	}

	private void initPUT() {
		
		put("/plan/:id", (req, res) -> {
			checkContentTypeJson(req);
			ScheduleID id = ScheduleID.get(req.params(":id"));
			WeekSchedule s = SERVICE.getScheduleWithID(id);
			WeekSchedule mod = GSON.fromJson(req.body(), WeekSchedule.class);

			if (mod.isValid()) {
				SERVICE.modifyScheduleContent(s, mod.getContent());
				res.status(StatusCodes.OK);
				return GSON.toJson(s.convert());
			}			
			throw new IllegalStateException();
		});
		
	}

	private void initDELETE() {
		
		delete("/plan/:id", (req, res) -> {
			
			ScheduleID id = ScheduleID.get(req.params(":id"));
			WeekSchedule s = SERVICE.getScheduleWithID(id);
			SERVICE.deleteSchedule(s);
			res.status(StatusCodes.OK);
			return StatusCodes.OK + StatusCodes.OK_DELETED_MSG;
			
		});
		
	}
	
	private void checkContentTypeJson(Request req){
		
		if(!req.contentType().equals("application/json")){
			throw new IllegalStateException();
		}
	}
	
	private void initExceptions() {
		exception(JsonParseException.class, (e, request, response) -> {
	    response.status(StatusCodes.INVALID_INPUT);
	    response.body(StatusCodes.INVALID_INPUT_MSG);
	    e.printStackTrace();
		});
		
		exception(NumberFormatException.class, (e, request, response) -> {
	    response.status(StatusCodes.INVALID_INPUT);
	    response.body(StatusCodes.INVALID_INPUT_MSG);
	    e.printStackTrace();
		});
		
		exception(IllegalArgumentException.class, (e, request, response) -> {
	    response.status(StatusCodes.NOT_FOUND);
	    response.body(StatusCodes.NOT_FOUND_MSG);
	    e.printStackTrace();
		});
		
		exception(IllegalArgumentException.class, (e, request, response) -> {
	    response.status(StatusCodes.NOT_FOUND);
	    response.body(StatusCodes.NOT_FOUND_MSG);
	    e.printStackTrace();
		});
		
		exception(IllegalStateException.class, (e, request, response) -> {
	    response.status(StatusCodes.INVALID_INPUT);
	    response.body(StatusCodes.INVALID_INPUT_MSG);
	    e.printStackTrace();
		});		
	}
}
