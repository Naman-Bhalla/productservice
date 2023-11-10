package dev.naman.productservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken>
{
    private static Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt)
    {
        List<String> roles = jwt.getClaim("roles");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (String role: roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }

        return grantedAuthorities;
    }

    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();


    @Override
    public AbstractAuthenticationToken convert(final Jwt source)
    {
        Collection<GrantedAuthority> authorities = Stream.concat(defaultGrantedAuthoritiesConverter.convert(source)
                                .stream(),
                        extractResourceRoles(source).stream())
                .collect(Collectors.toSet());
        return new JwtAuthenticationToken(source, authorities);
    }
}