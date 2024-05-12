package com.s207.cloudy.domain.learning.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLearningService is a Querydsl query type for LearningService
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLearningService extends EntityPathBase<LearningService> {

    private static final long serialVersionUID = 227122049L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLearningService learningService = new QLearningService("learningService");

    public final com.s207.cloudy.domain.learning.domain.embedded.QLearningServicePK learningServicePK;

    public QLearningService(String variable) {
        this(LearningService.class, forVariable(variable), INITS);
    }

    public QLearningService(Path<? extends LearningService> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLearningService(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLearningService(PathMetadata metadata, PathInits inits) {
        this(LearningService.class, metadata, inits);
    }

    public QLearningService(Class<? extends LearningService> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.learningServicePK = inits.isInitialized("learningServicePK") ? new com.s207.cloudy.domain.learning.domain.embedded.QLearningServicePK(forProperty("learningServicePK"), inits.get("learningServicePK")) : null;
    }

}

