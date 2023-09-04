package com.ssafy.chartflow.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1164285060L;

    public static final QUser user = new QUser("user");

    public final NumberPath<Long> budget = createNumber("budget", Long.class);

    public final BooleanPath cancel = createBoolean("cancel");

    public final NumberPath<Integer> coin = createNumber("coin", Integer.class);

    public final StringPath email = createString("email");

    public final ListPath<com.ssafy.chartflow.emblem.entity.UserEmblem, com.ssafy.chartflow.emblem.entity.QUserEmblem> emblems = this.<com.ssafy.chartflow.emblem.entity.UserEmblem, com.ssafy.chartflow.emblem.entity.QUserEmblem>createList("emblems", com.ssafy.chartflow.emblem.entity.UserEmblem.class, com.ssafy.chartflow.emblem.entity.QUserEmblem.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final NumberPath<Short> social = createNumber("social", Short.class);

    public final ListPath<com.ssafy.chartflow.board.entity.Article, SimplePath<com.ssafy.chartflow.board.entity.Article>> userArticle = this.<com.ssafy.chartflow.board.entity.Article, SimplePath<com.ssafy.chartflow.board.entity.Article>>createList("userArticle", com.ssafy.chartflow.board.entity.Article.class, SimplePath.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.chartflow.board.entity.Comments, SimplePath<com.ssafy.chartflow.board.entity.Comments>> userComments = this.<com.ssafy.chartflow.board.entity.Comments, SimplePath<com.ssafy.chartflow.board.entity.Comments>>createList("userComments", com.ssafy.chartflow.board.entity.Comments.class, SimplePath.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.chartflow.game.entity.GameHistory, com.ssafy.chartflow.game.entity.QGameHistory> userGameHistories = this.<com.ssafy.chartflow.game.entity.GameHistory, com.ssafy.chartflow.game.entity.QGameHistory>createList("userGameHistories", com.ssafy.chartflow.game.entity.GameHistory.class, com.ssafy.chartflow.game.entity.QGameHistory.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.chartflow.board.entity.Likes, SimplePath<com.ssafy.chartflow.board.entity.Likes>> userLikes = this.<com.ssafy.chartflow.board.entity.Likes, SimplePath<com.ssafy.chartflow.board.entity.Likes>>createList("userLikes", com.ssafy.chartflow.board.entity.Likes.class, SimplePath.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.chartflow.board.entity.ReComments, SimplePath<com.ssafy.chartflow.board.entity.ReComments>> userRecomments = this.<com.ssafy.chartflow.board.entity.ReComments, SimplePath<com.ssafy.chartflow.board.entity.ReComments>>createList("userRecomments", com.ssafy.chartflow.board.entity.ReComments.class, SimplePath.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

