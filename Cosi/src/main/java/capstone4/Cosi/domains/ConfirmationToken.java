package capstone4.Cosi.domains;

import capstone4.Cosi.domains.Member;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "confirmation_tokens")
@Entity(name = "ConfirmationToken")
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {
//    속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @NotNull
    @Column(name = "token")
    private String token;

    @NotNull
    @Column(
            name = "created_at",
            columnDefinition = "DATETIME"
    )
    @CreatedDate
    private LocalDateTime createdAt;

    @NotNull
    @Column(
            name = "expired_at",
            columnDefinition = "DATETIME"
    )
    private LocalDateTime expiredAt;

    @Column(
            name = "confirmed_at",
            columnDefinition = "DATETIME"
    )
    private LocalDateTime confirmedAt;

//    FKs
    @NotNull
    @ManyToOne
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "confirmation_tokens_member_id_fk")
    )
    private Member member;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiredAt,
                             Member member) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.member = member;
    }
}
