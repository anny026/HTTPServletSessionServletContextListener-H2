package shop.model.bean;

import shop.model.repository.GoodDao;

import java.util.Objects;

public class Good {
    private Long id;
    private String title;
    private float price;

    GoodDao goodDao= new GoodDao();
    public Good() { }


    public Good(Long id, String title, float price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle(Long id) {
        return goodDao.get(id).getTitle();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }
    public float getPrice(Long id) {
        return goodDao.get(id).getPrice();
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Good)) return false;
        Good good = (Good) o;
        return Float.compare(good.getPrice(), getPrice()) == 0 && Objects.equals(getId(), good.getId()) && Objects.equals(getTitle(), good.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getPrice());
    }

    @Override
    public String toString() {
        return  "  "+title+" "+ price+ "$";
    }
}
