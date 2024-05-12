package com.s207.cloudy.domain.learning.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJob is a Querydsl query type for Job
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJob extends EntityPathBase<Job> {

    private static final long serialVersionUID = 1997949063L;

    public static final QJob job = new QJob("job");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QJob(String variable) {
        super(Job.class, forVariable(variable));
    }

    public QJob(Path<? extends Job> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJob(PathMetadata metadata) {
        super(Job.class, metadata);
    }

}

