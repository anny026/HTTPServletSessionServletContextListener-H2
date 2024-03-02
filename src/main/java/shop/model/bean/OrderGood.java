package shop.model.bean;

public class OrderGood {
    private Long id;
    private Long orderId;
    private Long goodId;

    Good good= new Good();
    public OrderGood(Long id, Long orderId, Long goodId) {
        this.id = id;
        this.orderId = orderId;
        this.goodId = goodId;
    }
    public OrderGood() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    @Override
    public String toString() {
        return
                "    " +good.getTitle(goodId) +"   "+ good.getPrice(goodId)+ " $";
    }
}
