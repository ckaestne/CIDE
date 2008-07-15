package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class catch_clause extends GenASTNode {
  public catch_clause(catch_clauseEnd catch_clauseEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<catch_clauseEnd>("catch_clauseEnd", catch_clauseEnd)
    }, firstToken, lastToken);
  }
  public catch_clause(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new catch_clause(cloneProperties(),firstToken,lastToken);
  }
  public catch_clauseEnd getCatch_clauseEnd() {
    return ((PropertyOne<catch_clauseEnd>)getProperty("catch_clauseEnd")).getValue();
  }
}
