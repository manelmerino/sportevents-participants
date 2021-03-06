package sportevents.participants;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//enum Genere {
//    Home, Dona
//}

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String taxId;

    private Date birthday;

    private String name;

    private String firstSurname;

    private String secondSurname;

    private String gender;

    private String address;

    private String zipCode;

    private String city;

    private String country;

    private String personalPhone;

    private String emergencyPhone;

    private String email;

    private boolean active;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the createdAt
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     *            the createdAt to set
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     *            the updatedAt to set
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the taxId
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * @param taxId
     *            the taxId to set
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the firstSurname
     */
    public String getFirstSurname() {
        return firstSurname;
    }

    /**
     * @param firstSurname
     *            the firstSurname to set
     */
    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    /**
     * @return the secondSurname
     */
    public String getSecondSurname() {
        return secondSurname;
    }

    /**
     * @param secondSurname
     *            the secondSurname to set
     */
    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode
     *            the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the personalPhone
     */
    public String getPersonalPhone() {
        return personalPhone;
    }

    /**
     * @param personalPhone
     *            the personalPhone to set
     */
    public void setPersonalPhone(String personalPhone) {
        this.personalPhone = personalPhone;
    }

    /**
     * @return the emergencyPhone
     */
    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    /**
     * @param emergencyPhone
     *            the emergencyPhone to set
     */
    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active
     *            the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String participantRequestAsJson = mapper.writeValueAsString(this);

            return participantRequestAsJson;
        } catch (JsonProcessingException jpe) {
            return jpe.getMessage();
        }
    }

    public Participant fillForInsertion() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        this.setCreatedAt(currentTimestamp);
        this.setUpdatedAt(currentTimestamp);
        this.setActive(true);

        return this;
    }

    public Participant fillForUpdate(Participant participant) {
        this.setCreatedAt(participant.getCreatedAt());
        this.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        this.setActive(participant.isActive());
        this.setId(participant.getId());

        return this;
    }
}
