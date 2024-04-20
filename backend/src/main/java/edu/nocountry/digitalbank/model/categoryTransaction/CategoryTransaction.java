package edu.nocountry.digitalbank.model.categoryTransaction;

import edu.nocountry.digitalbank.model.category.Category;
import edu.nocountry.digitalbank.model.transaction.Transaction;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "categories_transactions")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
