package com.ezel.voza.domain.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlackUser is a Querydsl query type for BlackUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlackUser extends EntityPathBase<BlackUser> {

    private static final long serialVersionUID = 1904128292L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlackUser blackUser = new QBlackUser("blackUser");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.ezel.voza.domain.user.entity.QUser user;

    public QBlackUser(String variable) {
        this(BlackUser.class, forVariable(variable), INITS);
    }

    public QBlackUser(Path<? extends BlackUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlackUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlackUser(PathMetadata metadata, PathInits inits) {
        this(BlackUser.class, metadata, inits);
    }

    public QBlackUser(Class<? extends BlackUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.ezel.voza.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

