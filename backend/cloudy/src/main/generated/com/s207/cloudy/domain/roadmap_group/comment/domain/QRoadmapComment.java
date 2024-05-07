package com.s207.cloudy.domain.roadmap_group.comment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoadmapComment is a Querydsl query type for RoadmapComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoadmapComment extends EntityPathBase<RoadmapComment> {

    private static final long serialVersionUID = 315467441L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoadmapComment roadmapComment = new QRoadmapComment("roadmapComment");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.s207.cloudy.domain.members.entity.QMember member;

    public final DateTimePath<java.time.LocalDateTime> regAt = createDateTime("regAt", java.time.LocalDateTime.class);

    public final com.s207.cloudy.domain.roadmap_group.roadmap.domain.QRoadmap roadmap;

    public QRoadmapComment(String variable) {
        this(RoadmapComment.class, forVariable(variable), INITS);
    }

    public QRoadmapComment(Path<? extends RoadmapComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoadmapComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoadmapComment(PathMetadata metadata, PathInits inits) {
        this(RoadmapComment.class, metadata, inits);
    }

    public QRoadmapComment(Class<? extends RoadmapComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.s207.cloudy.domain.members.entity.QMember(forProperty("member")) : null;
        this.roadmap = inits.isInitialized("roadmap") ? new com.s207.cloudy.domain.roadmap_group.roadmap.domain.QRoadmap(forProperty("roadmap")) : null;
    }

}

