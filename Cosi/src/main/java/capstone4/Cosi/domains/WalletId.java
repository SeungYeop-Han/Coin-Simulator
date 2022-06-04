package capstone4.Cosi.domains;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

//복합 키
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class WalletId implements Serializable {

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "coin_id")
    private Long coinId;

    public WalletId() {
    }
}
