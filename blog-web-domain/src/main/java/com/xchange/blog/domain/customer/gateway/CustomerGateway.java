package com.xchange.blog.domain.customer.gateway;

import com.xchange.blog.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
