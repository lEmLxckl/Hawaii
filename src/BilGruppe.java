import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BilGruppe {
    private int gruppeID;
    private String gruppeNavn;

    public BilGruppe() {
    }

    public BilGruppe(String gruppeNavn) {
        this.gruppeNavn = gruppeNavn;
    }

    public int getGruppeID() {
        return gruppeID;
    }

    public void setGruppeID(int gruppeID) {
        this.gruppeID = gruppeID;
    }

    public String getGruppeNavn() {
        return gruppeNavn;
    }

    public void setGruppeNavn(String gruppeNavn) {
        this.gruppeNavn = gruppeNavn;
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        String query = "INSERT INTO BilGrupper (GruppeNavn) VALUES (?) ON DUPLICATE KEY UPDATE GruppeNavn=GruppeNavn";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, gruppeNavn);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                gruppeID = generatedKeys.getInt(1);
            }
        }
    }

    public static List<BilGruppe> getAllBilGrupper(Connection connection) throws SQLException {
        List<BilGruppe> bilGrupper = new ArrayList<>();
        String query = "SELECT * FROM BilGrupper";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BilGruppe bilGruppe = new BilGruppe();
                bilGruppe.setGruppeID(resultSet.getInt("GruppeID"));
                bilGruppe.setGruppeNavn(resultSet.getString("GruppeNavn"));
                bilGrupper.add(bilGruppe);
            }
        }
        return bilGrupper;
    }

    @Override
    public String toString() {
        return "BilGruppe{" +
                "gruppeID=" + gruppeID +
                ", gruppeNavn='" + gruppeNavn + '\'' +
                '}';
    }
}


