package sohaib.cardiacdiseaseprediction.DataProviders;

public class ViewDoctorDataProvider {

    String doctorName;
    String doctorSpeciality;
    String doctorGender;
    String doctorResidence;
    String doctorPhone;
    String doctorEmail;

    public ViewDoctorDataProvider() {
    }

    public ViewDoctorDataProvider(String doctorName, String doctorSpeciality, String doctorGender, String doctorResidence, String doctorPhone, String doctorEmail) {
        this.doctorName = doctorName;
        this.doctorSpeciality = doctorSpeciality;
        this.doctorGender = doctorGender;
        this.doctorResidence = doctorResidence;
        this.doctorPhone = doctorPhone;
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public String getDoctorGender() {
        return doctorGender;
    }

    public void setDoctorGender(String doctorGender) {
        this.doctorGender = doctorGender;
    }

    public String getDoctorResidence() {
        return doctorResidence;
    }

    public void setDoctorResidence(String doctorResidence) {
        this.doctorResidence = doctorResidence;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }
}
