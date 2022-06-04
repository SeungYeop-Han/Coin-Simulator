package capstone4.Cosi.domains;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "filled_orders")
@Entity(name = "FilledOrder")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class FilledOrder {
    //    속성
    @Id
    @Column(
            name = "order_id",
            updatable = false
    )
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "filled_orders_order_id_fk")
    )
    private Order order;

    @NotNull
    @CreatedDate
    @Column(
            name = "filled_datetime",
            columnDefinition = "DATETIME"
    )
    private LocalDateTime filledDatetime;

    @NotNull
    @Column(
            name = "filled_amount",
            columnDefinition = "DECIMAL(12, 8) CHECK(filled_amount >= 0.00000001)"
    )
    private Double filledAmount;

    @NotNull
    @Column(
            name = "filled_price",
            columnDefinition = "DECIMAL(14, 4) CHECK(filled_price > 0)"
    )
    private Double filledPrice;

    @NotNull
    @Column(
            name = "filled_unit_price",
            columnDefinition = "DECIMAL(14, 4) CHECK(filled_unit_price > 0)"
    )
    private Double filledUnitPrice;

    @NotNull
    @Column(
            name = "selling_fee",
            columnDefinition = "DECIMAL(14, 4) CHECK(selling_fee >= 0)"
    )
    private Double sellingFee;

    @NotNull
    @Column(
            name = "selling_fee_rate",
            columnDefinition = "DECIMAL(6, 5) CHECK(selling_fee_rate >= 0 AND selling_fee_rate <= 1)"
    )
    private Double sellingFeeRate;

//    생성자

    public FilledOrder(
            Order order,
            LocalDateTime filledDatetime,
            Double filledAmount,
            Double filledPrice,
            Double filledUnitPrice,
            Double sellingFee,
            Double sellingFeeRate) {
        this.id = order.getId();
        this.order = order;
        this.filledDatetime = filledDatetime;
        this.filledAmount = filledAmount;
        this.filledPrice = filledPrice;
        this.filledUnitPrice = filledUnitPrice;
        this.sellingFee = sellingFee;
        this.sellingFeeRate = sellingFeeRate;
    }

//    사용자 메소드 정의

//    메소드 재정의
}
