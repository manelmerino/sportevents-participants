package sportevents.participants;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ExtractableResponse;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

import java.io.IOException;
import java.sql.Date;
import static java.util.Arrays.asList;
import java.util.List;

import org.apache.http.HttpStatus;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasItems;

import org.junit.Before;
import org.junit.Test;

public class ParticipantControllerTest extends AbstractControllerTest {
    private List<Participant> participantsIntoDb;

    private Participant participantToAddIntoDb;

    private final String NON_EXISTENT_PARTICIPANT_ID = "1234567890";

    @Before
    public void setupTestDatabase() {
        participantRepository.deleteAll();

        participantsIntoDb = asList(participantRepository.save(this
                .generateParticipant("00000016Q", "1980-01-01", "Christian", "Vázquez", "Guerero", "Home",
                        "Guilleries 22", "08401", "Granollers", null, "666777888", null, "jmmerino@larocaenbike.com")
                .fillForInsertion()),
                participantRepository.save(this.generateParticipant("00270257F", "1986-11-24", "Montse", "Menéndez",
                        "Almirall", "Dona", "Industria 25 Baixos A", "08918", "Badalona", null, "933894747",
                        "645453661", "manel.biker@gmail.com").fillForInsertion()));

        participantToAddIntoDb = this.generateParticipant("38422254H", "1958-08-04", "Louis", "Geraud", "De La Maza",
                "Home", "Once de Septiembre 66,2º 3ª", "08840", "Viladecans", null, "638755345", null,
                "jmmerino@larocaenbike.com");
    }

    @Test
    public void readParticipantsTest() {
        when().get("/participant").then().statusCode(HttpStatus.SC_OK).and().contentType(ContentType.JSON).and()
                .body("_embedded.participants", hasSize(2)).and().body("_embedded.participants.name",
                        hasItems(participantsIntoDb.stream().map(Participant::getName).toArray()));
    }

    @Test
    public void readParticipantTest() {
        final List<Participant> participantsDb = participantRepository.findAll();
        final Participant participantToRead = participantsDb.get(0);

        // final ExtractableResponse<Response> extractableResponse =
        // given().contentType(ContentType.JSON).when()
        // .get("/participant/" +
        // participantToRead.getId()).then().assertThat().statusCode(200).and().extract();
        final ExtractableResponse<Response> extractableResponse = when()
                .get("/participant/" + participantToRead.getId()).then().assertThat().statusCode(HttpStatus.SC_OK).and()
                .extract();

        final String contentType = extractableResponse.header("Content-Type");
        assertEquals(contentType.split(";")[0], "application/hal+json");

        String responseBodyString = extractableResponse.body().asString();

        assertThat(responseBodyString, allOf(containsString("00000016Q"), containsString("Guilleries 22"),
                containsString("jmmerino@larocaenbike.com")));

        when().get("/participant/" + NON_EXISTENT_PARTICIPANT_ID).then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void createParticipantTest() {
        ParticipantRequest participantRequest = generateParticipantRequest(participantToAddIntoDb);

        // create a valid participant
        final ExtractableResponse<Response> extractableResponse = given().contentType(ContentType.JSON)
                .body(participantRequest).when().post("/participant").then().statusCode(HttpStatus.SC_OK).and()
                .extract();

        String responseBodyString = extractableResponse.body().asString();
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode jsonNode = mapper.readTree(responseBodyString);
            assertEquals(participantToAddIntoDb.getTaxId(), jsonNode.get("taxId").asText());
            assertEquals(participantToAddIntoDb.getName(), jsonNode.get("name").asText());
            assertEquals(participantToAddIntoDb.getFirstSurname(), jsonNode.get("firstSurname").asText());

        } catch (IOException ioe) {
            assertFalse("response result is not a valid JSON", true);
        }
        // participant creation returns the new participant as JSON string
        assertThat(responseBodyString,
                allOf(containsString(participantToAddIntoDb.getTaxId()),
                        containsString(participantToAddIntoDb.getAddress()),
                        containsString(participantToAddIntoDb.getEmail())));

        // create an already existing participant
        given().contentType(ContentType.JSON).body(participantRequest).when().post("/participant").then()
                .statusCode(HttpStatus.SC_CONFLICT);

        // create an invalid participant
        participantRequest.setBirthday(null);
        given().contentType(ContentType.JSON).body(participantRequest).when().post("/participant").then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    private Participant generateParticipant(String taxId, String birthday, String name, String firstSurname,
            String secondSurname, String gender, String address, String zipCode, String city, String country,
            String personalPhone, String emergencyPhone, String email) {

        Participant participant = new Participant();
        participant.setTaxId(taxId);
        participant.setBirthday(Date.valueOf(birthday));
        participant.setName(name);
        participant.setFirstSurname(firstSurname);
        participant.setSecondSurname(secondSurname);
        participant.setGender(gender);
        participant.setAddress(address);
        participant.setZipCode(zipCode);
        participant.setCity(city);
        participant.setCountry(country);
        participant.setPersonalPhone(personalPhone);
        participant.setEmergencyPhone(emergencyPhone);
        participant.setEmail(email);

        return participant;
    }

    private ParticipantRequest generateParticipantRequest(Participant participant) {
        ParticipantRequest participantRequest = new ParticipantRequest();

        participantRequest.setTaxId(participant.getTaxId());
        participantRequest.setBirthday(participant.getBirthday().toString());
        participantRequest.setName(participant.getName());
        participantRequest.setFirstSurname(participant.getFirstSurname());
        participantRequest.setSecondSurname(participant.getSecondSurname());
        participantRequest.setGender(participant.getGender());
        participantRequest.setAddress(participant.getAddress());
        participantRequest.setZipCode(participant.getZipCode());
        participantRequest.setCity(participant.getCity());
        participantRequest.setCountry(participant.getCountry());
        participantRequest.setPersonalPhone(participant.getPersonalPhone());
        participantRequest.setEmergencyPhone(participant.getEmergencyPhone());
        participantRequest.setEmail(participant.getEmail());

        return participantRequest;
    }

}
