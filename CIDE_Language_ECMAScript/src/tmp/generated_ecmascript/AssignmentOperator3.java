package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentOperator3 extends AssignmentOperator {
  public AssignmentOperator3(ASTStringNode slashassign, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("slashassign", slashassign)
    }, firstToken, lastToken);
  }
  public AssignmentOperator3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentOperator3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSlashassign() {
    return ((PropertyOne<ASTStringNode>)getProperty("slashassign")).getValue();
  }
}
