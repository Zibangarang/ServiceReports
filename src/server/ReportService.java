package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import server.Report;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;

import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Path("/servicereports") // path to all methods is /api/servicereports
public class ReportService { 

	static String dbName = "e1300525_servicereports"; // these are global variables used for database connections
	static String dbUserName = "e1300525";
	static String dbPassword = "ZHucAcyD9cKX";
	static String table = "service_report";
	static String url = "jdbc:mysql://mysql.cc.puv.fi:3306/" + dbName;

	@PUT // handles PUT requests
	@Consumes(MediaType.APPLICATION_JSON) // Consumes the submitted json and uses the POJO to generate the Report object
											// that will be submitted to the database.
//if the submitted json has multiple objects, the submission will fail.

	public String insertData(Report report) {

		try {
			Connection conn = null;
			java.sql.PreparedStatement prep = null;

			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://mysql.cc.puv.fi:3306/" + dbName;
			conn = DriverManager.getConnection(url, dbUserName, dbPassword); // the connection to the database is made

			prep = conn.prepareStatement("insert into " + table + " values(?,?,?,?)"); // statement prepared for
																						// execution

			prep.setString(1, report.getPerson()); // values submitted in the json assigned to the values for database
													// submission
			prep.setString(2, report.getCustomer());
			prep.setString(3, report.getEquipment());
			prep.setString(4, report.getDescription());

			prep.executeUpdate(); // the data is added to the database.

			conn.close(); // connection closed after no longer required

			return "Service report was added.";

		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed."; // In case of a database failure, this will be returned.
	} // end of insertData

	@GET
	@Produces(MediaType.APPLICATION_JSON) // GET method produces a json formatted String
	public String listReports() {

		JsonArray reports = new JsonArray(); // JsonArray holds the complete json data
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Gson library used to format the json properly

		try {
			Connection conn = null;

			java.sql.PreparedStatement prep = null;
			ResultSet rs = null;

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			prep = conn.prepareStatement("select * from " + table); // select all from the table

			rs = prep.executeQuery(); // the data read from the database is put into a ResultSet object

			if (rs == null) {
				String prettyJson = gson.toJson(reports); // returns empty json list if ResultSet is empty after
															// execution
				return prettyJson;
			}

			while (rs.next()) { // iterating through the ResultSet
				String person = rs.getString("person"); // the data is read from each column for each row and assigned
														// to their own variables
				String customer = rs.getString("customer");
				String equipment = rs.getString("equipment");
				String description = rs.getString("description");

				JsonObject report = new JsonObject(); // JsonObject to hold the data temporarily in the json format
				report.addProperty("person", person); // The JsonObject is assigned the values read from the database
				report.addProperty("customer", customer);
				report.addProperty("equipment", equipment);
				report.addProperty("description", description);
				reports.add(report); // The complete JsonObject is added to the JsonArray
			}

			conn.close();

			String prettyJson = gson.toJson(reports); // the json array is formatted and returned
			return prettyJson;

		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed.";
	} // end of listReports

}