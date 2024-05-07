package com.s207.cloudy.domain.learning.entity.embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLearningJobPK is a Querydsl query type for LearningJobPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QLearningJobPK extends BeanPath<LearningJobPK> {

    private static final long serialVersionUID = 1064576076L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLearningJobPK learningJobPK = new QLearningJobPK("learningJobPK");

    public final com.s207.cloudy.domain.learning.entity.QJob job;

    public final com.s207.cloudy.domain.learning.entity.QLearning learning;

    public QLearningJobPK(String variable) {
        this(LearningJobPK.class, forVariable(variable), INITS);
    }

    public QLearningJobPK(Path<? extends LearningJobPK> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLearningJobPK(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLearningJobPK(PathMetadata metadata, PathInits inits) {
        this(LearningJobPK.class, metadata, inits);
    }

    public QLearningJobPK(Class<? extends LearningJobPK> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.job = inits.isInitialized("job") ? new com.s207.cloudy.domain.learning.entity.QJob(forProperty("job")) : null;
        this.learning = inits.isInitialized("learning") ? new com.s207.cloudy.domain.learning.entity.QLearning(forProperty("learning")) : null;
    }

}

