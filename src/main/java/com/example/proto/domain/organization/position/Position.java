package com.example.proto.domain.organization.position;

import com.example.proto.constant.CommentLevel;
import com.example.proto.constant.NyStatus;
import com.example.proto.constant.PositionType;
import com.example.proto.domain.organization.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@DynamicInsert
@Entity
@Table(name = "position")
public class Position extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "INT")
    private PositionType type;

    private String name;
    private String publicName;
    private Integer orderRank;
    private Integer checkCount;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "INT")
    private NyStatus isRankSetting;

    private Integer rankHitCount;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "CHAR(1)")
    private CommentLevel commentLevel;

}
