import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Lejer {
    private int lejerID;
    private String navn;
    private String adresse;
    private int postnummerID;
    private String byNavn;
    private String mobiltelefon;
    private String telefon;
    private String email;
    private String koerekortNummer;
    private String koerekortUdstedelsesdato;

    public Lejer() {
    }

    public Lejer(String navn, String adresse, int postnummerID, String byNavn, String mobiltelefon, String telefon, String email, String koerekortNummer, String koerekortUdstedelsesdato) {
        this.navn = navn;
        this.adresse = adresse;
        this.postnummerID = postnummerID;
        this.byNavn = byNavn;
        this.mobiltelefon = mobiltelefon;
        this.telefon = telefon;
        this.email = email;
        this.koerekortNummer = koerekortNummer;
        this.koerekortUdstedelsesdato = koerekortUdstedelsesdato;
    }

    public int getLejerID() {
        return lejerID;
    }

    public void setLejerID(int lejerID) {
        this.lejerID = lejerID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPostnummerID() {
        return postnummerID;
    }

    public void setPostnummerID(int postnummerID) {
        this.postnummerID = postnummerID;
    }

    public String getByNavn() {
        return byNavn;
    }

    public void setByNavn(String byNavn) {
        this.byNavn = byNavn;
    }

    public String getMobiltelefon() {
        return mobiltelefon;
    }

    public void setMobiltelefon(String mobiltelefon) {
        this.mobiltelefon = mobiltelefon;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKoerekortNummer() {
        return koerekortNummer;
    }

    public void setKoerekortNummer(String koerekortNummer) {
        this.koerekortNummer = koerekortNummer;
    }

    public String getKoerekortUdstedelsesdato() {
        return koerekortUdstedelsesdato;
    }

    public void setKoerekortUdstedelsesdato(String koerekortUdstedelsesdato) {
        this.koerekortUdstedelsesdato = koerekortUdstedelsesdato;
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        String query = "INSERT INTO Lejere (Navn, Adresse, PostnummerID, ByNavn, Mobiltelefon, Telefon, Email, KoerekortNummer, KoerekortUdstedelsesdato) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, navn);
            preparedStatement.setString(2, adresse);
            preparedStatement.setInt(3, postnummerID);
            preparedStatement.setString(4, byNavn);
            preparedStatement.setString(5, mobiltelefon);
            preparedStatement.setString(6, telefon);
            preparedStatement.setString(7, email);
            preparedStatement.setString(8, koerekortNummer);
            preparedStatement.setString(9, koerekortUdstedelsesdato);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                lejerID = generatedKeys.getInt(1);
            }
        }
    }

    public static List<Lejer> getAllLejere(Connection connection) throws SQLException {
        List<Lejer> lejere = new ArrayList<>();
        String query = "SELECT * FROM Lejere";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lejer lejer = new Lejer();
                lejer.setLejerID(resultSet.getInt("LejerID"));
                lejer.setNavn(resultSet.getString("Navn"));
                lejer.setAdresse(resultSet.getString("Adresse"));
                lejer.setPostnummerID(resultSet.getInt("PostnummerID"));
                lejer.setByNavn(resultSet.getString("ByNavn"));
                lejer.setMobiltelefon(resultSet.getString("Mobiltelefon"));
                lejer.setTelefon(resultSet.getString("Telefon"));
                lejer.setEmail(resultSet.getString("Email"));
                lejer.setKoerekortNummer(resultSet.getString("KoerekortNummer"));
                lejer.setKoerekortUdstedelsesdato(resultSet.getString("KoerekortUdstedelsesdato"));
                lejere.add(lejer);
            }
        }
        return lejere;
    }

    @Override
    public String toString() {
        return "Lejer{" +
                "lejerID=" + lejerID +
                ", navn='" + navn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", postnummerID=" + postnummerID +
                ", byNavn='" + byNavn + '\'' +
                ", mobiltelefon='" + mobiltelefon + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                ", koerekortNummer='" + koerekortNummer + '\'' +
                ", koerekortUdstedelsesdato='" + koerekortUdstedelsesdato + '\'' +
                '}';
    }
}

