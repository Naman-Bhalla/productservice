select u1_0.id, u1_0.clazz_, u1_0.email, u1_0.name, u1_0.average_rating, u1_0.attendance, u1_0.psp
from (select id, email, name, null as average_rating, null as attendance, null as psp, 0 as clazz_
      from tpc_user
      union all
      select id, email, name, average_rating, null as attendance, null as psp, 1 as clazz_
      from tpc_mentor
      union all
      select id, email, name, null as average_rating, attendance, psp, 2 as clazz_
      from tpc_student
      union all
      select id, email, name, average_rating, null as attendance, null as psp, 3 as clazz_
      from tpc_ta) u1_0


select c1_0.id,
       c1_0.name,
       p1_0.category_id,
       p1_0.id,
       p1_0.description,
       p1_0.image,
       p1_0.price,
       p1_0.title
from category c1_0
         left join product p1_0 on c1_0.id = p1_0.category_id
where c1_0.id = ?

select c1_0.id, c1_0.name
from category c1_0
where c1_0.id = ?

select p1_0.category,
       p1_0.id,
       p1_0.description,
       p1_0.image,
       p2_0.id,
       p2_0.currency,
       p2_0.price,
       p1_0.title
from product p1_0
         left join price p2_0 on p2_0.id = p1_0.price_id
where p1_0.category = ?

select p1_0.category,
       p1_0.id,
       p1_0.description,
       p1_0.image,
       p2_0.id,
       p2_0.currency,
       p2_0.price,
       p1_0.title
from product p1_0
         left join price p2_0 on p2_0.id = p1_0.price_id
where p1_0.category = ?

select c1_0.id,
       c1_0.name,
       p1_0.category,
       p1_0.id,
       p1_0.description,
       p1_0.image,
       p2_0.id,
       p2_0.currency,
       p2_0.price,
       p1_0.title
from category c1_0
         left join product p1_0 on c1_0.id = p1_0.category
         left join price p2_0 on p2_0.id = p1_0.price_id
where c1_0.id = ? Hibernate:
select c1_0.id,
       c1_0.name,
       p1_0.category,
       p1_0.id,
       p1_0.description,
       p1_0.image,
       p2_0.id,
       p2_0.currency,
       p2_0.price,
       p1_0.title
from category c1_0
         left join product p1_0 on c1_0.id = p1_0.category
         left join price p2_0 on p2_0.id = p1_0.price_id
where c1_0.id = ?