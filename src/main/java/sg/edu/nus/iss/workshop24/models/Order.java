package sg.edu.nus.iss.workshop24.models;


import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId;
    private Date orderDate;
    private String customerName;
    private String shippingAddress;
    private String notes;
    private Float tax;
    private List<LineItem> itemList = new LinkedList<>();

    public void addItem(LineItem i) {
        this.itemList.add(i);
    }

    public List<LineItem> getLineItems() {
        return null;
    }
}