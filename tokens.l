%{
#include "y.tab.h"
%}

%%
INSERT         { return INSERT; }
INTO           { return INTO; }
VALUES         { return VALUES; }
UPDATE         { return UPDATE; }
SET            { return SET; }
UNSET          { return UNSET; }
REMOVE         { return REMOVE; }
DELETE         { return DELETE; }
ERASE          { return ERASE; }
SELECT         { return SELECT; }
FROM           { return FROM; }
WHERE          { return WHERE; }
PATCH          { return PATCH; }
ON             { return ON; }
CONFLICT       { return CONFLICT; }
DO             { return DO; }
NOTHING        { return NOTHING; }
OBJECT         { return OBJECT; }
UNION          { return UNION; }
ALL            { return ALL; }
DISTINCT       { return DISTINCT; }
JOIN           { return JOIN; }
LEFT           { return LEFT; }
RIGHT          { return RIGHT; }
OUTER          { return OUTER; }
INNER          { return INNER; }
CROSS          { return CROSS; }
UNNEST         { return UNNEST; }
WITH           { return WITH; }
RECURSIVE      { return RECURSIVE; }
ORDINALITY     { return ORDINALITY; }
ORDER          { return ORDER; }
BY             { return BY; }
GROUP          { return GROUP; }
HAVING         { return HAVING; }
LIMIT          { return LIMIT; }
OFFSET         { return OFFSET; }
INTERSECT      { return INTERSECT; }
EXCEPT         { return EXCEPT; }
OBJECTS        { return OBJECTS; }
AS             { return AS; }
ASC            { return ASC; }
DESC           { return DESC; }
ARRAY          { return ARRAY; }
[0-9]+         { yylval.num = atoi(yytext); return NUMBER; }
[a-zA-Z_][a-zA-Z0-9_]*  { yylval.str = strdup(yytext); return IDENTIFIER; }
'[^']*'       { yylval.str = strdup(yytext); return STRING; }
[{}(),;.*]     { return *yytext; }
[ \t\n\r]+     { /* ignore whitespace */ }
.              { /* unrecognized characters */ }
%%
