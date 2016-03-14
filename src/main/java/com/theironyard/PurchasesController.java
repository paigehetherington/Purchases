package com.theironyard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by vajrayogini on 3/9/16.
 */
@Controller
public class PurchasesController {

    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;

    @PostConstruct
    public void init() throws FileNotFoundException {
        if (customers.count() == 0) { //only runs once if empty
            File f = new File("customers.csv"); //Scanner scanner = new Scanner(new File("customers.csv"));
            Scanner scanner = new Scanner(f);
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                Customer c = new Customer();
                c.name = (columns[0]);
                c.email = (columns[1]);
                customers.save(c);



            }
        }
        if (purchases.count() == 0) {
            File f = new File("purchases.csv");
            Scanner scanner = new Scanner(f);
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                Purchase p = new Purchase();
                p.date =(columns[1]);
                p.creditCard = (columns[2]);
                p.cvv = (columns[3]);
                p.category = (columns[4]);

                int customerId = Integer.valueOf(columns[0]); //joining customer to purchases based on customerId
                p.customer = customers.findOne(customerId); //or Customer customer = customers.findOne(Integer.valueOf(columns[0]));


                purchases.save(p);
            }
        }


        System.out.println("Started up!");

    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category, Integer page) {  //Integer b/c option to not recieve
        //can reduce duplication using:  List<Purchase> p;
        page = (page == null) ? 0 : page;
        PageRequest pr = new PageRequest(page, 5); //if page zero makeit 0, otherwise return page, 20 on each page
        Page<Purchase> p;
        if (category != null) {
            p = purchases.findByCategory(pr, category);

        } else {
            p = purchases.findAll(pr);

        }
        model.addAttribute("purchases", p);
        model.addAttribute("nextPage", page+1);
        model.addAttribute("showNext", p.hasNext());
        model.addAttribute("category", category);



        return "home";
    }




}
