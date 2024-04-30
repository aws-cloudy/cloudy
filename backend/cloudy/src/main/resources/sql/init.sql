#to-do 인덱스 추가해야한다.

CREATE TABLE `roadmap` (
                           `id`	int	NOT NULL,
                           `title`	VARCHAR(255)	NULL,
                           `desc`	VARCHAR(255)	NULL,
                           `thumbnail`	VARCHAR(255)	NULL,
                           `service`	VARCHAR(255)	NULL,
                           `job`	VARCHAR(255)	NULL,
                           `summary`	VARCHAR(255)	NULL
);



CREATE TABLE `roadmap_comment` (
                                   `id`	int	NOT NULL,
                                   `roadmap_id`	int	NOT NULL,
                                   `member_id`	VARCHAR(255)	NOT NULL,
                                   `desc`	VARCHAR(255)	NULL
);

CREATE TABLE `member_roadmap` (
                                  `id`	int	NOT NULL,
                                  `member_id`	VARCHAR(255)	NOT NULL,
                                  `roadmap_id`	int	NOT NULL
);

CREATE TABLE `learning_roadmap` (
                                    `id`	int	NOT NULL,
                                    `roadmap_id`	int	NOT NULL,
                                    `learning_id`	int	NOT NULL,
                                    `rank`	int	NULL
);


create TABLE `member` (
                          `id` VARCHAR(255) NOT  NULL,
                          `name` VARCHAR(50) NOT NULL
    );



ALTER TABLE `roadmap_comment` ADD CONSTRAINT `PK_ROADMAP_COMMENT` PRIMARY KEY (
                                                                               `id`
    );



ALTER TABLE `member_roadmap` ADD CONSTRAINT `PK_MEMBER_ROADMAP` PRIMARY KEY (
                                                                             `id`
    );

ALTER TABLE `learning_roadmap` ADD CONSTRAINT `PK_LEARNING_ROADMAP` PRIMARY KEY (
                                                                                 `id`
    );
