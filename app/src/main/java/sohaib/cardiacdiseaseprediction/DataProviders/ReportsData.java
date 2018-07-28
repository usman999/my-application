package sohaib.cardiacdiseaseprediction.DataProviders;

public class ReportsData {

    int Id;
    String Name;

    public ReportsData(int id, String name) {
        Id = id;
        Name = name;
    }

    public ReportsData() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
