%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

extern int yylex();
void yyerror(const char *s) { fprintf(stderr, "Error: %s\n", s); }

%}

%union {
  int num;
  char* str;
}

%token INSERT INTO VALUES UPDATE SET UNSET REMOVE DELETE ERASE SELECT FROM WHERE PATCH ON CONFLICT DO NOTHING OBJECT UNION ALL DISTINCT JOIN LEFT RIGHT OUTER INNER CROSS UNNEST WITH RECURSIVE ORDINALITY ORDER BY GROUP HAVING LIMIT OFFSET INTERSECT EXCEPT OBJECTS AS ASC DESC ARRAY NUMBER IDENTIFIER STRING

%type <num> NUMBER
%type <str> IDENTIFIER STRING

%start transaction

%%

transaction
  : statement
  | transaction ';' statement
  ;

statement
  : select_query
  | insert_query
  | update_query
  | delete_query
  | erase_query
  ;

select_query
  : SELECT select_distinct_list FROM table_reference select_options
  ;

select_distinct_list
  : DISTINCT select_list
  | select_list
  ;

select_list
  : '*'
  | column_list
  ;

table_reference
  : IDENTIFIER table_alias
  | '(' VALUES values_list ')' table_alias
  | '(' OBJECTS objects_list ')' table_alias
  | '(' select_query ')' table_alias
  | table_reference join_type JOIN table_reference ON condition
  | UNNEST '(' array_literal ')' table_alias unnest_with_ordinality
  ;

join_type
  : LEFT OUTER
  | RIGHT OUTER
  | INNER
  | CROSS
  | /* empty, for standard join */
  ;

unnest_with_ordinality
  : WITH ORDINALITY
  | /* empty, if no ordinality */
  ;

table_alias
  : /* logic for table aliasing */
  ;

select_options
  : WHERE condition
  | ORDER BY order_list
  | GROUP BY column_list
  | HAVING condition
  | LIMIT num_expr
  | OFFSET num_expr
  | set_operation select_query
  | WITH with_query
  ;

set_operation
  : UNION
  | UNION ALL
  | INTERSECT
  | EXCEPT
  ;

with_query
  : WITH RECURSIVE cte_list
  | WITH cte_list
  ;

cte_list
  : cte_definition
  | cte_list ',' cte_definition
  ;

cte_definition
  : IDENTIFIER AS '(' select_query ')'
  ;

order_list
  : order_term
  | order_list ',' order_term
  ;

order_term
  : IDENTIFIER order_direction
  ;

order_direction
  : ASC
  | DESC
  ;

num_expr
  : NUMBER
  | IDENTIFIER
  ;

array_literal
  : '[' value_list_items ']'
  ;

array_constructor
  : ARRAY array_literal
  | ARRAY '(' value_list_items ')'

values_list
  : '(' value_list_items ')'
  | values_list ',' '(' value_list_items ')'
  ;

value_list_items
  : value
  | value_list_items ',' value
  ;

value
  : NUMBER
  | STRING
  | object_literal
  | object_constructor
  | array_literal
  | array_constructor
  ;

object_constructor
  : OBJECT '(' object_args ')'
  ;

object_args
  : object_arg
  | object_args ',' object_arg
  ;

object_arg
  : IDENTIFIER ':' value
  ;

object_literal
  : '{' object_args '}'
  ;

objects_list
  : object_literal
  | objects_list ',' object_literal
  ;

insert_query
  : INSERT INTO IDENTIFIER '(' column_list ')' VALUES values_list
  | INSERT INTO IDENTIFIER object_literal
  | INSERT INTO IDENTIFIER select_query on_conflict
  ;

on_conflict
  : ON CONFLICT '(' column_list ')' DO conflict_action
  | /* empty, if no conflict action */
  ;

conflict_action
  : UPDATE SET update_set
  | NOTHING
  ;

update_query
  : UPDATE IDENTIFIER SET update_set WHERE condition
  | UPDATE IDENTIFIER PATCH object_literal WHERE condition
  | UPDATE IDENTIFIER UNSET IDENTIFIER WHERE condition
  | UPDATE IDENTIFIER REMOVE IDENTIFIER WHERE condition
  ;

update_set
  : IDENTIFIER '=' value
  | update_set ',' IDENTIFIER '=' value
  ;

delete_query
  : DELETE FROM IDENTIFIER WHERE condition
  | DELETE FROM IDENTIFIER
  ;

erase_query
  : ERASE FROM IDENTIFIER WHERE condition
  ;

column_list
  : IDENTIFIER
  | column_list ',' IDENTIFIER
  ;

condition
  : /* Your condition parsing logic */
  ;

%%
