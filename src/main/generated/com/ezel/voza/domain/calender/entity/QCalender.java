package com.ezel.voza.domain.calender.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalender is a Querydsl query type for Calender
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCalender extends EntityPathBase<Calender> {

    private static final long serialVersionUID = 1731376466L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalender calender = new QCalender("calender");

    public final StringPath content = createString("content");

    public final StringPath date = createString("date");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final com.ezel.voza.domain.group.entity.QGroup group;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final com.ezel.voza.domain.user.entity.QUser user;

    public QCalender(String variable) {
        this(Calender.class, forVariable(variable), INITS);
    }

    public QCalender(Path<? extends Calender> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalender(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalender(PathMetadata metadata, PathInits inits) {
        this(Calender.class, metadata, inits);
    }

    public QCalender(Class<? extends Calender> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new com.ezel.voza.domain.group.entity.QGroup(forProperty("group")) : null;
        this.user = inits.isInitialized("user") ? new com.ezel.voza.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

