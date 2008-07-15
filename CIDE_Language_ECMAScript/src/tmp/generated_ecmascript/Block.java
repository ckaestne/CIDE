package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Block extends GenASTNode {
  public Block(StatementList statementList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<StatementList>("statementList", statementList)
    }, firstToken, lastToken);
  }
  public Block(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Block(cloneProperties(),firstToken,lastToken);
  }
  public StatementList getStatementList() {
    return ((PropertyZeroOrOne<StatementList>)getProperty("statementList")).getValue();
  }
}
