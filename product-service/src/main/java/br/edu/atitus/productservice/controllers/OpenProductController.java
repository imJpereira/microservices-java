package br.edu.atitus.productservice.controllers;

import br.edu.atitus.productservice.entities.ProductEntity;
import br.edu.atitus.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class OpenProductController {

    private final ProductRepository productRepository;

    public OpenProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/{idProduct}/{targetCurrency}")
    public ResponseEntity<ProductEntity> getById(@PathVariable Long idProduct, @PathVariable String targetCurrency) throws Exception {

        ProductEntity product = productRepository.findById(idProduct)
                .orElseThrow(() -> new Exception("Product not found"));

        product.setConvertedPrice(product.getPrice());
        product.setEnvironment("Product Service is running at " + serverPort);

        return ResponseEntity.ok(product);
    }


}
