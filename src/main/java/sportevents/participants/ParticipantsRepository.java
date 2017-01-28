package sportevents.participants;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantsRepository extends JpaRepository<Participant, Long>{
    Participant findByNif(String nif);
}
