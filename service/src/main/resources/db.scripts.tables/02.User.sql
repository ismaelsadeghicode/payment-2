begin
    execute immediate 'drop table user_topup';
    dbms_output.put_line('Table user_topup dropped');
exception
    when others then dbms_output.put_line('Table user_topup did not exist');
end;
/
begin
    execute immediate 'drop sequence user_topup_seq';
    dbms_output.put_line('Sequence user_topup_seq dropped');
exception
    when others then dbms_output.put_line('Sequence user_topup_seq did not exist');
end;
/

create table user_topup
(
    id           number(19) not null,
    created_date timestamp(6),
    password     varchar2(255 char),
    username     varchar2(255 char),
    constraint   user_topup_pk_id primary key (id);
);
/

create sequence user_topup_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/