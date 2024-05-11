package com.s207.cloudy.domain.learning.domain.embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLearningServicePK is a Querydsl query type for LearningServicePK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QLearningServicePK extends BeanPath<LearningServicePK> {

    private static final long serialVersionUID = 496920388L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLearningServicePK learningServicePK = new QLearningServicePK("learningServicePK");

    public final com.s207.cloudy.domain.learning.domain.QLearning learning;

    public final com.s207.cloudy.domain.learning.domain.QService service;

    public QLearningServicePK(String variable) {
        this(LearningServicePK.class, forVariable(variable), INITS);
    }

    public QLearningServicePK(Path<? extends LearningServicePK> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLearningServicePK(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLearningServicePK(PathMetadata metadata, PathInits inits) {
        this(LearningServicePK.class, metadata, inits);
    }

    public QLearningServicePK(Class<? extends LearningServicePK> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.learning = inits.isInitialized("learning") ? new com.s207.cloudy.domain.learning.domain.QLearning(forProperty("learning")) : null;
        this.service = inits.isInitialized("service") ? new com.s207.cloudy.domain.learning.domain.QService(forProperty("service")) : null;
    }

}

