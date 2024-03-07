import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Lejekontrakt {
    private int kontraktID;
    private int lejerID;
    private int bilID;
    private String fraDatoTid;
    private String tilDatoTid;
    private int maxKilometer;
    private int startKilometer;

    public Lejekontrakt() {
    }

    public Lejekontrakt(int lejerID, int bilID, String fraDatoTid, String tilDatoTid, int maxKilometer, int startKilometer) {
        this.lejerID = lejerID;
        this.bilID = bilID;
        this.fraDatoTid = fraDatoTid;
        this.tilDatoTid = tilDatoTid;
        this.maxKilometer = maxKilometer;
        this.startKilometer = startKilometer;
    }

    public int getKontraktID() {
        return kontraktID;
    }

    public void setKontraktID(int kontraktID) {
        this.kontraktID = kontraktID;
    }

    public int getLejerID() {
        return lejerID;
    }

    public void setLejerID(int lejerID) {
        this.lejerID = lejerID;
    }

    public int getBilID() {
        return bilID;
    }

    public void setBilID(int bilID) {
        this.bilID = bilID;
    }

    public String getFraDatoTid() {
        return fraDatoTid;
    }

    public void setFraDatoTid(String fraDatoTid) {
        this.fraDatoTid = fraDatoTid;
    }

    public String getTilDatoTid() {
        return tilDatoTid;
    }

    public void setTilDatoTid(String tilDatoTid) {
        this.tilDatoTid = tilDatoTid;
    }

    public int getMaxKilometer() {
        return maxKilometer;
    }

    public void setMaxKilometer(int maxKilometer) {
        this.maxKilometer = maxKilometer;
    }

    public int getStartKilometer() {
        return startKilometer;
    }

    public void setStartKilometer(int startKilometer) {
        this.startKilometer = startKilometer;
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        String query = "INSERT INTO Lejekontrakter (LejerID, BilID, FraDatoTid, TilDatoTid, MaxKilometer, StartKilometer) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, lejerID);
            preparedStatement.setInt(2, bilID);
            preparedStatement.setString(3, fraDatoTid);
            preparedStatement.setString(4, tilDatoTid);
            preparedStatement.setInt(5, maxKilometer);
            preparedStatement.setInt(6, startKilometer);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                kontraktID = generatedKeys.getInt(1);
            }
        }
    }

    public static List<Lejekontrakt> getAllLejekontrakter(Connection connection) throws SQLException {
        List<Lejekontrakt> lejekontrakter = new ArrayList<>();
        String query = "SELECT * FROM Lejekontrakter";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lejekontrakt lejekontrakt = new Lejekontrakt();
                lejekontrakt.setKontraktID(resultSet.getInt("KontraktID"));
                lejekontrakt.setLejerID(resultSet.getInt("LejerID"));
                lejekontrakt.setBilID(resultSet.getInt("BilID"));
                lejekontrakt.setFraDatoTid(resultSet.getString("FraDatoTid"));
                lejekontrakt.setTilDatoTid(resultSet.getString("TilDatoTid"));
                lejekontrakt.setMaxKilometer(resultSet.getInt("MaxKilometer"));
                lejekontrakt.setStartKilometer(resultSet.getInt("StartKilometer"));
                lejekontrakter.add(lejekontrakt);
            }
        }
        return lejekontrakter;
    }

    @Override
    public String toString() {
        return "Lejekontrakt{" +
                "kontraktID=" + kontraktID +
                ", lejerID=" + lejerID +
                ", bilID=" + bilID +
                ", fraDatoTid='" + fraDatoTid + '\'' +
                ", tilDatoTid='" + tilDatoTid + '\'' +
                ", maxKilometer=" + maxKilometer +
                ", startKilometer=" + startKilometer +
                '}';
    }
}

