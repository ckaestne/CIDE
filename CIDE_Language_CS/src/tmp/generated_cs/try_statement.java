package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class try_statement extends GenASTNode {
  public try_statement(block block, try_statement_clauses try_statement_clauses, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<block>("block", block),
      new PropertyOne<try_statement_clauses>("try_statement_clauses", try_statement_clauses)
    }, firstToken, lastToken);
  }
  public try_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new try_statement(cloneProperties(),firstToken,lastToken);
  }
  public block getBlock() {
    return ((PropertyOne<block>)getProperty("block")).getValue();
  }
  public try_statement_clauses getTry_statement_clauses() {
    return ((PropertyOne<try_statement_clauses>)getProperty("try_statement_clauses")).getValue();
  }
}
