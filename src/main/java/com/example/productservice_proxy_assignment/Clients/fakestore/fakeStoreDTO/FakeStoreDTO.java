package com.example.productservice_proxy_assignment.Clients.fakestore.fakeStoreDTO;

import com.example.productservice_proxy_assignment.Clients.fakestore.IClientProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDTO implements IClientProductDTO {
    private String id;
    private String title;
    private double price;
    private String description;
    private String imageURL;
    private String category;
}
