package sg.edu.nus.iss.workshop24.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.workshop24.models.LineItem;
import sg.edu.nus.iss.workshop24.models.Order;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @PostMapping
    public String postCart(@RequestBody MultiValueMap<String, String> form,
            Model model, HttpSession session) {
        
        // create new cart if cart not in sessionStorage
        List<LineItem> itemList = (List<LineItem>) session.getAttribute("cart");
        if (null == itemList) {
            itemList = new LinkedList();
            session.setAttribute("cart", itemList);
        }

        // create line item from form data
        String product = form.getFirst("product");
        Float unitPrice = Float.parseFloat(form.getFirst("unitprice"));
        Float discount = Float.parseFloat(form.getFirst("discount"));
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));
        itemList.add(LineItem.create(product, unitPrice, discount, quantity));
        Order order = new Order();
        order.setCustomerName(form.getFirst("name"));
        order.setitemList(itemList);

        session.setAttribute("order", order);
        model.addAttribute("itemList", itemList);
        return "cart";
    }
}
