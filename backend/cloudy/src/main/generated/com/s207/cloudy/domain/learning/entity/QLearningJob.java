package com.s207.cloudy.domain.learning.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLearningJob is a Querydsl query type for LearningJob
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLearningJob extends EntityPathBase<LearningJob> {

    private static final long serialVersionUID = 833205897L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLearningJob learningJob = new QLearningJob("learningJob");

    public final com.s207.cloudy.domain.learning.domain.embedded.QLearningJobPK learningJobPK;

    public QLearningJob(String variable) {
        this(LearningJob.class, forVariable(variable), INITS);
    }

    public QLearningJob(Path<? extends LearningJob> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLearningJob(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLearningJob(PathMetadata metadata, PathInits inits) {
        this(LearningJob.class, metadata, inits);
    }

    public QLearningJob(Class<? extends LearningJob> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.learningJobPK = inits.isInitialized("learningJobPK") ? new com.s207.cloudy.domain.learning.domain.embedded.QLearningJobPK(forProperty("learningJobPK"), inits.get("learningJobPK")) : null;
    }

}

