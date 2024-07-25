package com.xchange.blog.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.xchange.blog.dto.CustomerAddCmd;
import com.xchange.blog.dto.CustomerListByNameQry;
import com.xchange.blog.dto.data.CustomerDTO;

public interface CustomerServiceI {

    Response addCustomer(CustomerAddCmd customerAddCmd);

    MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}
