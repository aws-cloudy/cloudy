package com.s207.cloudy.domain.learning.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QService is a Querydsl query type for Service
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QService extends EntityPathBase<Service> {

    private static final long serialVersionUID = -968819073L;

    public static final QService service = new QService("service");

    public final StringPath desc = createString("desc");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath type = createString("type");

    public QService(String variable) {
        super(Service.class, forVariable(variable));
    }

    public QService(Path<? extends Service> path) {
        super(path.getType(), path.getMetadata());
    }

    public QService(PathMetadata metadata) {
        super(Service.class, metadata);
    }

}

