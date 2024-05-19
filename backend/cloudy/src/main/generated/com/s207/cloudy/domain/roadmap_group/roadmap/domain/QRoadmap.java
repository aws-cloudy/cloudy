package com.s207.cloudy.domain.roadmap_group.roadmap.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoadmap is a Querydsl query type for Roadmap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoadmap extends EntityPathBase<Roadmap> {

    private static final long serialVersionUID = -2020056495L;

    public static final QRoadmap roadmap = new QRoadmap("roadmap");

    public final StringPath desc = createString("desc");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath job = createString("job");

    public final StringPath service = createString("service");

    public final StringPath summary = createString("summary");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public QRoadmap(String variable) {
        super(Roadmap.class, forVariable(variable));
    }

    public QRoadmap(Path<? extends Roadmap> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoadmap(PathMetadata metadata) {
        super(Roadmap.class, metadata);
    }

}

