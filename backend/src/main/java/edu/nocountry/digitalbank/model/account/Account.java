package edu.nocountry.digitalbank.model.account;

import edu.nocountry.digitalbank.model.transaction.Transaction;
import edu.nocountry.digitalbank.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "accounts")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String number;

    private BigDecimal balance;

    @OneToOne(mappedBy = "senderAccount", fetch = FetchType.LAZY)
    private Transaction senderTransaction;

    @OneToOne(mappedBy = "receiverAccount", fetch = FetchType.LAZY)
    private Transaction receiverTransaction;
}