package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentOperator3 extends AssignmentOperator {
  public AssignmentOperator3(ASTStringNode assslash, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("assslash", assslash)
    }, firstToken, lastToken);
  }
  public AssignmentOperator3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentOperator3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAssslash() {
    return ((PropertyOne<ASTStringNode>)getProperty("assslash")).getValue();
  }
}
