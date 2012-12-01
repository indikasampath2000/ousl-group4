package ousl.group4.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "listing")
public class Listing implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long itemId;
    private int listingTimeId;
    private Long categoryId;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "item_id")
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Column(name = "listing_time_id")
    public int getListingTimeId() {
        return listingTimeId;
    }

    public void setListingTimeId(int listingTimeId) {
        this.listingTimeId = listingTimeId;
    }

    @Column(name = "category_id")
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

}
