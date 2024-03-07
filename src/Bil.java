import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bil {
    private int bilID;
    private String maerkeModel;
    private String braendstofType;
    private String registreringsnummer;
    private String registreringDATO;
    private int koeretoejKilometer;

    public Bil() {
    }

    public Bil(String maerkeModel, String braendstofType, String registreringsnummer, String registreringDATO, int koeretoejKilometer) {
        this.maerkeModel = maerkeModel;
        this.braendstofType = braendstofType;
        this.registreringsnummer = registreringsnummer;
        this.registreringDATO = registreringDATO;
        this.koeretoejKilometer = koeretoejKilometer;
    }

    // Gettere og settere

    public int getBilID() {
        return bilID;
    }

    public void setBilID(int bilID) {
        this.bilID = bilID;
    }

    public String getMaerkeModel() {
        return maerkeModel;
    }

    public void setMaerkeModel(String maerkeModel) {
        this.maerkeModel = maerkeModel;
    }

    public String getBraendstofType() {
        return braendstofType;
    }

    public void setBraendstofType(String braendstofType) {
        this.braendstofType = braendstofType;
    }

    public String getRegistreringsnummer() {
        return registreringsnummer;
    }

    public void setRegistreringsnummer(String registreringsnummer) {
        this.registreringsnummer = registreringsnummer;
    }

    public String getRegistreringDATO() {
        return registreringDATO;
    }

    public void setRegistreringDATO(String registreringDATO) {
        this.registreringDATO = registreringDATO;
    }

    public int getKoeretoejKilometer() {
        return koeretoejKilometer;
    }

    public void setKoeretoejKilometer(int koeretoejKilometer) {
        this.koeretoejKilometer = koeretoejKilometer;
    }

    // Andre metoder

    public void saveToDatabase(Connection connection) throws SQLException {
        String query = "INSERT INTO Biler (MaerkeModel, BraendstofType, Registreringsnummer, RegistreringDATO, KoeretoejKilometer) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, maerkeModel);
            preparedStatement.setString(2, braendstofType);
            preparedStatement.setString(3, registreringsnummer);
            preparedStatement.setString(4, registreringDATO);
            preparedStatement.setInt(5, koeretoejKilometer);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                bilID = generatedKeys.getInt(1);
            }
        }
    }

    public static List<Bil> getAllBiler(Connection connection) throws SQLException {
        List<Bil> biler = new ArrayList<>();
        String query = "SELECT * FROM Biler";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bil bil = new Bil();
                bil.setBilID(resultSet.getInt("BilID"));
                bil.setMaerkeModel(resultSet.getString("MaerkeModel"));
                bil.setBraendstofType(resultSet.getString("BraendstofType"));
                bil.setRegistreringsnummer(resultSet.getString("Registreringsnummer"));
                bil.setRegistreringDATO(resultSet.getString("RegistreringDATO"));
                bil.setKoeretoejKilometer(resultSet.getInt("KoeretoejKilometer"));
                biler.add(bil);
            }
        }
        return biler;
    }

    @Override
    public String toString() {
        return "Bil{" +
                "bilID=" + bilID +
                ", maerkeModel='" + maerkeModel + '\'' +
                ", braendstofType='" + braendstofType + '\'' +
                ", registreringsnummer='" + registreringsnummer + '\'' +
                ", registreringDATO='" + registreringDATO + '\'' +
                ", koeretoejKilometer=" + koeretoejKilometer +
                '}';
    }
}

