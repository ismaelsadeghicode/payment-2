begin
    execute immediate 'drop table direct_topup';
    dbms_output.put_line('Table direct_topup dropped');
exception
    when others then dbms_output.put_line('Table direct_topup did not exist');
end;
/
begin
    execute immediate 'drop sequence direct_topup_seq';
    dbms_output.put_line('Sequence direct_topup_seq dropped');
exception
    when others then dbms_output.put_line('Sequence direct_topup_seq did not exist');
end;
/

create table direct_topup
(
    id                      number(19) not null,
    created_date            timestamp(6),
    amount                  number(10),
    error_code              varchar2(255 char),
    message_id              varchar2(255 char),
    method                  number(10),
    postage_date            timestamp(6),
    request_date_time_topup number(19),
    request_date_topup      number(19),
    response_code_order     number(10),
    response_code_submit    number(10),
    res_num                 number(10),
    status                  number(1),
    subscriber_no           number(19),
    trace_no_topup          varchar2(255 char),
    constraint   direct_topup_pk_id primary key (id);
);
/

create sequence direct_topup_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/