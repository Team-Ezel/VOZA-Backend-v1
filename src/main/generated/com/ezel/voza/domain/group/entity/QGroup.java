package com.ezel.voza.domain.group.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroup is a Querydsl query type for Group
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroup extends EntityPathBase<Group> {

    private static final long serialVersionUID = 1634230884L;

    public static final QGroup group = new QGroup("group1");

    public final com.ezel.voza.global.entity.QBaseTimeEntity _super = new com.ezel.voza.global.entity.QBaseTimeEntity(this);

    public final BooleanPath canEnter = createBoolean("canEnter");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> groupId = createNumber("groupId", Long.class);

    public final StringPath groupName = createString("groupName");

    public final StringPath introduceGroup = createString("introduceGroup");

    public final StringPath leaderName = createString("leaderName");

    public final MapPath<com.ezel.voza.domain.user.entity.User, String, StringPath> members = this.<com.ezel.voza.domain.user.entity.User, String, StringPath>createMap("members", com.ezel.voza.domain.user.entity.User.class, String.class, StringPath.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath region = createString("region");

    public final BooleanPath stop = createBoolean("stop");

    public final SetPath<String, StringPath> tags = this.<String, StringPath>createSet("tags", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath url = createString("url");

    public QGroup(String variable) {
        super(Group.class, forVariable(variable));
    }

    public QGroup(Path<? extends Group> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroup(PathMetadata metadata) {
        super(Group.class, metadata);
    }

}

