package capstone4.Cosi.domains;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "wallets")
@Entity(name = "Wallet")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Wallet {
//    속성
    @EmbeddedId
    private WalletId walletId;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "wallets_member_id_fk")
    )
    private Member member;

    @MapsId("coinId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "coin_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "wallets_coin_id_fk")
    )
    private Coin coin;

    @Column(
            name = "amount",
            columnDefinition = "DECIMAL(12, 8) CHECK(amount >= 0.00000001)"
    )
    @NotNull
    private Double amount;

    @Column(
            name = "avg_buy_price",
            columnDefinition = "DECIMAL(14, 4) CHECK(avg_buy_price > 0)"
    )
    @NotNull
    private Double avgBuyPrice;

    @Column(
            name = "total_buy_price",
            columnDefinition = "DECIMAL(16, 4) CHECK(total_buy_price > 0)"
    )
    @NotNull
    private Double totalBuyPrice;
    
//    생성자

    public Wallet(Member member,
                  Coin coin,
                  Double amount,
                  Double avgBuyPrice) {
        this.walletId = new WalletId(member.getId(), coin.getId());
        this.member = member;
        this.coin = coin;
        this.amount = amount;
        this.avgBuyPrice = avgBuyPrice;
        this.totalBuyPrice = (Double) this.amount * this.avgBuyPrice;
    }

    //    사용자 메소드 정의
    
//    메소드 재정의
}
