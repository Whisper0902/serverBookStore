package Order.OrderDto;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private List<CartItemDto> items;
    private BigDecimal totalPrice;

    public CartDto() {
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
