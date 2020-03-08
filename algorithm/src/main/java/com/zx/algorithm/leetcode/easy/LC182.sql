Create table If Not Exists Person (Id int, Email varchar(255));
Truncate table Person;
insert into Person (Id, Email) values ('1', 'a@b.com');
insert into Person (Id, Email) values ('2', 'c@d.com');
insert into Person (Id, Email) values ('3', 'a@b.com');

select distinct b.Email from Person a inner join Person b on a.Email = b.Email where a.Id != b.Id;
select Email from Person group by Email having count(1) > 1;