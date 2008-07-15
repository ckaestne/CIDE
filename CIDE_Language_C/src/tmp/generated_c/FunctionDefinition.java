package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FunctionDefinition extends GenASTNode {
  public FunctionDefinition(DeclarationSpecifiers declarationSpecifiers, Declarator declarator, DeclarationList declarationList, CompoundStatement compoundStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<DeclarationSpecifiers>("declarationSpecifiers", declarationSpecifiers),
      new PropertyOne<Declarator>("declarator", declarator),
      new PropertyZeroOrOne<DeclarationList>("declarationList", declarationList),
      new PropertyOne<CompoundStatement>("compoundStatement", compoundStatement)
    }, firstToken, lastToken);
  }
  public FunctionDefinition(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FunctionDefinition(cloneProperties(),firstToken,lastToken);
  }
  public DeclarationSpecifiers getDeclarationSpecifiers() {
    return ((PropertyZeroOrOne<DeclarationSpecifiers>)getProperty("declarationSpecifiers")).getValue();
  }
  public Declarator getDeclarator() {
    return ((PropertyOne<Declarator>)getProperty("declarator")).getValue();
  }
  public DeclarationList getDeclarationList() {
    return ((PropertyZeroOrOne<DeclarationList>)getProperty("declarationList")).getValue();
  }
  public CompoundStatement getCompoundStatement() {
    return ((PropertyOne<CompoundStatement>)getProperty("compoundStatement")).getValue();
  }
}
