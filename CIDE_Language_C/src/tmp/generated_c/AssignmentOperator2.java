package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentOperator2 extends AssignmentOperator {
  public AssignmentOperator2(ASTStringNode assstar, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("assstar", assstar)
    }, firstToken, lastToken);
  }
  public AssignmentOperator2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentOperator2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAssstar() {
    return ((PropertyOne<ASTStringNode>)getProperty("assstar")).getValue();
  }
}
