package sportevents.participants;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/participant")
public class ParticipantsController {
    private ParticipantsRepository participantsRepository;

    @Autowired
    public ParticipantsController(ParticipantsRepository participantsRepository) {
        this.participantsRepository = participantsRepository;
    }

     @RequestMapping(path="/", method = RequestMethod.GET)
     public HttpEntity<List<ParticipantResource>> readParticipants() {
         List<Participant> participants = participantsRepository.findAll();
         if (participants.isEmpty()) {
             return new ResponseEntity<List<ParticipantResource>>(HttpStatus.NOT_FOUND);
         } else {
             List<ParticipantResource> participantResources = new ArrayList<ParticipantResource>();
             for (Participant participant : participants) {
                 ParticipantResource participantResource = ParticipantResource.resourceFactory(participant);
                 participantResource.add(linkTo(methodOn(ParticipantsController.class).readParticipant(participant.getId())).withSelfRel());
                 participantResources.add(participantResource);
             }
//             participantResources.add(linkTo(methodOn(ParticipantsController.class).readParticipants()).withSelfRel());
             return new ResponseEntity<List<ParticipantResource>>(participantResources, HttpStatus.OK);
         }
     }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public HttpEntity<ParticipantResource> readParticipant(@PathVariable("id") Long id) {
        if (participantsRepository.exists(id)) {
            Participant participant = participantsRepository.getOne(id);

            ParticipantResource participantResource = ParticipantResource.resourceFactory(participant);
            participantResource.add(linkTo(methodOn(ParticipantsController.class).readParticipant(id)).withSelfRel());

            return new ResponseEntity<ParticipantResource>(participantResource, HttpStatus.OK);
        } else {
            return new ResponseEntity<ParticipantResource>(HttpStatus.NOT_FOUND);
        }
    }

    // @RequestMapping(value="/", method = RequestMethod.POST)
    // public String createParticipant(Participant participant) {
    // participantsRepository.save(participant);
    //
    // return "redirect:/participant/";
    // }
    //
    // @RequestMapping(value="/{participant}", method = RequestMethod.GET)
    // public String readParticipant(@PathVariable("participant") Long id, Model
    // model) {
    // Participant participant = participantsRepository.getOne(id);
    // if (participant != null) {
    // model.addAttribute(participant);
    // }
    //
    // return "participant";
    // }
}
