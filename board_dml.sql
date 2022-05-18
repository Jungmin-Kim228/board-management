select * from Users;
select * from Posts;
select * from Likes;
select * from Reposts;
select * from Comments;
select * from Files;

insert into Users (user_id, user_pw, check_admin)
values ('user', 'useruser', false);
insert into Users (user_id, user_pw, check_admin)
values ('admin', 'adminadmin', true);

insert into Posts (user_no, post_title, post_content, post_write_datetime, post_check_hide, post_hits)
values (1, 'title1', 'first post', now(), false, 0);
insert into Posts (user_no, post_title, post_content, post_write_datetime, post_check_hide, post_hits)
values (1, 'title2', 'second post', now(), false, 0);

insert into Reposts (post_no, parent_repost_no)
values (1, null);
insert into Reposts (post_no, parent_repost_no)
values (2, 1);
insert into Reposts (post_no, parent_repost_no)
values (5, 2);
insert into Reposts (post_no, parent_repost_no)
values (6, 2);
insert into Reposts (post_no, parent_repost_no)
values (8, 6);
insert into Reposts (post_no, parent_repost_no)
values (7, 2);
insert into Reposts (post_no, parent_repost_no)
values (3, 1);
insert into Reposts (post_no, parent_repost_no)
values (9, 1);
insert into Reposts (post_no, parent_repost_no)
values (4, null);

