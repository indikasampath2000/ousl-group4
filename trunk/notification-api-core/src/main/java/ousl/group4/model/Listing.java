package ousl.group4.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "listing")
public class Listing implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private Item item;
    private ListingTime listingTime;
    private Category category;
    private int type;
    private BigDecimal price;
    private BigDecimal startingPrice;
    private int quantity;
    private int quantityRemain;
    private Date startTime;
    private Date endTime;
    private boolean status;
    private String paymentInstructions;
    private BigDecimal shippingCost;
    private String shippingInstructions;
    private String returnInstructions;
    private String itemLocation;
    private List<BidMax> bidMaxes = new ArrayList<BidMax>();
    private List<Payment> payments = new ArrayList<Payment>();
    private List<Bid> bids = new ArrayList<Bid>();
    private BigDecimal maxBid;
    private Integer bidCount;
    private String remainingTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_time_id", nullable = false)
    public ListingTime getListingTime() {
        return listingTime;
    }

    public void setListingTime(ListingTime listingTime) {
        this.listingTime = listingTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "starting_price")
    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "quantity_remain")
    public int getQuantityRemain() {
        return quantityRemain;
    }

    public void setQuantityRemain(int quantityRemain) {
        this.quantityRemain = quantityRemain;
    }

    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Column(name = "payment_instructions")
    public String getPaymentInstructions() {
        return paymentInstructions;
    }

    public void setPaymentInstructions(String paymentInstructions) {
        this.paymentInstructions = paymentInstructions;
    }

    @Column(name = "shipping_cost")
    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    @Column(name = "shipping_instructions")
    public String getShippingInstructions() {
        return shippingInstructions;
    }

    public void setShippingInstructions(String shippingInstructions) {
        this.shippingInstructions = shippingInstructions;
    }

    @Column(name = "return_instructions")
    public String getReturnInstructions() {
        return returnInstructions;
    }

    public void setReturnInstructions(String returnInstructions) {
        this.returnInstructions = returnInstructions;
    }

    @Column(name = "item_location")
    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "listing")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<BidMax> getBidMaxes() {
        return bidMaxes;
    }

    public void setBidMaxes(List<BidMax> bidMaxes) {
        this.bidMaxes = bidMaxes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "listing")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "listing")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    @Transient
    public BigDecimal getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(BigDecimal maxBid) {
        this.maxBid = maxBid;
    }

    @Transient
    public Integer getBidCount() {
        return bidCount;
    }

    public void setBidCount(Integer bidCount) {
        this.bidCount = bidCount;
    }

    @Transient
    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }
}
