package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentOperator9 extends AssignmentOperator {
  public AssignmentOperator9(ASTStringNode assamp, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("assamp", assamp)
    }, firstToken, lastToken);
  }
  public AssignmentOperator9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentOperator9(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAssamp() {
    return ((PropertyOne<ASTStringNode>)getProperty("assamp")).getValue();
  }
}
