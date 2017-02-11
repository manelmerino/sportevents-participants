package sportevents.participants;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonCreator;

@Relation(value = "participant", collectionRelation = "participants")
public class ParticipantResource extends ResourceSupport {
    private final Long _id;
    private final String createdAt;
    
    private final String updatedAt;
    
    private final String taxId;
    
    private final String birthday;
    
    private final String name;
    
    private final String firstSurname;
    
    private final String secondSurname;
    
    private final String gender;
    
    private final String address;
    
    private final String zipCode;
    
    private final String city;
    
    private final String country;
    
    private final String personalPhone;
    
    private final String emergencyPhone;
    
    private final String email;

    private ParticipantResource(Long _id, String createdAt, String updatedAt, String taxId,
            String birthday, String name, String firstSurname, String secondSurname, String gender, String address,
            String zipCode, String city, String country, String personalPhone, String emergencyPhone, String email) {
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.taxId = taxId;
        this.birthday = birthday;
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.gender = gender;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.personalPhone = personalPhone;
        this.emergencyPhone = emergencyPhone;
        this.email = email;
    }

    @JsonCreator
    public static ParticipantResource resourceFactory(Participant participant) {
        return new ParticipantResource(participant.getId(), participant.getCreatedAt().toString(),
                participant.getUpdatedAt().toString(), participant.getTaxId(),
                participant.getBirthday().toString(), participant.getName(), participant.getFirstSurname(),
                participant.getSecondSurname(), participant.getGender(), participant.getAddress(), participant.getZipCode(),
                participant.getCity(), participant.getCountry(), participant.getPersonalPhone(),
                participant.getEmergencyPhone(), participant.getEmail());
    }

    /**
     * @return the _id
     */
    public Long get_id() {
        return _id;
    }

    /**
     * @return the createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return the taxId
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the firstSurname
     */
    public String getFirstSurname() {
        return firstSurname;
    }

    /**
     * @return the secondSurname
     */
    public String getSecondSurname() {
        return secondSurname;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the personalPhone
     */
    public String getPersonalPhone() {
        return personalPhone;
    }

    /**
     * @return the emergencyPhone
     */
    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}