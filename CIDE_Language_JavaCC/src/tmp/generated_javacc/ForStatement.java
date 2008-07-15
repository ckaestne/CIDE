package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ForStatement extends GenASTNode {
  public ForStatement(ForStatementInternal forStatementInternal, Statement statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ForStatementInternal>("forStatementInternal", forStatementInternal),
      new PropertyOne<Statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public ForStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ForStatement(cloneProperties(),firstToken,lastToken);
  }
  public ForStatementInternal getForStatementInternal() {
    return ((PropertyOne<ForStatementInternal>)getProperty("forStatementInternal")).getValue();
  }
  public Statement getStatement() {
    return ((PropertyOne<Statement>)getProperty("statement")).getValue();
  }
}
