package sportevents.participants;

import java.sql.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParticipantRequest {
    private String taxId;

    private String birthday;

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
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(String birthday) {
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

    public boolean isValid() {
        if ((taxId == null) || taxId.isEmpty()) {
            return false;
        }
        if ((birthday == null) || birthday.isEmpty()) {
            return false;
        }
        if ((name == null) || name.isEmpty()) {
            return false;
        }
        if ((firstSurname == null) || firstSurname.isEmpty()) {
            return false;
        }
        if ((secondSurname == null) || secondSurname.isEmpty()) {
            return false;
        }
        if ((gender == null) || gender.isEmpty()) {
            return false;
        }
        if ((address == null) || address.isEmpty()) {
            return false;
        }
        if ((zipCode == null) || zipCode.isEmpty()) {
            return false;
        }
        if ((city == null) || city.isEmpty()) {
            return false;
        }
        if ((personalPhone == null) || personalPhone.isEmpty()) {
            return false;
        }
        if ((email == null) || email.isEmpty()) {
            return false;
        }

        return true;
    }

    public Participant generateParticipant() {
        Participant participant = new Participant();

        participant.setTaxId(this.getTaxId());
        participant.setBirthday(Date.valueOf(this.getBirthday()));
        participant.setName(this.getName());
        participant.setFirstSurname(this.getFirstSurname());
        participant.setSecondSurname(this.getSecondSurname());
        participant.setGender(this.getGender());
        participant.setAddress(this.getAddress());
        participant.setZipCode(this.getZipCode());
        participant.setCity(this.getCity());
        participant.setCountry(this.getCountry());
        participant.setPersonalPhone(this.getPersonalPhone());
        participant.setEmergencyPhone(this.getEmergencyPhone());
        participant.setEmail(this.getEmail());

        return participant;
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

}
