package mobile.homeless;

/**
 * Created by Mateo on 04/03/2016.
 */
public class Person {

    private String PersonneId;
    private String Titre;
    private String Description;
    private Double Latitude;
    private Double Longitude;


    public String getPersonneId() {
        return PersonneId;
    }

    public void setPersonneId(String personneId) {
        PersonneId = personneId;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }
}
