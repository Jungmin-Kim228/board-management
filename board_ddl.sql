use nhn_academy_10;

drop table Users;
drop table Posts;
drop table Comments;
drop table Files;
drop table Likes;
drop table Reposts;

create table Users (
	user_no int not null auto_increment,
    user_id varchar(20) not null,
    user_pw varchar(20) not null,
    check_admin boolean not null,
    primary key(user_no)
);
create table Posts (
	post_no int not null auto_increment,
    user_no int not null,
    post_title varchar(20) not null,
    post_content varchar(100) not null,
    post_write_datetime datetime not null,
    post_modify_datetime datetime null,
    post_check_hide boolean not null,
    post_hits int not null,
    file_name varchar(20) not null,
    file_data longblob not null,
    primary key(post_no)
);

create table Reposts (
	post_no int not null,
    parent_repost_no int null,
    primary key(post_no)
);

create table Comments (
	comment_no int not null auto_increment,
    post_no int not null,
    user_no int not null,
    comment_content varchar(50) not null,
    primary key(comment_no)
);

create table Likes (
	like_no int not null auto_increment,
    post_no int not null,
    user_no int not null,
    primary key(like_no)
);