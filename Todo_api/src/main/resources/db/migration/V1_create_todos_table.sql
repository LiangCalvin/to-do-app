create table todos (
    id bigserial primary key,
    name text,
    completed boolean not null default false
)