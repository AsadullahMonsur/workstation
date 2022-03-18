package com.hospital.others;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatientRecord {
	String docId, pId, symptomps, prescribetion;
	LocalDateTime time;

	public PatientRecord(String docId, String pId, String symp, String prescribe) {
		this.docId = docId;
		this.pId = pId;
		this.symptomps = symp;
		this.prescribetion = prescribe;
		this.time = LocalDateTime.now();
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getSymptomps() {
		return symptomps;
	}

	public void setSymptomps(String symptomps) {
		this.symptomps = symptomps;
	}

	public String getPrescribetion() {
		return prescribetion;
	}

	public void setPrescribetion(String prescribetion) {
		this.prescribetion = prescribetion;
	}

	public LocalDateTime getTime() {
		return time;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("\tdd-MM-yyyy\t\tHH:mm:ss");
		String appTime = time.format(formatter);

		return "\n\tDoc Id: " + docId + "\tPatient Id: " + pId + "\n\tSymptomps: " + symptomps + "\n\tPrescribetion: "
				+ prescribetion + "\n\tTime: " + appTime + "\n";
	}
}