package com.scaler.thirdpartyapi.Services;

import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
import com.scaler.thirdpartyapi.Models.Category;
import com.scaler.thirdpartyapi.Models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreproductservice")
public class FakeStoreProductServiceImpl implements ProductService {

    // To call 3rd party api we have use either RestTemplate or WebClient and single bean is sufficient across application so we store it inside configs module.

    private String API_URL = "https://fakestoreapi.com/products";

    private RestTemplate restTemplate;

    public FakeStoreProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProduct(long id) throws ProductNotExistsException {

//        int a = 1/0; this exception also catched by controller advice.

        FakeStoreResponseDTO responseDTO = restTemplate.getForObject(API_URL + "/" + id,
                FakeStoreResponseDTO.class);
        if(responseDTO==null) {
            throw new ProductNotExistsException("Product with " + id + " doesn't exists!");
        }
        return convertFakeStoreProductToProduct(responseDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreResponseDTO[] fakeStoreResponseDTOArray =
                restTemplate.getForObject(API_URL, FakeStoreResponseDTO[].class);

        List<Product> productList = new ArrayList<>();

        if(fakeStoreResponseDTOArray != null) {
            for (FakeStoreResponseDTO fakeStoreResponseDTO : fakeStoreResponseDTOArray) {
                productList.add(convertFakeStoreProductToProduct(fakeStoreResponseDTO));
            }
        }

        return productList;
    }

    @Override
    public Product addProduct(FakeStoreResponseDTO fakeStoreProduct) {
        FakeStoreResponseDTO responseDTO = restTemplate.postForObject(API_URL,
                fakeStoreProduct, FakeStoreResponseDTO.class);
        return convertFakeStoreProductToProduct(responseDTO);
    }

    //PATCH
//    @Override
//    public Product updateProduct(long productId, FakeStoreResponseDTO fakeStoreProduct) {
//
////        FakeStoreResponseDTO fakeStoreResponseDTO = restTemplate.patchForObject(API_URL +"/" + productId,
////                fakeStoreProduct, FakeStoreResponseDTO.class);
//
//        HttpEntity<FakeStoreResponseDTO> requestEntity = new HttpEntity<>(fakeStoreProduct);
//
//        ResponseEntity<FakeStoreResponseDTO> responseDTO = restTemplate.exchange(API_URL + "/" + productId,
//                HttpMethod.PATCH, requestEntity, FakeStoreResponseDTO.class);
//
//        return convertFakeStoreProductToProduct(responseDTO.getBody());
//    }

    //PUT
    //Since put() api is having 'void' return type, we are using exchange() api which is a low-level api for CRUD operations using RestTemplate.
    @Override
    public Product replaceProduct(long productId, FakeStoreResponseDTO fakeStoreProduct) {

        HttpEntity<FakeStoreResponseDTO> requestEntity = new HttpEntity<>(fakeStoreProduct);

        ResponseEntity<FakeStoreResponseDTO> responseDTO = restTemplate.exchange(API_URL +"/" + productId,
                HttpMethod.PUT, requestEntity, FakeStoreResponseDTO.class);

        return convertFakeStoreProductToProduct(responseDTO.getBody());
    }

    @Override
    public Product deleteProduct(long productId) {

        ResponseEntity<FakeStoreResponseDTO> responseDTO = restTemplate.exchange(API_URL +"/" + productId,
                HttpMethod.DELETE, null, FakeStoreResponseDTO.class);

        return convertFakeStoreProductToProduct(responseDTO.getBody());
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
