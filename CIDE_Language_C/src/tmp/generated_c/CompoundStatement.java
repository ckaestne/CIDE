package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CompoundStatement extends GenASTNode {
  public CompoundStatement(DeclarationList declarationList, StatementList statementList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<DeclarationList>("declarationList", declarationList),
      new PropertyZeroOrOne<StatementList>("statementList", statementList)
    }, firstToken, lastToken);
  }
  public CompoundStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CompoundStatement(cloneProperties(),firstToken,lastToken);
  }
  public DeclarationList getDeclarationList() {
    return ((PropertyZeroOrOne<DeclarationList>)getProperty("declarationList")).getValue();
  }
  public StatementList getStatementList() {
    return ((PropertyZeroOrOne<StatementList>)getProperty("statementList")).getValue();
  }
}
