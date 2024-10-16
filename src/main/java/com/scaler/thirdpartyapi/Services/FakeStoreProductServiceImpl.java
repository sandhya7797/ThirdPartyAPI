package com.scaler.thirdpartyapi.Services;

import com.scaler.thirdpartyapi.Configs.ApplicationConfigurator;
import com.scaler.thirdpartyapi.Models.Category;
import com.scaler.thirdpartyapi.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    // To call 3rd party api we have use either RestTemplate or WebClient and single bean is sufficient across application so we store it inside configs module.

    private String API_URL = "https://fakestoreapi.com/products";

    private RestTemplate restTemplate;

    public FakeStoreProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProduct(long id) {
        FakeStoreResponseDTO responseDTO = restTemplate.getForObject(API_URL + "/" + id,
                FakeStoreResponseDTO.class);
        return convertFakeStoreProductToProduct(responseDTO);
    }

    private Product convertFakeStoreProductToProduct(FakeStoreResponseDTO responseDTO) {

        Product product = new Product();

        product.setId(responseDTO.getId());
        product.setTitle(responseDTO.getTitle());
        product.setDescription(responseDTO.getDescription());
        product.setPrice(responseDTO.getPrice());

        //Product has category object initialize properly
        Category category = new Category();
        category.setName(responseDTO.getCategory());
        product.setCategory(category);

        product.setImage(responseDTO.getImage());

        return product;

    }
}
