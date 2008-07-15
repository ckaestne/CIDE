package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class pointer_member_access extends GenASTNode {
  public pointer_member_access(ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public pointer_member_access(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new pointer_member_access(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
