package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentOperator1 extends AssignmentOperator {
  public AssignmentOperator1(ASTStringNode eq, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("eq", eq)
    }, firstToken, lastToken);
  }
  public AssignmentOperator1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentOperator1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getEq() {
    return ((PropertyOne<ASTStringNode>)getProperty("eq")).getValue();
  }
}
