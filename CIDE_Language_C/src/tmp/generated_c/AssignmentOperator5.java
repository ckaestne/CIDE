package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentOperator5 extends AssignmentOperator {
  public AssignmentOperator5(ASTStringNode assplus, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("assplus", assplus)
    }, firstToken, lastToken);
  }
  public AssignmentOperator5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentOperator5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAssplus() {
    return ((PropertyOne<ASTStringNode>)getProperty("assplus")).getValue();
  }
}
