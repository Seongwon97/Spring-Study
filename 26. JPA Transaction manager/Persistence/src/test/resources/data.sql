/*
현재 실습은 in-memory에서 동작하는 H2 DB로 실습을 한다.
그래서 프로그램을 종료할때마다 DB가 초기화되는데 초기에 DB에 값을
넣어주고 싶다면 resources안에 data.sql파일을 생성하여 query문으로 넣어준다.
 */

-- pk의 값이 중복이 되면 save를 할 때 문제가 발생할 수 있어서 H2를 활용한 예제에서는 그러한 일을 방지하기 위해 pk값을 먼저 알린다.
-- 추후 mySql을 할 때는 다른 방법을 사용
/* 버전이 업데이트되면서 사용 코드 형태가 바뀌었는지 실행이 안됨
call next value for hibernate_sequence:
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (1, 'martin', 'martin@gmail.com', now(), now());

call next value for hibernate_sequence:
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (2, 'James', 'James@gmail.com', now(), now());

call next value for hibernate_sequence:
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (3, 'sophia', 'sophia@gmail.com', now(), now());

call next value for hibernate_sequence:
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (4, 'Lisa', 'Lisa@gmail.com', now(), now());

call next value for hibernate_sequence:
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (5, 'martin', 'martin123@gmail.com', now(), now());
*/


insert into user (id, name, email, created_at, updated_at) values (1, 'martin', 'martin@gmail.com', now(), now());
insert into user (id, name, email, created_at, updated_at) values (2, 'James', 'James@gmail.com', now(), now());
insert into user (id, name, email, created_at, updated_at) values (3, 'sophia', 'sophia@gmail.com', now(), now());
insert into user (id, name, email, created_at, updated_at) values (4, 'Lisa', 'Lisa@gmail.com', now(), now());
insert into user (id, name, email, created_at, updated_at) values (5, 'martin', 'martin123@gmail.com', now(), now());

insert into publisher(id, name) values (1, "Seongwon.Ing");

insert into book(`id`, `name`, `publisher_id`, `deleted`) values (1, "Good life", 1, false );

insert into book(`id`, `name`, `publisher_id`, `deleted`) values (2, "Spring Boot", 1, false );

insert into book(`id`, `name`, `publisher_id`, `deleted`) values (3, "Spring Boot2", 1, true);

/*
  aplicaton.yml에 다음 코드를 추가해야함
  jpa:
    defer-datasource-initialization: true
*/