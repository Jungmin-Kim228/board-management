use nhn_academy_8;

drop table Users;
drop table Posts;
drop table Comments;
drop table Likes;
drop table Views;

create table if not exists Users (
	user_no int not null auto_increment,
    user_id varchar(20) not null,
    user_pw varchar(20) not null,
    check_admin boolean not null,
    primary key(user_no)
);
create table if not exists Posts (
	post_no int not null auto_increment,
    user_no int not null,
    post_title varchar(20) not null,
    post_content varchar(100) not null,
    post_write_datetime datetime not null,
    post_modify_datetime datetime null,
    post_check_hide boolean not null,
    file_name varchar(50) not null,
    file_data longblob not null,
    parent int not null,
    re_depth int not null,
    primary key(post_no)
);

create table if not exists Comments (
	comment_no int not null auto_increment,
    post_no int not null,
    user_no int not null,
    comment_content varchar(50) not null,
    primary key(comment_no, post_no)
);

create table if not exists Likes (
	like_no int not null auto_increment,
    post_no int not null,
    user_no int not null,
    primary key(like_no, post_no)
);

create table if not exists Views (
	post_no int not null,
	user_no int not null,
    primary key(post_no, user_no)
);
