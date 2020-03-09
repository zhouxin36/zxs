Create table If Not Exists Person
(
    Id    int,
    Email varchar(255)
);
Truncate table Person;
insert into Person (Id, Email)
values ('1', 'john@example.com ');
insert into Person (Id, Email)
values ('2', 'bob@example.com');
insert into Person (Id, Email)
values ('3', 'john@example.com');

DELETE p1
FROM Person p1,
     Person p2
WHERE p1.Email = p2.Email
  AND p1.Id > p2.Id;

DELETE
FROM Person
WHERE Id NOT IN
      (SELECT *
       FROM (
                SELECT MIN(Id)
                FROM Person
                GROUP BY Email) as p);