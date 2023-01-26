package com.allstate.payments.control;

import com.allstate.payments.domain.Payment;
import com.allstate.payments.exceptions.PaymentNotFoundException;
import com.allstate.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments(@RequestParam(value="country", required = false) String country,
                                        @RequestParam(value="order", required = false) String order) {
        if (order != null) {
            return paymentService.getByOrder(order);
        }
        else if (country != null) {
            return paymentService.getByCountry(country);
        }
        else
        {
            return paymentService.getAllPayments();
        }
    }

    @GetMapping("/{id}")
    public Payment findById(@PathVariable Integer id) throws PaymentNotFoundException {
        return paymentService.getById(id);
    }

    @PostMapping
    public Payment saveNewPayment(@RequestBody Payment payment) {
        System.out.println(payment);
        return paymentService.savePayment(payment);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Integer id, @RequestBody HashMap<String, Object> fields) {
        return paymentService.updatePayment(id, fields);
    }

//    @GetMapping("/{paymentId}")
//    public Payment findById(@PathVariable("paymentId") Integer id) {
//        return paymentService.getById(id);
//    }

//    @GetMapping("/claims/insurancetype/{itype}/{status}")
//    public void doSomethingClever(@PathVariable String itype, @PathVariable("status") Integer status) {
//        System.out.println(itype);
//    }

}
