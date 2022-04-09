package com.proday.market.web.controller;

import com.proday.market.domain.Purchase;
import com.proday.market.domain.service.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ruben Malaga
 */
@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(
            this.purchaseService.getAll(), HttpStatus.OK
        );
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<Purchase> getPurchase(
        @PathVariable("purchaseId") int purchaseId
    ) {
        return this.purchaseService.getPurchase(purchaseId)
                    .map(purchase -> new ResponseEntity<>(purchase, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND))
        ;
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Purchase>> getByClient(
        @PathVariable("clientId") String clientId
    ) {
        return this.purchaseService.getByClient(clientId)
                    .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND))
        ;
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(
            this.purchaseService.save(purchase), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/delete/{purchaseId}")
    public ResponseEntity delete(@PathVariable("purchaseId") int purchaseId) {
        if (this.purchaseService.delete(purchaseId)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
