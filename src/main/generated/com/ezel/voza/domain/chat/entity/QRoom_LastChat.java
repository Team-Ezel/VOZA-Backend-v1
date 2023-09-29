package com.ezel.voza.domain.chat.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoom_LastChat is a Querydsl query type for LastChat
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRoom_LastChat extends BeanPath<Room.LastChat> {

    private static final long serialVersionUID = 1593351915L;

    public static final QRoom_LastChat lastChat1 = new QRoom_LastChat("lastChat1");

    public final StringPath lastChat = createString("lastChat");

    public final DateTimePath<java.time.LocalDateTime> lastSendAt = createDateTime("lastSendAt", java.time.LocalDateTime.class);

    public QRoom_LastChat(String variable) {
        super(Room.LastChat.class, forVariable(variable));
    }

    public QRoom_LastChat(Path<? extends Room.LastChat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoom_LastChat(PathMetadata metadata) {
        super(Room.LastChat.class, metadata);
    }

}

