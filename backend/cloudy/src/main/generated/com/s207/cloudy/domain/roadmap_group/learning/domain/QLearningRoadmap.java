package com.s207.cloudy.domain.roadmap_group.learning.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLearningRoadmap is a Querydsl query type for LearningRoadmap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLearningRoadmap extends EntityPathBase<LearningRoadmap> {

    private static final long serialVersionUID = 1414359543L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLearningRoadmap learningRoadmap = new QLearningRoadmap("learningRoadmap");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.s207.cloudy.domain.learning.domain.QLearning learning;

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    public final com.s207.cloudy.domain.roadmap_group.roadmap.domain.QRoadmap roadmap;

    public QLearningRoadmap(String variable) {
        this(LearningRoadmap.class, forVariable(variable), INITS);
    }

    public QLearningRoadmap(Path<? extends LearningRoadmap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLearningRoadmap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLearningRoadmap(PathMetadata metadata, PathInits inits) {
        this(LearningRoadmap.class, metadata, inits);
    }

    public QLearningRoadmap(Class<? extends LearningRoadmap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.learning = inits.isInitialized("learning") ? new com.s207.cloudy.domain.learning.domain.QLearning(forProperty("learning")) : null;
        this.roadmap = inits.isInitialized("roadmap") ? new com.s207.cloudy.domain.roadmap_group.roadmap.domain.QRoadmap(forProperty("roadmap")) : null;
    }

}

