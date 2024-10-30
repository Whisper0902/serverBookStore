package Order.OrderDto;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;

public class OrderItemDto {

    private Long bookId;
    private Integer quantity;
    private BigDecimal price;

    public OrderItemDto() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
