package capstone4.Cosi.repositories;

import capstone4.Cosi.domains.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CoinRepository extends JpaRepository<Coin, Long> {
}
