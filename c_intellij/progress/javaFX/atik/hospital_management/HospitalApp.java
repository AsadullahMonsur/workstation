package LAB8_17101157;

import java.util.Scanner;

public class HospitalApp {
	
	public static Hospital h = new Hospital("Central Hospital");
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean endApp = false;
		while(!endApp) {
			System.out.println("Enter \"a\" for admin, \"d\" for doctor, \"p\" for patient and \"e\" to end.");
			String s = sc.next();
			char ch = s.charAt(0);
			sc.nextLine();
			if(ch == 'e') {
				endApp = true;
			}
			else if(ch == 'a') {
				boolean end = false;
				while(!end) {
					System.out.println(	"Enter 1 to view doctor's list\n" +
										"Enter 2 to view a patient's record\n" +
										"Enter 3 to view patient records of a doctor\n" +
										"Enter 4 to view patient's list\n" +
										"Enter 5 to add new doctor\n" + 
										"Enter 6 to add new patient\n" +
										"Enter 0 to exit admin menu");
					int option = sc.nextInt();
					sc.nextLine();
					switch(option) {
						case 1:
							System.out.println(h.getDoctors());
							break;
						case 2:
							System.out.println("Enter patient ID");
							String patientId = sc.next();
							Patient p = h.findPatient(patientId);
							System.out.println(p.getPatientRecords());
							break;
						case 3:
							System.out.println("Enter doctor ID");
							String doctorId = sc.next();
							Doctor d = h.findDoctor(doctorId);
							System.out.println(d.getPatientRecords());
							break;
						case 4:
							System.out.println(h.getPatients());
							break;
						case 5:
							System.out.println("Enter name:");
							String name = sc.nextLine();
							System.out.println("Enter id:");
							String id = sc.nextLine();
							System.out.println("Enter gender:");
							String gender = sc.nextLine();
							System.out.println("Enter age:");
							int age = sc.nextInt();
							sc.nextLine();
							System.out.println("Enter specialty:");
							String specialty = sc.nextLine();
							h.addDoctor(name, id, gender, age, specialty);
							break;
						case 6:
							System.out.println("Enter name:");
							name = sc.nextLine();
							System.out.println("Enter id:");
							id = sc.nextLine();
							System.out.println("Enter gender:");
							gender = sc.nextLine();
							System.out.println("Enter age:");
							age = sc.nextInt();
							h.addPatient(name, id, gender, age);
							break;
						case 0:
							end = true;
							break;
					}
				}
			}
			else if(ch == 'd') {
				boolean end = false;
				while(!end) {
					System.out.println(	"Enter 1 to view doctor's list\n" +
										"Enter 2 to visit doctor\n" +
										"Enter 3 to view your patient's record\n" +
										"Enter 0 to exit doctor menu");
					int option = sc.nextInt();
					if(option == 0) {
						break;
					}
					switch(option) {
						case 1:
							System.out.println(h.getDoctors());
							break;
						case 2:
							sc.nextLine();
							System.out.println("Enter doctor ID:");
							String doctorId = sc.nextLine();
							System.out.println("Enter patient ID:");
							String patientId = sc.nextLine();
							System.out.println("Enter symptoms:");
							String symptoms = sc.nextLine();
							System.out.println("Enter prescription:");
							String prescription = sc.nextLine();
							h.meetDoctor(doctorId, patientId, symptoms, prescription);
							break;
						case 3:
							System.out.println("Enter doctor ID");
							doctorId = sc.next();
							Doctor d = h.findDoctor(doctorId);
							System.out.println(d.getPatientRecords());
							break;
						case 0:
							end = true;
							break;
					}
				}
			}
			else if(ch == 'p') {
				boolean end = false;
				while(!end) {
					System.out.println(	"Enter 1 to view doctor's list\n" +
										"Enter 2 to view your records\n" +
										"Enter 0 to exit patient menu");
					int option = sc.nextInt();
					sc.nextLine();
					switch(option) {
						case 1:
							System.out.println(h.getDoctors());
							break;
						case 2:
							System.out.println("Enter your patient ID:");
							String patientId = sc.nextLine();
							Patient p = h.findPatient(patientId);
							System.out.println(p.getPatientRecords());
							break;
						case 0:
							end = true;
							break;
					}
				}
			}
		}
        sc.close();
	}

}

