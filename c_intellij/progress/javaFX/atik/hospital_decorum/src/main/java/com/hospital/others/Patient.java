package com.hospital.others;

public class Patient extends Personnel {

	public Patient(String name, String id, String gender, int age) {
		super(name, id, gender, age);
	}
	public void addAppointment(String docId) {
		    int serial = (int) getAppointments().size();
			Appointment appt = new Appointment(docId, super.getId(), "Scheduled", serial+=1);
			super.addAppointment(appt);
	}
	public Appointment getAppointment(String doctorId) {
		return super.getAppointment(doctorId, getId());
	}

	// for checking if there is any appointment or not
	public boolean hasAppointment(String doctorId) {
		Appointment a = getAppointment(doctorId);
		return a != null;
	}

}