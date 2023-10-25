package dev.naman.productservice.dtos;

import dev.naman.productservice.security.JwtObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request<T> {
    T userPayload;
    JwtObject authPayload;
}
