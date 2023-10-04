package com.ezel.voza.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlackMember is a Querydsl query type for BlackMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlackMember extends EntityPathBase<BlackMember> {

    private static final long serialVersionUID = -896279615L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlackMember blackMember = new QBlackMember("blackMember");

    public final com.ezel.voza.domain.group.entity.QGroup group;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> kickOutTime = createDateTime("kickOutTime", java.time.LocalDateTime.class);

    public final com.ezel.voza.domain.user.entity.QUser user;

    public QBlackMember(String variable) {
        this(BlackMember.class, forVariable(variable), INITS);
    }

    public QBlackMember(Path<? extends BlackMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlackMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlackMember(PathMetadata metadata, PathInits inits) {
        this(BlackMember.class, metadata, inits);
    }

    public QBlackMember(Class<? extends BlackMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new com.ezel.voza.domain.group.entity.QGroup(forProperty("group")) : null;
        this.user = inits.isInitialized("user") ? new com.ezel.voza.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

