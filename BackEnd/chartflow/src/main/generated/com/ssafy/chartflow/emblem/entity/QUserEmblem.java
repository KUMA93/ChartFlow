package com.ssafy.chartflow.emblem.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEmblem is a Querydsl query type for UserEmblem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEmblem extends EntityPathBase<UserEmblem> {

    private static final long serialVersionUID = -1383732987L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEmblem userEmblem = new QUserEmblem("userEmblem");

    public final QEmblem emblem;

    public final com.ssafy.chartflow.user.entity.QUser user;

    public final NumberPath<Long> userEmblemId = createNumber("userEmblemId", Long.class);

    public QUserEmblem(String variable) {
        this(UserEmblem.class, forVariable(variable), INITS);
    }

    public QUserEmblem(Path<? extends UserEmblem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserEmblem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserEmblem(PathMetadata metadata, PathInits inits) {
        this(UserEmblem.class, metadata, inits);
    }

    public QUserEmblem(Class<? extends UserEmblem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.emblem = inits.isInitialized("emblem") ? new QEmblem(forProperty("emblem")) : null;
        this.user = inits.isInitialized("user") ? new com.ssafy.chartflow.user.entity.QUser(forProperty("user")) : null;
    }

}

