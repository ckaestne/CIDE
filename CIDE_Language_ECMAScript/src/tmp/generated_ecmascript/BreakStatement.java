package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BreakStatement extends GenASTNode {
  public BreakStatement(Identifier identifier, ASTTextNode text334, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Identifier>("identifier", identifier),
      new PropertyZeroOrOne<ASTTextNode>("text334", text334)
    }, firstToken, lastToken);
  }
  public BreakStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BreakStatement(cloneProperties(),firstToken,lastToken);
  }
  public Identifier getIdentifier() {
    return ((PropertyZeroOrOne<Identifier>)getProperty("identifier")).getValue();
  }
  public ASTTextNode getText334() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text334")).getValue();
  }
}
