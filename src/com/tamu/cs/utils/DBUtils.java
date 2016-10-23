package com.tamu.cs.utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tamu.cs.beans.Event;
import com.tamu.cs.beans.User;
 
public class DBUtils {
 
  public static User findUser(Connection conn, String userName, String password) throws SQLException {
 
      String sql = "Select a.username, a.password, a.uniqueid from USERS a "
              + " where a.username = ? and a.password= ?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, userName);
      pstm.setString(2, password);
      ResultSet rs = pstm.executeQuery();
 
      if (rs.next()) {
          String uniqueId = rs.getString("uniqueid");
          User user = new User();
          user.setUserName(userName);
          user.setPassword(password);
          user.setUniqueId(uniqueId);
          return user;
      }
      return null;
  }
 
  public static User findUser(Connection conn, String userName) throws SQLException {
 
      String sql = "Select a.username, a.Password, a.uniqueid from USERS a " + " where a.username = ? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, userName);
 
      ResultSet rs = pstm.executeQuery();
 
      if (rs.next()) {
          String password = rs.getString("password");
          String uniqueId = rs.getString("uniqueid");
          User user = new User();
          user.setUserName(userName);
          user.setPassword(password);
          user.setUniqueId(uniqueId);
          return user;
      }
      return null;
  }
 
  public static List<Event> queryEvents(Connection conn, String createdBy, Date eventstartdate, Date  eventenddate) throws SQLException {
      String sql = "Select e.eventid, e.eventname, e.eventstartdate, e.eventenddate, e.eventstarttime, e.eventendtime, "
      		+ "e.isrecurring, e.createdon from EVENTS e  where e.createdby =? and date_format(date(eventstartdate),'%y-%m-%d')>=? and"
      		+ " date_format(date(eventstartdate),'%y-%m-%d')<=? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, createdBy);
      pstm.setString(2, eventstartdate.toString());
      pstm.setString(3, eventenddate.toString());
      ResultSet rs = pstm.executeQuery();
      List<Event> list = new ArrayList<Event>();
      while (rs.next()) {
          String eventId = rs.getString("eventid");
          String eventName = rs.getString("eventname");
          Date eventStartDate = rs.getDate("eventstartdate");
          Date eventEndDate = rs.getDate("eventenddate");
          Timestamp eventStartTime = rs.getTimestamp("eventstarttime");
          Timestamp eventEndTime = rs.getTimestamp("eventendtime");
          Date createdOn = rs.getDate("createdon");
          String location = rs.getString("location");
          char isRecurring = rs.getString("isrecurring").charAt(0);
          Event event = new Event();
          event.setEventId(eventId);
          event.setEventName(eventName);
          event.setEventStartDate(eventStartDate);
          event.setEventStartTime(eventStartTime);
          event.setEventEndDate(eventEndDate);
          event.setEventEndTime(eventEndTime);
          event.setIsRecurring(isRecurring);
          event.setLocation(location);
          event.setCreatedOn(createdOn);
          event.setCreatedBy(createdBy);
          list.add(event);
      }
      return list;
  }
 
  public static Event findEvent(Connection conn, String createdBy, String eventId) throws SQLException {
      String sql = "Select e.eventname, e.eventstartdate, e.eventenddate, e.eventstarttime, e.eventendtime, "
        		+ "e.isrecurring, e.location, e.createdon from EVENTS e  where e.createdby =? and e.eventid =?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, createdBy);
      pstm.setString(2, eventId);
 
      ResultSet rs = pstm.executeQuery();
 
      while (rs.next()) {
          String eventName = rs.getString("eventname");
          Date eventStartDate = rs.getDate("eventstartdate");
          Date eventEndDate = rs.getDate("eventenddate");
          Timestamp eventStartTime = rs.getTimestamp("eventstarttime");
          Timestamp eventEndTime = rs.getTimestamp("eventendtime");
          Date createdOn = rs.getDate("createdon");
          char isRecurring = rs.getString("isrecurring").charAt(0);
          String location = rs.getString("location");
          Event event = new Event();
          event.setEventId(eventId);
          event.setEventName(eventName);
          event.setEventStartDate(eventStartDate);
          event.setEventStartTime(eventStartTime);
          event.setEventEndDate(eventEndDate);
          event.setEventEndTime(eventEndTime);
          event.setIsRecurring(isRecurring);
          event.setLocation(location);
          event.setCreatedOn(createdOn);
          event.setCreatedBy(createdBy);          
          return event;
      }
      return null;
  }
 
  public static void updateEvent(Connection conn, Event event) throws SQLException {
      String sql = "Update EVENTS set eventname =?, eventstartdate =?, eventenddate =?, eventstarttime =?, eventendtime =?,"
      		+ "isrecurring =?, location=?  where createdby =? and eventid =? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, event.getEventName());
      pstm.setDate(2, event.getEventStartDate());
      pstm.setDate(3, event.getEventEndDate());
      pstm.setTimestamp(4, event.getEventStartTime());
      pstm.setTimestamp(5, event.getEventEndTime());
      pstm.setString(6, Character.toString(event.getIsRecurring()));
      pstm.setString(7, event.getLocation());
      pstm.setString(8, event.getCreatedBy());
      pstm.setString(9, event.getEventId());
      pstm.executeUpdate();
  }
 
  public static void insertProduct(Connection conn, Event event) throws SQLException {
      String sql = "Insert into Event(eventid, eventname, eventstartdate, eventenddate, eventstarttime, eventendtime,"
      		+ "createdon, createdby,isrecurring) values (?,?,?,?,?,?,?,?,?)";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, event.getEventId());
      pstm.setString(2, event.getEventName());
      pstm.setDate(3, event.getEventStartDate());
      pstm.setDate(4, event.getEventEndDate());
      pstm.setDate(5, event.getCreatedOn());
      pstm.setString(6, event.getCreatedBy());
      pstm.setString(7, Character.toString(event.getIsRecurring()));
      
      pstm.executeUpdate();
  }
 
  public static void deleteEvent(Connection conn, String createdBy, String eventId) throws SQLException {
      String sql = "Delete from EVENTS where createdby= ? and eventid= ?";
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, createdBy);
      pstm.setString(2, eventId);
 
      pstm.executeUpdate();
  }
 
  public static void createUser(Connection conn, User user) throws SQLException{
//	  String sql = "call `rahul.bhagat`.sp_createUser( ?, ?, ?, ?, ?);";
	  String sql = "call `jigna`.sp_createUser( ?, ?, ?, ?, ?);";
	  PreparedStatement pstm = conn.prepareStatement(sql);
	  pstm.setString(1,user.getFirstName());
	  pstm.setString(2,user.getLastName());
	  pstm.setString(3, user.getUserName());
	  pstm.setString(4, user.getPassword());
	  pstm.setString(5, user.getEmailId());
	  pstm.executeUpdate();
  }
  
  public static void createEvent(Connection conn, Event event) throws SQLException{
//	  String sql = "call `rahul.bhagat`.sp_createEvent(?, ?, ?, ?, ?, ?,?, ?);";
	  String sql = "call `jigna`.sp_createEvent(?, ?, ?, ?, ?, ?,?, ?);";
	  PreparedStatement pstm = conn.prepareStatement(sql);
	  pstm.setString(1,event.getEventName());
	  pstm.setDate(2,event.getEventStartDate());
	  pstm.setDate(3,event.getEventEndDate());
	  pstm.setTimestamp(4, event.getEventStartTime());
	  pstm.setTimestamp(5, event.getEventEndTime());
	  pstm.setString(6, event.getCreatedBy());
	  pstm.setString(7, event.getLocation());
	  pstm.setString(8, Character.toString(event.getIsRecurring()));
	  
	  pstm.executeUpdate();
  }
  
  public static List<Event> listEvent(Connection conn, String createdBy) throws SQLException {
      String sql = "select e.eventid, e.eventname, e.eventstartdate, e.eventenddate, e.eventstarttime, e.eventendtime, "
        		+ "e.isrecurring, e.createdon, e.location from EVENTS e  where e.createdby =? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, createdBy);
//      pstm.setString(2, eventId);
 
      ResultSet rs = pstm.executeQuery();
      List<Event> list = new ArrayList<Event>();
      while (rs.next()) {
          String eventName = rs.getString("eventname");
          Date eventStartDate = rs.getDate("eventstartdate");
          Date eventEndDate = rs.getDate("eventenddate");
          Timestamp eventStartTime = rs.getTimestamp("eventstarttime");
          Timestamp eventEndTime = rs.getTimestamp("eventendtime");
          String eventId = rs.getString("eventid");
          Date createdOn = rs.getDate("createdon");
          char isRecurring = rs.getString("isrecurring").charAt(0);
          String location = rs.getString("location");
          Event event = new Event();
          event.setEventId(eventId);
          event.setEventName(eventName);
          event.setEventStartDate(eventStartDate);
          event.setEventStartTime(eventStartTime);
          event.setEventEndDate(eventEndDate);
          event.setEventEndTime(eventEndTime);
          event.setIsRecurring(isRecurring);
          event.setLocation(location);
          event.setCreatedOn(createdOn);
          event.setCreatedBy(createdBy); 
          System.out.println(event.toString());
          list.add(event);
         
      }
      return list;
  }
}