/***********************************************************************
* PostgreSQL : Database - rapid4j
* Created by Mignet
**********************************************************************/
-- Table: tsys_user
DROP SEQUENCE IF EXISTS seq_user_id CASCADE ;
CREATE SEQUENCE seq_user_id;

DROP TABLE IF EXISTS tsys_user;
CREATE TABLE tsys_user
(
  id integer NOT NULL DEFAULT nextval('seq_user_id'), -- 用户id
  username character varying(50), -- 用户名
  password character(64), -- 密码
  state character varying(32), -- 状态
  create_time timestamp without time zone, -- 创建时间
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tsys_user
  OWNER TO postgres;
COMMENT ON TABLE tsys_user
  IS '用户表';
COMMENT ON COLUMN tsys_user.id IS '用户id';
COMMENT ON COLUMN tsys_user.username IS '用户名';
COMMENT ON COLUMN tsys_user.password IS '密码';
COMMENT ON COLUMN tsys_user.state IS '状态';
COMMENT ON COLUMN tsys_user.create_time IS '创建时间';

ALTER SEQUENCE seq_user_id OWNED BY tsys_user.id;

/*Data for the table `user` */
INSERT INTO "tsys_user" (username,password,state,create_time)VALUES ('kermit', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL, '2014-7-17 12:59:08');

-- Table: tsys_role
DROP SEQUENCE IF EXISTS seq_role_id CASCADE ;
CREATE SEQUENCE seq_role_id;

DROP TABLE IF EXISTS tsys_role;
CREATE TABLE tsys_role
(
  id integer NOT NULL DEFAULT nextval('seq_role_id'), -- 角色id
  role_name character varying(32), -- 角色名
  role_sign character varying(128), -- 角色标识,程序中判断使用,如"admin"
  description character varying(256), -- 角色描述,UI界面显示使用
  CONSTRAINT role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tsys_role
  OWNER TO postgres;
COMMENT ON TABLE tsys_role
  IS '角色表';
COMMENT ON COLUMN tsys_role.id IS '角色id';
COMMENT ON COLUMN tsys_role.role_name IS '角色名';
COMMENT ON COLUMN tsys_role.role_sign IS '角色标识,程序中判断使用,如"admin"';
COMMENT ON COLUMN tsys_role.description IS '角色描述,UI界面显示使用';

ALTER SEQUENCE seq_role_id OWNED BY tsys_role.id;

/*Data for the table role */
INSERT INTO "tsys_role" (role_name,role_sign,description)VALUES ('admin', 'admin','管理员');
-- Table: tsys_permission
DROP SEQUENCE IF EXISTS seq_permission_id CASCADE ;
CREATE SEQUENCE seq_permission_id;

DROP TABLE IF EXISTS tsys_permission;
CREATE TABLE tsys_permission
(
  id integer NOT NULL DEFAULT nextval('seq_permission_id'), -- 权限id
  permission_name character varying(32), -- 权限名
  permission_sign character varying(128), -- 权限标识,程序中判断使用,如"user:create"
  description character varying(256), -- 权限描述,UI界面显示使用
  CONSTRAINT permission_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tsys_permission
  OWNER TO postgres;
COMMENT ON TABLE tsys_permission
  IS '权限表';
COMMENT ON COLUMN tsys_permission.id IS '权限id';
COMMENT ON COLUMN tsys_permission.permission_name IS '权限名';
COMMENT ON COLUMN tsys_permission.permission_sign IS '权限标识,程序中判断使用,如"user:create"';
COMMENT ON COLUMN tsys_permission.description IS '权限描述,UI界面显示使用';

ALTER SEQUENCE seq_permission_id OWNED BY tsys_permission.id;

/*Data for the table permission */
INSERT INTO "tsys_permission" (permission_name,permission_sign,description)VALUES ('用户新增', 'user:create','新增用户');

-- Table: tsys_user_role
DROP SEQUENCE IF EXISTS seq_user_role_id CASCADE ;
CREATE SEQUENCE seq_user_role_id;

DROP TABLE IF EXISTS tsys_user_role;
CREATE TABLE tsys_user_role
(
  id integer NOT NULL DEFAULT nextval('seq_user_role_id'), -- 表id
  user_id numeric(20,0), -- 用户id
  role_id numeric(20,0), -- 角色id
  CONSTRAINT user_role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tsys_user_role
  OWNER TO postgres;
COMMENT ON TABLE tsys_user_role
  IS '用户与角色关联表';
COMMENT ON COLUMN tsys_user_role.id IS '表id';
COMMENT ON COLUMN tsys_user_role.user_id IS '用户id';
COMMENT ON COLUMN tsys_user_role.role_id IS '角色id';

ALTER SEQUENCE seq_user_role_id OWNED BY tsys_user_role.id;

/*Data for the table role */
INSERT INTO tsys_user_role (user_id,role_id)VALUES (1,1);

-- Table: tsys_role_permission
DROP SEQUENCE IF EXISTS seq_role_permission_id CASCADE ;
CREATE SEQUENCE seq_role_permission_id;

DROP TABLE IF EXISTS tsys_role_permission;
CREATE TABLE tsys_role_permission
(
  id integer NOT NULL DEFAULT nextval('seq_role_permission_id'), -- 表id
  role_id numeric(20,0), -- 角色id
  permission_id numeric(20,0), -- 权限id
  CONSTRAINT role_permission_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tsys_role_permission
  OWNER TO postgres;
COMMENT ON TABLE tsys_role_permission
  IS '角色与权限关联表';
COMMENT ON COLUMN tsys_role_permission.id IS '表id';
COMMENT ON COLUMN tsys_role_permission.role_id IS '角色id';
COMMENT ON COLUMN tsys_role_permission.permission_id IS '权限id';

ALTER SEQUENCE seq_role_permission_id OWNED BY tsys_role_permission.id;

/*Data for the table role */
INSERT INTO tsys_role_permission (role_id,permission_id)VALUES (1,1);

--add a column//
ALTER TABLE tsys_user ADD COLUMN description character varying(256);
COMMENT ON COLUMN tsys_user.description IS '用户描述,UI界面显示使用';

