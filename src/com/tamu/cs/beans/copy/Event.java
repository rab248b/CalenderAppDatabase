package com.tamu.cs.beans.copy;

import java.sql.Date;
import java.sql.Timestamp;

public class Event {
	public static final char RECURRING ='A';
	public static final char NOT_RECURRING = 'S';

	private String eventId;
	private String eventName;
	private Date eventStartDate;
	private Date eventEndDate;
	private Timestamp eventStartTime;
	private Timestamp eventEndTime;
	private Date createdOn;
	private String createdBy;
	private char isRecurring;
	
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Date getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public Date getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	public Timestamp getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(Timestamp eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	public Timestamp getEventEndTime() {
		return eventEndTime;
	}
	public void setEventEndTime(Timestamp eventEndTime) {
		this.eventEndTime = eventEndTime;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		return "Events [eventId=" + eventId + ", eventName=" + eventName + ", eventStartDate=" + eventStartDate
				+ ", eventEndDate=" + eventEndDate + ", eventStartTime=" + eventStartTime + ", eventEndTime="
				+ eventEndTime + ", createdOn=" + createdOn + ", createdBy=" + createdBy + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((eventEndDate == null) ? 0 : eventEndDate.hashCode());
		result = prime * result + ((eventEndTime == null) ? 0 : eventEndTime.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ((eventStartDate == null) ? 0 : eventStartDate.hashCode());
		result = prime * result + ((eventStartTime == null) ? 0 : eventStartTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (eventEndDate == null) {
			if (other.eventEndDate != null)
				return false;
		} else if (!eventEndDate.equals(other.eventEndDate))
			return false;
		if (eventEndTime == null) {
			if (other.eventEndTime != null)
				return false;
		} else if (!eventEndTime.equals(other.eventEndTime))
			return false;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (eventStartDate == null) {
			if (other.eventStartDate != null)
				return false;
		} else if (!eventStartDate.equals(other.eventStartDate))
			return false;
		if (eventStartTime == null) {
			if (other.eventStartTime != null)
				return false;
		} else if (!eventStartTime.equals(other.eventStartTime))
			return false;
		return true;
	}
	public char getIsRecurring() {
		return isRecurring;
	}
	public void setIsRecurring(char isRecurring) {
		this.isRecurring = isRecurring;
	}
	
	
	
}
