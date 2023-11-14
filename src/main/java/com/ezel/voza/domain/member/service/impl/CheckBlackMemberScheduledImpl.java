package com.ezel.voza.domain.member.service.impl;

import com.ezel.voza.domain.member.entity.BlackMember;
import com.ezel.voza.domain.member.repository.BlackMemberRepository;
import com.ezel.voza.domain.member.service.CheckBlackMemberScheduled;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@ServiceWithTransactional
@RequiredArgsConstructor
public class CheckBlackMemberScheduledImpl implements CheckBlackMemberScheduled {

    private final BlackMemberRepository blackMemberRepository;

    @Override
    @Scheduled(fixedRate = 5 * 60 * 60 * 1000) // 5시간 마다 실행
    public void execute() {

        List<BlackMember> blackMemberList = blackMemberRepository.findAllEntityGraph();

        LocalDateTime currentTime = LocalDateTime.now();

        blackMemberList.stream()
                .filter(blackMember -> currentTime.isAfter(blackMember.getKickOutTime()))
                .forEach(blackMemberRepository::delete);
    }
}
