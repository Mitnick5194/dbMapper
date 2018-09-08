drop table if exit tb_user;
create table tb_user(
	id int(11) not null auto_increment,
	name varchar(32) not null,
	password varchar(32) not null ,
	nickName varchar(32) default null,
	synopsis varchar (128) default null comment '简介',
	sex ENUM('1','2','0') default '0' comment '1男2女0保密',
	phone char(11) default null,
	email varchar(32) default null,
	createTime timestamp not null comment '创建时间',
	lastActive timestamp not null comment '最后活跃时间',
	roleIds varchar(128) default null comment '权限id集，以逗号分隔',
	header varchar(128) default null comment '头像路径',
	mark int(11) default 0,
	primary key (id),
	unique key name(name) using btree,
	unique key phone(phone) using btree,
	unique key email(email) using btree
) engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

drop table if exit tb_label;
create table tb_label(
	id int(11) not null auto_increment,
	name varchar(32) not null,
	primary key (id),
	unique key name(name) using btree,
) engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

drop table if exit tb_blog;
create table tb_blog(
	id int(11) not null auto_increment,
	name varchar(32）default null,
	userId int(11) not null,
	primary key (id),
	unique key name(name) using btree
)engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHATSET=UTF8 COMMENT='博客';









