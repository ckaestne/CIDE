package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ContinueStatement extends GenASTNode {
  public ContinueStatement(Identifier identifier, ASTTextNode text333, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Identifier>("identifier", identifier),
      new PropertyZeroOrOne<ASTTextNode>("text333", text333)
    }, firstToken, lastToken);
  }
  public ContinueStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ContinueStatement(cloneProperties(),firstToken,lastToken);
  }
  public Identifier getIdentifier() {
    return ((PropertyZeroOrOne<Identifier>)getProperty("identifier")).getValue();
  }
  public ASTTextNode getText333() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text333")).getValue();
  }
}
