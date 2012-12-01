package ousl.group4.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String transactionId;
    private User userFrom;
    private User userTo;
    private Listing listing;
    private int type;
    private BigDecimal amount;
    private boolean status;
    private Date purchaseDate;
    private Date paymentDate;
    private Date paymentReceivedDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_to_id")
    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id")
    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Column(name = "purchase_date")
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Column(name = "payment_date")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Column(name = "payment_receive_date")
    public Date getPaymentReceivedDate() {
        return paymentReceivedDate;
    }

    public void setPaymentReceivedDate(Date paymentReceivedDate) {
        this.paymentReceivedDate = paymentReceivedDate;
    }

}
