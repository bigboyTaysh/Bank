package pl.wolski.bank.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "credits_applications")
public class CreditApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 17, fraction = 4)
    private BigDecimal creditAmount;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 17, fraction = 4)
    private BigDecimal totalRepayment;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 17, fraction = 4)
    private BigDecimal monthRepayment;

    @NotNull
    @PositiveOrZero
    private int numberOfMonths;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_credit_type", nullable = false)
    private CreditType creditType;

    @Past
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@Temporal(TemporalType.DATE)
    private Date dateOfSubmissionOfTheApplication;

    private Boolean accepted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_user", nullable = false)
    private User user;
}
