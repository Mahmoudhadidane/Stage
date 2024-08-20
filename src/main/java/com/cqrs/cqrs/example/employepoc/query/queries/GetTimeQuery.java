package com.cqrs.cqrs.example.employepoc.query.queries;



import com.hydatis.cqrsref.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetTimeQuery extends BaseQuery{
    private String ipAddress ;
    private int portNumber ;

}
