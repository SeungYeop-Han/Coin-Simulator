package capstone4.Cosi.repositories;

import capstone4.Cosi.domains.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.enabled = TRUE WHERE m.email = :email")
    public int enableMember(@Param("email") String email);

    @Query("SELECT m FROM Member m WHERE m.email = :email")
    public Optional<Member> findByEmail(String email);

    @Query("SELECT DISTINCT m FROM Member m LEFT JOIN fetch m.wallets")
    public List<Member> findMemberByIdWithWallets(@Param("id") Long id);

    @Query("SELECT m FROM Member m WHERE m.email = :email")
    public Optional<Member> findMemberByEmail(@Param("email")String email);

}
