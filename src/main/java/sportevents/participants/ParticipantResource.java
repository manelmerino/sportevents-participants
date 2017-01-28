package sportevents.participants;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ParticipantResource extends ResourceSupport {
    private final Long ident;
    private final String nif;
    private final String nom;

    // public ParticipantResource(@JsonProperty("ident") Long ident,
    // @JsonProperty("nif") String nif, @JsonProperty("nom") String nom) {
    // this.ident = ident;
    // this.nif = nif;
    // this.nom = nom;
    // }

    public ParticipantResource(Long ident, String nif, String nom) {
        this.ident = ident;
        this.nif = nif;
        this.nom = nom;
    }

    @JsonCreator
    public static ParticipantResource resourceFactory(Participant participant) {
        return new ParticipantResource(participant.getId(), participant.getNif(), participant.getNom());
    }

    public Long getIdent() {
        return ident;
    }

    public String getNif() {
        return nif;
    }

    public String getNom() {
        return nom;
    }
}