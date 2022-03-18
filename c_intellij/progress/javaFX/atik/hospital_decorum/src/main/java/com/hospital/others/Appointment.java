package com.hospital.others;
public class Appointment {	
	
	private String doctorId,patientId,status;
	private int serialNO;
	
	public Appointment(String doctorId, String patientId, String status, int serialNO) {
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.status = status;
		this.serialNO = serialNO;
	}
	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSerialNO() {
		return serialNO;
	}

	public void setSerialNO(int serialNO) {
		this.serialNO = serialNO;
	}

	@Override
	public String toString() {
		return "Appointment [doctorId=" + doctorId + ", patientId=" + patientId + ", status=" + status + ", serialNO="
				+ serialNO + "]";
	}	
	
	public void display() {
		System.out.println(this);
	}

		
	}