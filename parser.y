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

%token INSERT INTO VALUES UPDATE SET UNSET REMOVE DELETE ERASE SELECT FROM WHERE PATCH ON CONFLICT DO NOTHING OBJECT UNION ALL DISTINCT JOIN LEFT RIGHT OUTER INNER CROSS UNNEST WITH RECURSIVE ORDINALITY ORDER BY GROUP HAVING LIMIT OFFSET INTERSECT EXCEPT OBJECTS NUMBER IDENTIFIER STRING AS ASC DESC
%type <str> IDENTIFIER STRING

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
  : SELECT select_list FROM table_reference select_options ';'
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
  | table_reference JOIN table_reference ON condition
  | UNNEST '(' array_expr ')' table_alias
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

array_expr: /* logic to parse array expression */
          ;

values_list: /* logic to parse VALUES list */
           ;

objects_list: /* logic to parse OBJECTS list */
            ;

insert_query: /* existing logic for INSERT */
            ;

update_query: /* existing logic for UPDATE */
            ;

delete_query: /* existing logic for DELETE */
            ;

erase_query: /* existing logic for ERASE */
           ;

column_list: IDENTIFIER
           | column_list ',' IDENTIFIER
           ;

condition: /* Your condition parsing logic */
          ;

%%
