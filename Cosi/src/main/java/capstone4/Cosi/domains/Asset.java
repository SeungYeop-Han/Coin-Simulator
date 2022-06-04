package capstone4.Cosi.domains;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Table(name = "assets")
@Entity(name = "Asset")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Asset {
//    속성
    @Id
    @Column(
            name = "member_id",
            updatable = false
    )
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "assets_member_id_fk")
    )
    private Member member;

    @Column(
            name = "seed",
            columnDefinition = "DECIMAL(16, 4)"
    )
    @NotNull
    private Double seed;
    
//    생성자

    public Asset(Member member, Double seed) {
        this.id = member.getId();
        this.member = member;
        this.seed = seed;
    }


//    사용자 메소드 정의
    
//    메소드 재정의
}
