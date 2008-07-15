package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Identifier extends GenASTNode {
  public Identifier(ASTStringNode identifier_name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier_name", identifier_name)
    }, firstToken, lastToken);
  }
  public Identifier(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Identifier(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier_name() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier_name")).getValue();
  }
}
