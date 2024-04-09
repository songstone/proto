package com.example.proto.domain.organization.employee;

import com.example.proto.constant.NyStatus;
import com.example.proto.dto.organization.request.EmployeeRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.example.proto.domain.organization.employee.QEmployee.employee;
import static org.springframework.util.ObjectUtils.isEmpty;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryQuery{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Employee> getEmployeesSearch(EmployeeRequest.List request, Pageable pageable) {

        List<Employee> list = queryFactory
            .selectFrom(employee)
            .leftJoin(employee.rank).fetchJoin()
            .leftJoin(employee.position).fetchJoin()
            .where(
                keywordContains(request.keyword()),
                rankEq(request.rankNo()),
                isActiveEq(request.isActive())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Long> countQuery = queryFactory
            .select(employee.count())
            .from(employee)
            .where(
                keywordContains(request.keyword()),
                rankEq(request.rankNo()),
                isActiveEq(request.isActive())
            );

        return PageableExecutionUtils.getPage(list, pageable, countQuery::fetchOne);
    }

    BooleanExpression rankEq(Integer rankNo) {
        return !isEmpty(rankNo) ? employee.rank.idx.eq(rankNo) : null;
    }
    BooleanExpression isActiveEq(Integer isActive) {
        return !isEmpty(isActive) ? employee.isActive.eq(NyStatus.findByOrdinal(isActive)) : null;
    }
    BooleanExpression keywordContains(String keyword) {
        return hasText(keyword)
            ? nameContains(keyword).or(emailContains(keyword)).or(phoneContains(keyword))
            : null;
    }
    BooleanExpression nameContains(String name) {
        return hasText(name) ? employee.name.contains(name) : null;
    }
    BooleanExpression emailContains(String email) {
        return hasText(email) ? employee.email.contains(email) : null;
    }
    BooleanExpression phoneContains(String phone) {
        return hasText(phone) ? employee.phone.contains(phone) : null;
    }
}
