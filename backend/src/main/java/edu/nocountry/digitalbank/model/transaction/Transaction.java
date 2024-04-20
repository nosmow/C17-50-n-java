package edu.nocountry.digitalbank.model.transaction;

import edu.nocountry.digitalbank.model.account.Account;
import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransaction;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "transactions")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_account_id")
    private Account receiverAccount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;

    private BigDecimal amount;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY)
    private List<CategoryTransaction> categoryTransactions = new ArrayList<>();
}
