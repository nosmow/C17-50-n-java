package edu.nocountry.digitalbank.model.category;

import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransaction;
import edu.nocountry.digitalbank.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "categories")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<CategoryTransaction> categoryTransactions = new ArrayList<>();
}
