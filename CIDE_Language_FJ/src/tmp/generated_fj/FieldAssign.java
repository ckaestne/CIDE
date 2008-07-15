package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FieldAssign extends GenASTNode {
  public FieldAssign(ASTStringNode identifier, ASTStringNode identifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<ASTStringNode>("identifier1", identifier1)
    }, firstToken, lastToken);
  }
  public FieldAssign(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FieldAssign(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public ASTStringNode getIdentifier1() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier1")).getValue();
  }
}
