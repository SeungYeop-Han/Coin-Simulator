package capstone4.Cosi.domains;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Table(
        name = "coins",     //테이블 이름
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "coins_code_uk",       //제약조건 이름
                        columnNames = "code"           //컬럼 이름
                )
        }
)
@Entity(name = "Code")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Coin {
//    속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "code",
            length = 4
    )
    @NotNull
    private String code;

    @Column(
            name = "name",
            length = 30
    )
    @NotNull
    private String name;

//    역방향 참조 속성
    
//    생성자

    public Coin(String code, String name) {
        this.code = code;
        this.name = name;
    }

//    사용자 메소드 정의


//    메소드 재정의

    //양방향 참조 시 toString 순환 참조에 주의
    @Override
    public String toString() {
        return "Coin{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
