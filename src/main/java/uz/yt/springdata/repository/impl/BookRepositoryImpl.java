package uz.yt.springdata.repository.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;
import uz.yt.springdata.dao.CustomBook;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.helper.NumberHelper;
import uz.yt.springdata.helper.StringHelper;
import uz.yt.springdata.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl {

    private final EntityManager entityManager;

    public BookRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<?> getAllBooksByParam(MultiValueMap<String, String> params){
        StringBuilder queryBuilder = new StringBuilder(" where 1=1 ");
        queryParams(params, queryBuilder);

        Integer size = NumberHelper.toInt(params.getFirst("size")), page = NumberHelper.toInt(params.getFirst("page"));
        boolean isPageable = StringHelper.isValidDigit(params.getFirst("size")) && StringHelper.isValidDigit(params.getFirst("page"));


        if (isPageable) {
            queryBuilder.append(" limit :size offset :page");
        }

        String queryStr = "select t.id, " +
                "t.nameuz, " +
                "a.firstname || ' ' || a.lastname as author_name, " +
                "p.name as publisher_name, " +
                "t.cost " +
                "from book t " +
                "left join author a on a.id = t.author_id " +
                "left join publisher p on p.id = t.publisher_id " + queryBuilder;

        Query query = entityManager.createNativeQuery(queryStr, CustomBook.class);

        queryValues(params, query);

        if (isPageable){
            query.setParameter("size", size);
            query.setParameter("page", size * page);
        }

        List<CustomBook> books = query.getResultList();

        if (isPageable){
            return Optional.of(new PageImpl<>(books, PageRequest.of(page, size), books.size()));
        }

        return Optional.of(books);
    }


    private void queryParams(MultiValueMap<String, String> params, StringBuilder sb){
        if (StringHelper.isValidDigit(params.getFirst("id"))){
            sb.append(" AND t.id = :id");
        }
        if (StringHelper.isValid(params.getFirst("name"))){
            sb.append(" AND t.nameuz like :name");
        }
        if (StringHelper.isValid(params.getFirst("genre"))){
            sb.append(" AND t.genre like :genre");
        }
        if (StringHelper.isValid(params.getFirst("authorName"))){
            sb.append(" AND a.lastname || ' ' || a.firstname like :authorName");
        }
    }

    public void queryValues(MultiValueMap<String, String> params, Query query){
        if(StringHelper.isValidDigit(params.getFirst("id"))){
            query.setParameter("id", NumberHelper.toInt(params.getFirst("id")));
        }

        if (StringHelper.isValid(params.getFirst("name"))){
            query.setParameter("name", params.getFirst("name"));
        }
        if (StringHelper.isValid(params.getFirst("genre"))){
            query.setParameter("genre", params.getFirst("genre"));
        }
        if (StringHelper.isValid(params.getFirst("authorName"))){
            query.setParameter("authorName", params.getFirst("authorName"));
        }
    }
}
