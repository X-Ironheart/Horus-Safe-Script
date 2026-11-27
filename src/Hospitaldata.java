import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hospitaldata {


public static final String DRUGS_FILES = "../drugs_db.csv";
// ده اسم الملف البضان بتاع الداتا بتاع ام الدوا 
public static final String PATIENTS_FILES = "../patients_log.csv";
// ده اسم الملف البضان بتاع الداتا بتاع ام المرضى  

public static int drugs_count = 0;
public static int patients_count = 0;
static final int MAX = 100000;

public static String [] drug_name = new String [MAX];
public static String [] tdisease = new String [MAX];
public static String [] cont = new String [MAX];
public static double [] drug_price = new double [MAX];
public static String [] patientlogs = new String [MAX];


public static void load_drugs_file() {
    File drugs_file = new File(DRUGS_FILES);
    try (Scanner fileScanner = new Scanner(drugs_file)) {
        while (fileScanner.hasNextLine()&& drugs_count < MAX){
            String line = fileScanner.nextLine();
            if (line == null || line.trim().isEmpty()|| line.startsWith("DrugName")) {continue;}
            String [] drug_details = line.split(";"); // تقسيم ال csv نحول الدوا ل Array 
            if (drug_details.length == 4){
                drug_name[drugs_count] = drug_details[0];
                tdisease[drugs_count] = drug_details[1];
                cont[drugs_count] = drug_details[2];
                drug_price[drugs_count] = Double.parseDouble(drug_details[3]);
                drugs_count++;
            }
        }
        // read from fileScanner انا طلع عين امي علشان افهم في ايرور ليه .Try , catch..-_-
    } catch (FileNotFoundException e) {
        System.out.println("ُError loading drugs file [file not found]: " + DRUGS_FILES);
    }
}

//ده هيبقا زي الميثود اللي فوق بس بكرا بقا ولا حاجه <<
public static void load_patientlogs_file() {
File patient_file = new File(PATIENTS_FILES);
    try (Scanner fileScanner = new Scanner(patient_file)) {
        while (fileScanner.hasNextLine() && patients_count < MAX) { 
            String line = fileScanner.nextLine();
            if (line == null || line.trim().isEmpty()){continue;}
            String firstLine = fileScanner.nextLine();
            if (!firstLine.startsWith("PatientName;")) {
                patientlogs[patients_count] = firstLine;
                patients_count++;
            }
            while (fileScanner.hasNextLine() && patients_count < MAX) {
            // String line = fileScanner.nextLine();
            if (!line.trim().isEmpty()) {
                patientlogs[patients_count] = line.trim();
            patients_count++;
                }
        }
        // read from fileScanner انا طلع عين امي علشان افهم في ايرور ليه ...-_-
    }
} catch (FileNotFoundException e) {
    System.out.println("ُError loading patient logs file [file not found]: " + PATIENTS_FILES);
}
}
}
