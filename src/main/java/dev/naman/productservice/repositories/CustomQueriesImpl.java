package dev.naman.productservice.repositories;

import dev.naman.productservice.dtos.GenericProductDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CustomQueriesImpl implements CustomQueries{

    @PersistenceContext
    private EntityManager em;
    private String getAllProducts = "select cast(p.id as char) as id, p.title, p.description, p.image, c.name as category, pri.price from Product p\r\n " +
            "left join category c on c.id = p.category " +
            "left join price pri on pri.id = p.price_id ";
    @Override
    public List<GenericProductDto> findAllProducts() {
        String sql = getAllProducts;
        Query query = em.createNativeQuery(sql)
                .unwrap(org.hibernate.query.Query.class);
        query.setResultTransformer(new AliasToBeanResultTransformer(GenericProductDto.class));
        List<GenericProductDto> list = query.list();
        return list;
    }
}
