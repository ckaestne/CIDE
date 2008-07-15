package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentOperator7 extends AssignmentOperator {
  public AssignmentOperator7(ASTStringNode asssl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("asssl", asssl)
    }, firstToken, lastToken);
  }
  public AssignmentOperator7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentOperator7(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAsssl() {
    return ((PropertyOne<ASTStringNode>)getProperty("asssl")).getValue();
  }
}
