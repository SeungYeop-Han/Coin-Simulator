package capstone4.Cosi.repositories;

import capstone4.Cosi.domains.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    public Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmedAt = :confirmedAt WHERE c.token = :token")
    public int updateConfirmedAt(@Param("token") String token, @Param("confirmedAt") LocalDateTime confirmedAt);

    @Transactional
    @Modifying
    @Query("DELETE FROM ConfirmationToken c WHERE c.member.id = :id")
    public int deleteConfirmationTokensByMemberId(@Param("id") Long id);
}
