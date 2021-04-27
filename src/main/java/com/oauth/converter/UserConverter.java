package com.oauth.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.oauth.entity.ClientAuthoritiesEntity;

import org.springframework.cglib.core.Converter;

@SuppressWarnings("all")
public class UserConverter implements Converter {

    @Override
    public Object convert(Object o, Class a, Object o2) {
        if (o instanceof Byte) {
            return ((Byte) o) == 1;
        } else if (o instanceof List) {
            List<ClientAuthoritiesEntity> list = (List<ClientAuthoritiesEntity>) o;
            return list.stream().map((clientau) -> clientau.getAuthorities()).collect(Collectors.toList());
        }
        return o;
    }
}
