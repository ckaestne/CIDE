package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class try_statement_clauses1 extends try_statement_clauses {
  public try_statement_clauses1(catch_clauses catch_clauses, finally_clause finally_clause, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<catch_clauses>("catch_clauses", catch_clauses),
      new PropertyZeroOrOne<finally_clause>("finally_clause", finally_clause)
    }, firstToken, lastToken);
  }
  public try_statement_clauses1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new try_statement_clauses1(cloneProperties(),firstToken,lastToken);
  }
  public catch_clauses getCatch_clauses() {
    return ((PropertyOne<catch_clauses>)getProperty("catch_clauses")).getValue();
  }
  public finally_clause getFinally_clause() {
    return ((PropertyZeroOrOne<finally_clause>)getProperty("finally_clause")).getValue();
  }
}
