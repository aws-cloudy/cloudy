package com.s207.cloudy.domain.learning.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLearning is a Querydsl query type for Learning
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLearning extends EntityPathBase<Learning> {

    private static final long serialVersionUID = 226374804L;

    public static final QLearning learning = new QLearning("learning");

    public final StringPath desc = createString("desc");

    public final StringPath difficulty = createString("difficulty");

    public final StringPath duration = createString("duration");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath link = createString("link");

    public final StringPath summary = createString("summary");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final StringPath type = createString("type");

    public QLearning(String variable) {
        super(Learning.class, forVariable(variable));
    }

    public QLearning(Path<? extends Learning> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLearning(PathMetadata metadata) {
        super(Learning.class, metadata);
    }

}

