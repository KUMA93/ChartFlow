package com.ssafy.chartflow.emblem.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmblem is a Querydsl query type for Emblem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmblem extends EntityPathBase<Emblem> {

    private static final long serialVersionUID = -1375308454L;

    public static final QEmblem emblem = new QEmblem("emblem");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<UserEmblem, QUserEmblem> userEmblems = this.<UserEmblem, QUserEmblem>createList("userEmblems", UserEmblem.class, QUserEmblem.class, PathInits.DIRECT2);

    public QEmblem(String variable) {
        super(Emblem.class, forVariable(variable));
    }

    public QEmblem(Path<? extends Emblem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmblem(PathMetadata metadata) {
        super(Emblem.class, metadata);
    }

}

