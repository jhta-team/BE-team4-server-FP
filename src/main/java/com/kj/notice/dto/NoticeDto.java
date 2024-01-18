package com.kj.notice.dto;

import com.kj.faq.dto.FaqBoardDto;
import com.kj.faq.entity.FaqBoard;
import com.kj.faq.entity.FaqCategory;
import com.kj.member.dto.CustomUserDetails;
import com.kj.member.entity.Member;
import com.kj.notice.entity.Notice;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private String noticeCategory;
    private LocalDateTime noticeDate;
    private int noticeView;

    public static Notice toEntity(NoticeDto noticeDto, Member member) {
        return Notice.builder()
                .id(noticeDto.getId())
                .noticeTitle(noticeDto.getNoticeTitle())
                .noticeContent(noticeDto.getNoticeContent())
                .noticeCategory(noticeDto.getNoticeCategory())
                .noticeView(noticeDto.getNoticeView())
                .noticeDate(LocalDateTime.now())
                .writer(member)
                .build();
    }
}