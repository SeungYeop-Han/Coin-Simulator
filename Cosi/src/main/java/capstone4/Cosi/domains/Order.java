package capstone4.Cosi.domains;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "orders")
@Entity(name = "Order")
@Getter
@Setter
@NoArgsConstructor
public class Order {
//    속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @NotNull
    @Column(
            name = "is_buying",
            columnDefinition = "BOOLEAN"
    )
    private Boolean isBuying;

    @NotNull
    @Column(name = "order_type")
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @NotNull
    @CreatedDate
    @Column(
            name = "order_datetime",
            columnDefinition = "DATETIME"
    )
    private LocalDateTime orderDatetime;

    @NotNull
    @Column(name = "order_state")
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Column(
            name = "order_amount",
            columnDefinition = "DECIMAL(12, 8) CHECK(order_amount >= 0.00000001)"
    )
    private Double orderAmount;

    @Column(
            name = "order_price",
            columnDefinition = "DECIMAL(14, 4) CHECK(order_price > 0)"
    )
    private Double orderPrice;

    @Column(
            name = "stop_price",
            columnDefinition = "DECIMAL(14, 4) CHECK(stop_price > 0)"
    )
    private Double stopPrice;

    @Column(
            name = "total_price",
            columnDefinition = "DECIMAL(16, 4) CHECK(total_price >= 5000)"
    )
    private Double totalPrice;

    @Column(
            name = "buying_fee",
            columnDefinition = "DECIMAL(14, 4) CHECK(buying_fee >= 0)"
    )
    private Double buyingFee;

    @Column(
            name = "buying_fee_rate",
            columnDefinition = "DECIMAL(6, 5) CHECK(buying_fee_rate >= 0 AND buying_fee_rate <= 1)"
    )
    private Double buyingFeeRate;

//    FKs
    @NotNull
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "orders_member_id_fk")
    )
    private Member member;

    @NotNull
    @ManyToOne
    @JoinColumn(
            name = "coin_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "orders_coin_id_fk")
    )
    private Coin coin;

//    역방향 참조 속성
    @OneToOne(
            mappedBy = "order",
            cascade = CascadeType.ALL,    //CascadeType.Merge(또는 ALL)가 명시되지 않으면 entity 연관관계만이 바뀌었을 때, save해도 반영 x
            orphanRemoval = true
    )
    private FilledOrder filledOrder;

//    생성자

    public Order(
            Boolean isBuying,
            OrderType orderType,
            LocalDateTime orderDatetime,
            OrderState orderState,
            Double orderAmount,
            Double orderPrice,
            Double stopPrice,
            Double totalPrice,
            Double buyingFee,
            Double buyingFeeRate,
            Member member, Coin coin) {
        this.isBuying = isBuying;
        this.orderType = orderType;
        this.orderDatetime = orderDatetime;
        this.orderState = orderState;
        this.orderAmount = orderAmount;
        this.orderPrice = orderPrice;
        this.stopPrice = stopPrice;
        this.totalPrice = totalPrice;
        this.buyingFee = buyingFee;
        this.buyingFeeRate = buyingFeeRate;
        this.member = member;
        this.coin = coin;
    }


//    사용자 정의 메소드

//    재정의 메소드
    //양방향 참조 시 toString 순환 참조에 주의

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", isBuying=" + isBuying +
                ", orderType='" + orderType + '\'' +
                ", orderDatetime=" + orderDatetime +
                ", orderState='" + orderState + '\'' +
                ", orderAmount=" + orderAmount +
                ", orderPrice=" + orderPrice +
                ", stopPrice=" + stopPrice +
                ", totalPrice=" + totalPrice +
                ", buyingFee=" + buyingFee +
                ", buyingFeeRate=" + buyingFeeRate +
                ", member=" + member +
                ", coin=" + coin +
                '}';
    }
}
