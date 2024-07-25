package com.xchange.blog.domain.customer.gateway;

import com.xchange.blog.domain.customer.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}
