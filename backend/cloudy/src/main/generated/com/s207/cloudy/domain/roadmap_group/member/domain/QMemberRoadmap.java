package com.s207.cloudy.domain.roadmap_group.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberRoadmap is a Querydsl query type for MemberRoadmap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRoadmap extends EntityPathBase<MemberRoadmap> {

    private static final long serialVersionUID = 535599103L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberRoadmap memberRoadmap = new QMemberRoadmap("memberRoadmap");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath memberId = createString("memberId");

    public final com.s207.cloudy.domain.roadmap_group.roadmap.domain.QRoadmap roadmap;

    public QMemberRoadmap(String variable) {
        this(MemberRoadmap.class, forVariable(variable), INITS);
    }

    public QMemberRoadmap(Path<? extends MemberRoadmap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberRoadmap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberRoadmap(PathMetadata metadata, PathInits inits) {
        this(MemberRoadmap.class, metadata, inits);
    }

    public QMemberRoadmap(Class<? extends MemberRoadmap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.roadmap = inits.isInitialized("roadmap") ? new com.s207.cloudy.domain.roadmap_group.roadmap.domain.QRoadmap(forProperty("roadmap")) : null;
    }

}

