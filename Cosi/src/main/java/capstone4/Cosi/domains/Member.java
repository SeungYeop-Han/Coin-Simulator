package capstone4.Cosi.domains;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(
        name = "members",     //테이블 이름
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "members_email_uk",       //제약조건 이름
                        columnNames = "email"           //컬럼 이름
                )
        }
)
@Entity(name = "Member")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Member implements UserDetails {
//    속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "email",
            length = 320
    )
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "locked")
    @NotNull
    private Boolean locked = false;

    @Column(name = "enabled")
    @NotNull
    private Boolean enabled = false;

//    역방향 참조 속성
    @OneToOne(
            mappedBy = "member",
            cascade = CascadeType.ALL,    //CascadeType.Merge(또는 ALL)가 명시되지 않으면 entity 연관관계만이 바뀌었을 때, save해도 반영 x
            orphanRemoval = true
    )
    private Asset asset;

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private final List<Wallet> wallets = new ArrayList<>();

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private final List<Order> orders = new ArrayList<>();

//    @OneToMany(
//            mappedBy = "member",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.LAZY
//    )
//    private final List<ConfirmationToken> confirmationTokens = new ArrayList<>();

//    생성자 정의


    public Member(String email,
                  String password,
                  String name,
                  UserRole userRole) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.userRole = userRole;
    }

    //    사용자 메소드 정의
    //wallet 추가
    //this가 주어진 wallet과 같은 코인의 wallet을 이미 가지고 있는 경우 DuplicatedWalletException을 발생
    public void addWallet(Wallet wallet) {
        this.wallets.forEach(w -> {
            if(!w.getMember().getId()
                    .equals(this.getId())){
                throw new RuntimeException("Other person's wallet!! YOU THIEVE!!");
            }
            if( w.getCoin().getId()
                    .equals(wallet.getCoin().getId()) )
                throw new RuntimeException(
                        String.format(
                                "member: %s  already has wallet of %s", this.toString(), wallet.getCoin().getCode()
                        )
                );
        });

        this.wallets.add(wallet);
    }

    //wallet 삭제
    //this가 주어진 wallet을 가지고 있지 않은 경우 예외를 발생시킴
    public void removeWallet(Wallet wallet){
        if(this.wallets.contains(wallet)){
            this.wallets.remove(wallet);
        }
        else{
            throw new RuntimeException(
                    String.format(
                            "member: %s  hasn't wallet %s", this.toString(), wallet
                    )
            );
        }
    }
    
    //주문 생성
    public void addOrder(Order order) {
        this.orders.forEach(o -> {
            if(!o.getMember().getId()
                    .equals(this.getId())){
                throw new RuntimeException("Other person's order!!");
            }
        });

        if(this.orders.contains(order)){
            throw new RuntimeException(String.format("%s already has the order %s", this, order));
        }
        this.orders.add(order);
    }

    //주문 삭제(주문 취소가 아님에 주의)
    public void removeOrder(Order order){
        if(this.orders.contains(order)){
            this.orders.remove(order);
        }
        else{
            throw new RuntimeException(
                    String.format("member(orderer): %s hasn't the order %s", this, order)
            );
        }
    }

//    메소드 재정의

    //양방향 참조 시 toString 순환 참조에 주의
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    //UserDetail for security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return authorities;
    }

    @Override
    public String getPassword() { return this.password; }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
