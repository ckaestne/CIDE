package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class try_statement_clauses2 extends try_statement_clauses {
  public try_statement_clauses2(finally_clause finally_clause1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<finally_clause>("finally_clause1", finally_clause1)
    }, firstToken, lastToken);
  }
  public try_statement_clauses2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new try_statement_clauses2(cloneProperties(),firstToken,lastToken);
  }
  public finally_clause getFinally_clause1() {
    return ((PropertyOne<finally_clause>)getProperty("finally_clause1")).getValue();
  }
}
