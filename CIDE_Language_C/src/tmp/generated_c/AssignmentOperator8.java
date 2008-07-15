package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentOperator8 extends AssignmentOperator {
  public AssignmentOperator8(ASTStringNode asssr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("asssr", asssr)
    }, firstToken, lastToken);
  }
  public AssignmentOperator8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentOperator8(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAsssr() {
    return ((PropertyOne<ASTStringNode>)getProperty("asssr")).getValue();
  }
}
