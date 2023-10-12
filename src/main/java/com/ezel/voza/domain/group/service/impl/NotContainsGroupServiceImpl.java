package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.presentation.dto.response.GroupListResponse;
import com.ezel.voza.domain.group.presentation.dto.response.GroupResponse;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.group.service.OtherGroupListService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.UserUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.ezel.voza.domain.group.entity.QGroup.group;

@ReadOnlyService
@RequiredArgsConstructor
@Qualifier("otherGroupListService")
public class NotContainsGroupServiceImpl implements OtherGroupListService {

    private final GroupRepository groupRepository;
    private final UserUtil util;
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GroupListResponse> execute(Pageable pageable) {
        User currentUser = util.currentUser();

        // 자신이 속한 그룹 목록을 가져옵니다.
        List<Group> userGroups = groupRepository.findGroupsByMembers(currentUser);

        // 모든 그룹을 가져옵니다.
        List<Group> allGroups = queryFactory
                .selectFrom(group)
                .fetch();

        // 자신이 속하지 않은 그룹을 필터링합니다.
        List<Group> notBelongingGroups = allGroups.stream()
                .filter(group -> !userGroups.contains(group))
                .toList();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<GroupListResponse> pagedGroups;

        if (notBelongingGroups.size() < startItem) {
            pagedGroups = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, notBelongingGroups.size());
            pagedGroups = notBelongingGroups.subList(startItem, toIndex).stream()
                    .map(group -> GroupListResponse.builder()
                            .groupList(Collections.singletonList(GroupResponse.groupResponse(group)))
                            .build())
                    .collect(Collectors.toList());
        }

        return new PageImpl<>(pagedGroups, pageable, notBelongingGroups.size());
    }
}
